package com.example.mssqlloader;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for reading/writing `thing` entities.
 *
 * @author Greg Baker (gregory.j.baker@hrsdc-rhdcc.gc.ca)
 * @since 0.0.0
 */
public interface ThingRepository extends JpaRepository<ThingEntity, String> {}
