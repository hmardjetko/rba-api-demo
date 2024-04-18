# CARD REQUEST API

A simple REST API with CRUD operations on Client and CardRequest objects with message exchange using Kafka.

### Startup 
Apache Kafka server should be running on localhost:9092 (default value). Topic is created automatically.

App uses H2 database in file mode. Dummy data can be filled on startup with property `spring.sql.init.mode=always`.