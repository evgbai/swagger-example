# Swagger Cats Example

## Overview
* A simple application using [Springdoc - Swagger](https://springdoc.org/). 
* The Springdoc configuration can be seen in the [OpenAPIConfiguration](https://github.com/evgbai/swagger-example/blob/main/src/main/java/ru/decathlon/swagger/example/config/OpenAPIConfiguration.java) class. 
* Method's documentation is based on an annotations in the [controller](https://github.com/evgbai/swagger-example/blob/main/src/main/java/ru/decathlon/swagger/example/controller/CatController.java).

### To run (via Docker)

Expose port 8080 from the image and access `swagger-example` via the exposed port. You can then add and delete cats as you see fit.

* #### Create Docker Image:
*Example*:

```
docker build -t swagger-example/catservice:unstable .
```

* #### Download Docker Image from DockerHub:
*Example*

```
docker pull evgbai/swagger-example:unstable
docker run  --name swagger-example -d -p 8080:8080 evgbai/swagger-example:unstable
```

### Testing the server
Once started, you can navigate to http://localhost:8080/swagger to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate.

### Redoc API
After running your application or Docker container, you can navigate to http://localhost:8080/ to view the Redoc API documentation.