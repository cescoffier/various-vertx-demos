package me.escoffier.demo.bank;

import io.vertx.core.Vertx;
import me.escoffier.demo.bank.balance.BalanceVerticle;
import me.escoffier.demo.bank.customer.CustomerVerticle;
import me.escoffier.demo.bank.debt.DebtVerticle;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(BalanceVerticle.class.getName());
        vertx.deployVerticle(DebtVerticle.class.getName());
        vertx.deployVerticle(CustomerVerticle.class.getName());
    }
}
