# Project Detail

The service is build based on Spring Boot to act as a cache service for redis instance.

## Supported Behavior
Version 1.0
- a non hit key will result to redis db look up
  - found in redis, store in cache
  - no found, inform client
- cache value is ordered with LRU algorithm
- cache value has a pre defined timeout value in millisecond
Up coming
- less blocking on the LRU cache service
- Pass-in parameter support
- config support on remote redis instance connection

### Endpoints

Access
```
GET http://<host>:8080/redisrest/search/{key}
```
Insert test data
```
POST http://<host>:8080/redisrest/insert/{key}/{value}
```
### Diagram
---

![Example Diagram](https://yuml.me/14bede2a.png)

---

### Compile and Building

MVN to build local .jar file
```
./mvn clean package
```
Docker image build
```
docker build --tag <name> .
```

### Deploy
local application only run
```
docker run -p 8080:8080 -d <image name>
```
single node deploy
```
docker-compose up
```
