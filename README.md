## dropwizard-jdbc-comsat

#### Example project to verify JDBC comsat instrumentation within dropwizard

Builds a capsule container with a manifest that already contains the javaagent args which will instrument the code for us.
This is an example of a raw JDBC application written with blocking DB access, instrumented by quasar (via comsat-dropwizard, comsat-jdbc) which makes all the database access asynchronous.

##### To Build

```
mvn package
```

##### To Run (windows)

```
java -Xms256m -Xmx512m -jar .\target\jdbc-instrumentation-1.0-SNAPSHOT-capsule-fat.jar server .\config\application-development.yml
```

