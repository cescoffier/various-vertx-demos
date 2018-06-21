package me.escoffier.demo.iot;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.Flowable;
import io.vertx.core.json.JsonObject;
import io.vertx.kafka.client.serialization.JsonObjectDeserializer;
import io.vertx.kafka.client.serialization.JsonObjectSerializer;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.kafka.client.consumer.KafkaConsumer;
import io.vertx.reactivex.kafka.client.consumer.KafkaConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;


public class DataProcessorVerticle extends AbstractVerticle {

    @Override
    public void start() {
        KafkaConsumer<String, JsonObject> consumer =
            KafkaConsumer.create(vertx, configuration(), String.class, JsonObject.class);


        Flowable<JsonObject> stream = consumer.subscribe("data").toFlowable()
            .map(KafkaConsumerRecord::value);

        stream
            .map(json -> json.getInteger("data"))
            .window(5)
            .flatMap(MathFlowable::averageDouble)
            .subscribe(
                average ->
                    vertx.eventBus()
                        .publish("average", average)
            );
        
        //TODO Extract "data"
        //TODO Take the last 5 items
        //TODO Compute the average
        //TODO Publish to the event bus
        
    }

    private Map<String, String> configuration() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.serializer", StringSerializer.class.getName());
        config.put("value.serializer", JsonObjectSerializer.class.getName());
        config.put("key.deserializer", StringDeserializer.class.getName());
        config.put("value.deserializer", JsonObjectDeserializer.class.getName());
        config.put("group.id", "some-group");
        config.put("acks", "1");
        return config;

    }
}
