package me.escoffier.demo.bank.gateway;


import io.vertx.core.Vertx;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(GatewayVerticle.class.getName());
    }

}
