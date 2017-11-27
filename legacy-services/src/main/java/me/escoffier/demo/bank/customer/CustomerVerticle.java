package me.escoffier.demo.bank.customer;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class CustomerVerticle extends AbstractVerticle {

    private Map<String, Customer> accounts = new HashMap<>();

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.get("/customers/:id").handler(this::getAccount);


        accounts.put("clement", new Customer().setName("clement").setAccount(UUID.randomUUID().toString()));
        accounts.put("julien", new Customer().setName("julien").setAccount(UUID.randomUUID().toString()));

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(9001);
    }
    

    private void getAccount(RoutingContext rc) {
        Customer customer = accounts.get(rc.pathParam("id"));
        if (customer == null) {
            rc.response().setStatusCode(404).end("Account not found");
        } else {
            rc.response().end(customer.toJson().encodePrettily());
        }
    }

}
