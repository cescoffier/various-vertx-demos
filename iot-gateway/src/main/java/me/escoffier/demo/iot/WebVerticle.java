package me.escoffier.demo.iot;

import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.StaticHandler;
import io.vertx.reactivex.ext.web.handler.sockjs.SockJSHandler;

import java.util.Collections;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class WebVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        
        BridgeOptions options = new BridgeOptions();
        options.setOutboundPermitted(Collections.singletonList(new PermittedOptions()
            .setAddress("average")));
        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));
        
        router.route().handler(StaticHandler.create().setCachingEnabled(false));

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8080);
    }
}
