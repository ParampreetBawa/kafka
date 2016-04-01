package com.intelligrape.kafka

import groovy.util.logging.Log4j
import kafka.consumer.ConsumerConfig
import kafka.consumer.KafkaStream
import kafka.javaapi.consumer.ConsumerConnector

/**
 * Created by parampreet on 11/10/14.
 */
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Log4j
public class KafkaConsumerGroup {
    private final ConsumerConnector consumer;
    private final List<String> topics;
    private  ExecutorService executor;

    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
    }

    public KafkaConsumerGroup(String a_zookeeper, String a_groupId, List<String> a_topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(a_zookeeper, a_groupId));
        this.topics = a_topic;
    }

    public void run(int a_numThreads) {
        executor = Executors.newFixedThreadPool(a_numThreads);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topics.each {topic->
            topicCountMap.put(topic, new Integer(a_numThreads));
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        for(String topic : topics) {
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
            for (final KafkaStream stream : streams) {
                executor.submit(new KafkaConsumer(stream,0));
            }
        }

        // now launch all the threads
        //


        // now create an object to consume the messages
        //

        println("run........")
    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("queued.max.message.chunks","10")

        return new ConsumerConfig(props);
    }
}