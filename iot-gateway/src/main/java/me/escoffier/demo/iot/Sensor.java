package me.escoffier.demo.iot;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.reactivex.Flowable;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.mqtt.MqttClient;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Sensor extends AbstractVerticle {

    private static final String HOST = "localhost";
    private static final int PORT = 1883;

    private final String id = UUID.randomUUID().toString();

    private Random random = new Random();

    @Override
    public void start() throws Exception {
        MqttClient client = MqttClient.create(vertx);

        client.rxConnect(PORT, HOST)
            .flatMapPublisher(ack ->
                Flowable.interval(1, TimeUnit.SECONDS)
                    .flatMapSingle(l -> {
                        JsonObject payload = new JsonObject()
                            .put("uuid", id)
                            .put("data",
                                random.nextInt(100));
                        return client
                            .rxPublish("/data",
                                Buffer.buffer(payload.encode()),
                                MqttQoS.AT_MOST_ONCE, false, false);
                    }))
            .subscribe();
    }
}
