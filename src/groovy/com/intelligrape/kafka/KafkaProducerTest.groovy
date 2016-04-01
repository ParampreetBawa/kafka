package com.intelligrape.kafka

import org.apache.log4j.Logger

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by parampreet on 11/10/14.
 */
class KafkaProducerTest {
    static List statesList = ["AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY", "UN"]
    public static void main(String[] args) {
        final String id = args[0]
        1.times {
            new Thread(new Runnable() {
                @Override
                void run() {
                    while(true) {
                        playerEvent(id)
                        sleep(1000)
                    }
                }
            }).start()
        }
    }

    static Random random = new Random()
    private static void playerEvent(id) {
        List<String> campaignIDs = [id]
        10.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size()))
            Map data = [
                    time          : new Date(),
                    campaignID    : campaignID,
                    action        : ['CountsAsView', 'Load', 'Start'/*,'Paused','Done'*/][random.nextInt(3)],
                    publisherID   : 'd7ad3f80-00bf-40f6-ae4b-ede4ae6b820f',
                    srcShortDomain: 'viralgains.com',
                    elapsedSeconds: (5 * random.nextInt(28)),
                    regionCode    : statesList[random.nextInt(statesList.size())],
            ]
            KafkaProducer.doProduce('playerevent', data)
        }
    }
}
