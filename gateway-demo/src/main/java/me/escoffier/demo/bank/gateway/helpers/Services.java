package me.escoffier.demo.bank.gateway.helpers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Services {

    public static WebClient getCustomerService(Vertx vertx) {
        return WebClient.create(vertx, new WebClientOptions().setDefaultPort(9001));
    }

    public static WebClient getDebtService(Vertx vertx) {
        return WebClient.create(vertx, new WebClientOptions().setDefaultPort(9002));
    }

    public static WebClient getBalanceService(Vertx vertx) {
        return WebClient.create(vertx, new WebClientOptions().setDefaultPort(9003));
    }

    public static io.vertx.reactivex.ext.web.client.WebClient getCustomerService(io.vertx.reactivex.core.Vertx vertx) {
        return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9001));
    }

    public static io.vertx.reactivex.ext.web.client.WebClient getDebtService(io.vertx.reactivex.core.Vertx vertx) {
        return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9002));
    }

    public static io.vertx.reactivex.ext.web.client.WebClient getBalanceService(io.vertx.reactivex.core.Vertx vertx) {
        return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9003));
    }

}
