package com.intelligrape.kafka

import grails.converters.JSON
import groovy.util.logging.Log4j
import kafka.consumer.ConsumerIterator
import kafka.consumer.KafkaStream
import org.codehaus.jackson.map.ObjectMapper

/**
 * Created by parampreet on 11/10/14.
 */
@Log4j
class KafkaConsumer implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;

    public KafkaConsumer(KafkaStream a_stream, int a_threadNumber) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
    }

    public void run() {
        println("consumer started")
        int count = 0
        Long windowStart
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()) {
            def x = it.next()
            println x.offset()
            def msg = x.message()
//            Map data = JSON.parse(msg) as Map
//            data.time = new Date()
//            KafkaProducer.doProduce('cmevent',msg)
            println(msg)
            /*if (!windowStart)
                windowStart = System.currentTimeMillis()
            count++
            Long currentTime = System.currentTimeMillis()
            if (currentTime - windowStart >= 1000) {
                println("${count} messages consumer in ${currentTime - windowStart}ms")
                count = 0
                windowStart = currentTime
            }*/
        }

        System.out.println("Shutting down Thread: " + m_threadNumber);
    }

}
