




FROM openjdk:21
COPY . /app
WORKDIR /app
CMD ["java", "-jar", "target/java-docker-0.0.1-SNAPSHOT.jar"]

