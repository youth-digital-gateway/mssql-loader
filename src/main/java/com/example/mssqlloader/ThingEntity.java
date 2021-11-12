package com.example.mssqlloader;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * JPA entity representing a `thing` domain object.
 *
 * @author Greg Baker (gregory.j.baker@hrsdc-rhdcc.gc.ca)
 * @since 0.0.0
 */
@Entity(name = "Thing")
@SuppressWarnings({ "serial" })
public class ThingEntity implements Persistable<String>, Serializable {

	@Id
	@GeneratedValue(generator = "uuid-generator")
	@GenericGenerator(name = "uuid-generator", strategy = UuidGenerator.STRATEGY)
	private String id;

	@Column(nullable = false)
	private String name;

	// Can be used to force spring to persist or merge:
	// 	isNew = true → use em.persist()
	// 	isNew = false → use em.merge()
	@Transient
	private Boolean isNew;

	@Override
	public String getId() {
		return id;
	}

	public ThingEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ThingEntity setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public boolean isNew() {
		return Optional.ofNullable(isNew).orElse(id == null);
	}

	public ThingEntity setNew(boolean isNew) {
		this.isNew = isNew;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		return Objects.equals(id, ((ThingEntity) obj).id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
