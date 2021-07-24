# @jvimora bia-service

Java Service to register and consult energy measures

## Tools & Frameworks
* Docker
* Spring boot 2.3.0.RELEASE
* H2 database (in memory)
* Java 11
* Open API (Swagger)

## Installation

* Clone repository
* Build docker file

```bash
docker build -t jvimora/bia .
```
* Run docker
```bash
docker run -p 8080:8080 jvimora/bia
```

## URLs

* [OpenAPI (Swagger)](http://localhost:8080/swagger-ui.html)
* [H2 Console (sa/root)](http://localhost:8080/console)

## API Endpoints
* localhost:8080/measures (create new register)
* localhost:8080/measures/average (get averages measures)
* localhost:8080/measures/filter (filter registers)

## Default data
```sql
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device1', 1400.0, '2021-07-21T14:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device1', 7320.0, '2021-07-21T15:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device1', 8845.0, '2021-07-21T16:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device1', 3424.0, '2021-07-21T17:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device2', 22.0, '2021-07-21T14:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device2', 31.0, '2021-07-21T15:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device2', 3423.0, '2021-07-21T16:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device3', 4324.0, '2021-07-21T14:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device3', 2424.0, '2021-07-21T15:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device4', 554.0, '2021-07-21T14:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device5', 5425.0, '2021-07-21T14:41:16.342Z');
INSERT INTO measure(timestamp, devicesn, energy, created_at) VALUES('2021-07-21T14:41:16.342Z', 'device5', 25234.0, '2021-07-21T15:41:16.342Z');
```
