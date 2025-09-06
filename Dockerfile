FROM openjdk:21-jdk-slim

WORKDIR /app

# copy the built JAR into container
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


