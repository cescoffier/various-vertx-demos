package me.escoffier.demo.bank.debt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class DebtVerticle extends AbstractVerticle {

    private Map<String, Double> levels = new HashMap<>();


    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.get("/debt/:account").handler(this::getDebt);

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(9002);
    }

    private void getDebt(RoutingContext rc) {
        String account = rc.pathParam(":account");

        if (!levels.containsKey(account)) {
            levels.put(account, Math.random() * 100);
        }
        rc.response().end(new JsonObject().put("level", levels.get(account)).encode());

    }
}
