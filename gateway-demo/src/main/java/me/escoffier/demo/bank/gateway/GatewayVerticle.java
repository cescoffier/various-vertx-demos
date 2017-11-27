package me.escoffier.demo.bank.gateway;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import me.escoffier.demo.bank.gateway.helpers.Services;


public class GatewayVerticle extends AbstractVerticle {

    private WebClient service;
    private WebClient debt;
    private WebClient balance;

    @Override
    public void start() throws Exception {

        service = Services.getCustomerService(vertx);
        debt = Services.getDebtService(vertx);
        balance = Services.getBalanceService(vertx);

        Router router = Router.router(vertx);
        router.get("/").handler(this::compute);

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8080);

    }

    private void compute(RoutingContext rc) {

        Single<String> single = service
            .get("/customers/clement").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .map(json -> json.getString("account"));

        single.flatMap(account -> {
            Single<Double> de =
                debt.get("/debt/" + account).rxSend()
                .map(HttpResponse::bodyAsJsonObject)
                .map(json -> json.getDouble("level"));

            Single<Double> ba =
                balance.get("/balance/" + account).rxSend()
                .map(HttpResponse::bodyAsJsonObject)
                .map(json -> json.getDouble("balance"));

            return Single.zip(de, ba, (d, b) ->
                new JsonObject()
                    .put("account", account)
                    .put("debt", d)
                    .put("balance", b));
        })
            .subscribe((res, err) -> {
            if (err != null) {
                rc.fail(err);
            } else {
                rc.response().end(res.encodePrettily());
            }
        });
    }
}
