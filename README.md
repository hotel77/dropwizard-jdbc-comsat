# dropwizard-jdbc-comsat

## Example project to verify JDBC comsat instrumentation within dropwizard

## To Build

```
mvn package
```

## To Run (windows)

```
java -Xms256m -Xmx512m -D"co.paralleluniverse.fibers.verifyInstrumentation=true" -jar -javaagent:.\lib\quasar-core-0.7.3.jar .\target\jdbc-instrumentation-1.0-SNAPSHOT.jar server .\config\application-development.yml
```

