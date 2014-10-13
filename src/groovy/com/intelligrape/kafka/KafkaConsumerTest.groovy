package com.intelligrape.kafka

import groovy.util.logging.Log4j
import org.apache.log4j.Appender
import org.apache.log4j.Level
import org.apache.log4j.Logger


/**
 * Created by parampreet on 11/10/14.
 */

class KafkaConsumerTest {

    public static void main(String[] args){
        String zooKeeper = args[0];
        String groupId = args[1];
        String topic = args[2];
        int threads = Integer.parseInt(args[3]);
        println("consuming ${topic} groupid - ${groupId} thread - ${threads}")

        KafkaConsumerGroup example = new KafkaConsumerGroup(zooKeeper, groupId, topic);
        example.run(threads);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ie) {
//            //ignore
//        }
//        example.shutdown();
    }
}
