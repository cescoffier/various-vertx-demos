# Various demo using Eclipse Vert.x

These demos are used with this [slide deck](https://docs.google.com/presentation/d/1lt8ebEWlJV8IIrzQO5owm3sw8a7FpqrcihMDCpv7bog/edit?usp=sharing).

## Prerequisites

1. Docker - you need docker to start various services on your machine. Use _native_ docker (and not version using a 
VM, such as Docker Machine). 
2. Java 8 (JDK) - you can either use OracleJDK or OpenJDK
3. Apache Maven - 3.3.9+
4. An ide able to import Maven project

Checkout this project and build it:

```bash
git clone https://github.com/cescoffier/various-vertx-demos.git
cd various-vertx-demos
mvn clean install 
```

Then, import the project in your IDE using the adequate Maven import feature. 

## Demo simple-vertx-app

The demo code is located in: `simple-vertx-app/src/main/java/me/escoffier/demo/Main.java`.
In your IDE, you should be able to _run_ it as a Java application.

1. Start it
2. Open your browser to http://localhost:8080
3. Refresh the page a couple of times

Don't forget to stop the process once done.

## Demo vertx-web-app

The demo code is located in: `vertx-web-app`

1. In this directory, launch `./start-database.sh` - it starts a PostGreSQL database
2. Run the application with `java -jar vertx-web-app-1.0-SNAPSHOT.jar`
3. Open your browser to http://localhost:8080
4. Play with the application

Once done:

1. Stop the application (CTRL+C in your terminal)
2. Stop the docker process

## Demo gateway-demo

The demo code is located in `gateway-demo`

1. In the legacy-services directory, launch in a terminal: `java -jar target/legacy-services-1.0-SNAPSHOT.jar`
2. In your IDE, open `me.escoffier.demo.bank.gateway.Main` from the `gateway-demo` project
3. Run it as a Java application from your IDE
4. Open your browser to http://localhost:8080

Once done, stop the legacy-service application (CTRL+C in the terminal), and stop the application in the IDE.

## Demo IOT-Gateway

The demo code is located in `iot-gateway`

1. In the `iot-gateway` directory, run `./start-kafka.sh`
2. In your IDE, open `me.escoffier.demo.iot.Main` and run it as a Java application
4. Open your browser to http://localhost:8080 - you should see a graph

 







