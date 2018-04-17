FROM maven:3.5-jdk-8 as build

COPY src /usr/src/redislru/src
COPY pom.xml /usr/src/redislru
RUN mvn -f /usr/src/redislru/pom.xml clean package

FROM java:openjdk-8-alpine

COPY --from=build /usr/src/redislru/target/SpringBootRedisRest-0.0.1-SNAPSHOT.jar /opt/SpringBootRedisRest-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/SpringBootRedisRest-0.0.1-SNAPSHOT.jar"]