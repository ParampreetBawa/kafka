package com.intelligrape

import com.intelligrape.kafka.KafkaConsumerGroup
import com.intelligrape.kafka.KafkaProducer

class ProducerController {

    def index() {
        KafkaProducer.doProduce('mytopic',['producerID':"1","msg":params.msg])
    }
    def consume(){
        String zooKeeper = 'localhost:2181'
        String groupId = 'test-group'
        String topic = 'test'
        KafkaConsumerGroup example = new KafkaConsumerGroup(zooKeeper, groupId, topic);
        example.run(3);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ie) {
//            //ignore
//        }
//        example.shutdown();
    }
}
