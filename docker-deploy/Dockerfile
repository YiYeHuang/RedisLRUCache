#Official JDK8 image
FROM java:openjdk-8-alpine
ADD SpringBootRedisRest-0.0.1-SNAPSHOT.jar /opt/SpringBootRedisRest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/SpringBootRedisRest-0.0.1-SNAPSHOT.jar"]
