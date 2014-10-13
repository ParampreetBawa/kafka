package com.intelligrape.kafka

import grails.util.Holders
import groovy.util.logging.Log4j
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.SerializationConfig

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
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        return config
    }

    private static Producer<String, String> getProducer() {
        if (producer == null)
            producer = new Producer<String, String>(getConfig());
        return producer
    }

    public static void doProduce(String topicName, Map<String, Object> dataMap) {
        String topic = topicName.toLowerCase()
        ObjectMapper  om = new ObjectMapper()
        om.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
        String data = om.writeValueAsString(dataMap)
        String key = null
        KeyedMessage<String, String> km= new KeyedMessage(topic, key, data)
        if (log.isDebugEnabled())
            log.debug("Producing data on topic ${topic}:\n${data}")

        getProducer().send(km)
//        buffer << km
//        if (buffer.size() >= LIMIT) {
//            getProducer().send(buffer)
//            buffer.clear()
//        }
    }


}
