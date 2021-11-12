package com.example.mssqlloader;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

/**
 * Application bootstrapping class.
 *
 * @author Greg Baker (gregory.j.baker@hrsdc-rhdcc.gc.ca)
 * @since 0.0.0
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/* Create a proxied datasource so we can get some info about what Hibernate is doing */
	@Bean DataSource dataSource(DataSourceProperties dataSourceProperties) {
		final var dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
		return ProxyDataSourceBuilder.create(dataSource).logQueryBySlf4j(SLF4JLogLevel.INFO).build();
	}

}
