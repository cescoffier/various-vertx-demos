package me.escoffier.demo.iot;

import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.Vertx;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.rxDeployVerticle(IOTGateway.class.getName())
            .flatMap(x ->
                vertx.rxDeployVerticle(
                    DataProcessorVerticle.class.getName()))
            .flatMap(x ->
                vertx.rxDeployVerticle(
                    WebVerticle.class.getName()))
            .flatMap(x ->
                vertx.rxDeployVerticle(
                    Sensor.class.getName(),
                    new DeploymentOptions().setInstances(1)))
            .subscribe();
    }
}
