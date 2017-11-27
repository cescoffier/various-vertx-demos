package me.escoffier.demo;

import io.vertx.core.Vertx;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
            .requestHandler(req -> req.response()
                .end("hello "
                    + Thread.currentThread().getName()))
            .listen(8080);
    }
}
