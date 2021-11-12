package com.example.mssqlloader;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A Spring {@link ApplicationRunner} that loads randomized data using a Spring Data repository.
 *
 * @author Greg Baker (gregory.j.baker@hrsdc-rhdcc.gc.ca)
 * @since 0.0.0
 */
@Component
@SuppressWarnings({ "unused" })
public class DataLoader implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

	private final ThingRepository repository;

	public DataLoader(ThingRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) {
		final var things = create(50_000);
		final var updatedThings = update(things);
	}

	protected List<ThingEntity> create(int limit) {
		// generate some data that can be dumped into the database
		final var things = Stream.generate(() -> new ThingEntity().setName(randomAlphabetic(12))).limit(limit).collect(toList());

		log.info("Creating {} things using Spring repositories…", limit);
		final var stopwatch = StopWatch.createStarted();
		final var createdEntities = repository.saveAll(things);
		log.info("Created {} things using Spring repositories in {} ms", limit, stopwatch.getTime());

		return createdEntities;
	}

	protected List<ThingEntity> update(Collection<ThingEntity> things) {
		// update the name of each thing
		things.stream().forEach(thing -> thing.setName(randomAlphabetic(12)));

		log.info("Updating {} things using Spring repositories…", things.size());
		final var stopwatch = StopWatch.createStarted();
		final var updatedEntities = repository.saveAll(things);
		log.info("Updated {} things using Spring repositories in {} ms", things.size(), stopwatch.getTime());

		return updatedEntities;
	}

}
