package me.escoffier.demo.bank.balance;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class BalanceVerticle extends AbstractVerticle {

    private final Map<String, Double> balances = new HashMap<>();
    private final Random random = new Random();


    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/balance/:account").handler(this::getBalance);

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(9003);
    }

    private void getBalance(RoutingContext rc) {
        String account = rc.pathParam("account");
        if (!balances.containsKey(account)) {
            balances.put(account, (random.nextBoolean() ? -1 : 1) * random.nextDouble() * 1000);
        }
        rc.response().end(new JsonObject()
            .put("account", account)
            .put("balance", balances.get(account)).encode());
    }
}
