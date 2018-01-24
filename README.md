# restapi

This is a trivial example of a Spring Boot REST API that demonstrates the following:
1. Using two different data sources for run vs. unit tests
  a. Using different configuration for run and test
2. Uses liquibase migrations on test data source only
3. Use MockMvc to mock out endpoints for testing without spinning up a web server
4. Use JPA to access data source regardless of type
5. Separate packages based on components

## TODO
Add example of unit testing repository
