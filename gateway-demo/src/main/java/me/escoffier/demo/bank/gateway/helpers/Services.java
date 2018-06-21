package me.escoffier.demo.bank.gateway.helpers;

import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.ext.web.client.WebClient;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Services {

  public static WebClient getCustomerService(io.vertx.reactivex.core.Vertx vertx) {
    return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9001));
  }

  public static WebClient getDebtService(io.vertx.reactivex.core.Vertx vertx) {
    return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9002));
  }

  public static WebClient getBalanceService(io.vertx.reactivex.core.Vertx vertx) {
    return io.vertx.reactivex.ext.web.client.WebClient.create(vertx, new WebClientOptions().setDefaultPort(9003));
  }

}
