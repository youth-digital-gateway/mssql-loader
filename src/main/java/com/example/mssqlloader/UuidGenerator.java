package com.example.mssqlloader;

import java.io.Serializable;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * A hibernate {@link IdentifierGenerator} that will generate random
 * UUIDs when a persistable entity does not already have an ID.
 *
 * @author Greg Baker (gregory.j.baker@hrsdc-rhdcc.gc.ca)
 * @since 0.0.0
 */
public class UuidGenerator implements Configurable, IdentifierGenerator {

	public static final String STRATEGY = "com.example.mssqlloader.UuidGenerator";

	private final UUIDGenerator delegate;

	public UuidGenerator() {
		this(new UUIDGenerator());
	}

	public UuidGenerator(UUIDGenerator delegate) {
		this.delegate = delegate;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
		delegate.configure(type, params, serviceRegistry);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		 final var id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		 return Optional.ofNullable(id).orElseGet(() -> delegate.generate(session, object).toString());
	}

}
