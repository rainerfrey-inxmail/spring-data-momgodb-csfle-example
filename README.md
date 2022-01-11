# spring-data-momgodb-csfle-example

Demonstrates the problem with FLE encryption schema creation described in https://github.com/spring-projects/spring-data-mongodb/issues/3929

Fill MongoDB (Atlas) connection details and (local) encryption master key and data key ID in application.yml

On startup, the application tries to create collection schema with an encrypted property for entity class SampleEntity, with collection name "sample", in the test database.
