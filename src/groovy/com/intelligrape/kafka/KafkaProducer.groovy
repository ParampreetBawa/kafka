package com.intelligrape.kafka

import grails.util.Holders
import groovy.util.logging.Log4j
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.SerializationConfig
import scala.annotation.meta.getter

/**
 * Created by parampreet on 11/10/14.
 */
@Log4j
class KafkaProducer {
    private static Producer<String, String> producer = null
    private static List<KeyedMessage> buffer = []
    private static Integer LIMIT = 100

    private static ProducerConfig getConfig() {
        Properties props = new Properties();
//        String brokers = Holders.config.kafka.broker.list
        String brokers = "localhost:9092"
        props.put("metadata.broker.list", brokers);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("acks","1")
        props.put("compression.type","gzip")
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
//        props.put("request.required.acks", "1");
//        props.put('default.replication.factor',"1")
//        props.put("batch.num.messages", "2000");
//        props.put("producer.type", "sync")
//        props.put("queue.buffering.max.messages", "2000")

        ProducerConfig config = new ProducerConfig(props);
        return config
    }

    private static Producer<String, String> getProducer() {
        if (producer == null)
            producer = new Producer<String, String>(getConfig());
        return producer
    }

    public static void doProduce(String topic, Map data) {
        ObjectMapper om = new ObjectMapper()
        om.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
        String d = om.writeValueAsString(data)
        String key = "0"
        KeyedMessage<String, String> km= new KeyedMessage(topic, key, d)
        getProducer().send(km)
    }


}
