# MS SQL Loader

A barebones Spring Boot w/ JPA application for testing batch inserts.

## Running the application

### With embedded H2 database:

``` sh
# mvn clean spring-boot:run
```

### With SQL Server (on localhost -- see `docker-compose.yml`):

``` sh
# mvn clean spring-boot:run --define spring-boot.run.profiles=localhost
```

### With SQL server (in Azure):

Create an `application-azure.yml` in `src/main/resources` and then run the following.

``` sh
# mvn clean spring-boot:run --define spring-boot.run.profiles=azure
```
