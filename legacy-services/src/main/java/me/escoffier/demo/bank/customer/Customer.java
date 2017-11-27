package me.escoffier.demo.bank.customer;

import io.vertx.core.json.JsonObject;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Customer {

    private String name;

    private String account;

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public Customer setAccount(String account) {
        this.account = account;
        return this;
    }

    public JsonObject toJson() {
        return new JsonObject().put("name", name).put("account", account);
    }
}
