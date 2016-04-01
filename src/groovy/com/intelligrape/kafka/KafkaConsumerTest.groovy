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
        String zooKeeper = "localhost:2181"
        String groupId = "test-gpid"
        List<String> topics = ["test2"]
        println("consuming ${topics} groupid - ${groupId} thread -1")

        KafkaConsumerGroup example = new KafkaConsumerGroup(zooKeeper, groupId, topics);
        example.run(1);
        println("end")
    }
}
