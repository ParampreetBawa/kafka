package com.intelligrape.kafka

import org.apache.log4j.Logger

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by parampreet on 11/10/14.
 */
class KafkaProducerTest {
    private final static Logger log = Logger.getLogger(KafkaProducerTest.class)

    public static void main(String[] args) {
//        cmevent()
//        ytStats()
      playerEvent()
//        conv()
//        shareevent()
//        cricket()
//        test()
        rtbwin()

    }

    static test(){
        int i = 0;
        500.times {
            KafkaProducer.doProduce('druidtest',['utcdt':new Date(),'wp':Math.random()*100])
        }
    }

    private static void cricket(){
        100.times {
            Map data = [
                    playerid: UUID.randomUUID(),
                    wickets: 1,
                    score: 11,
                    time: new Date()
            ]
            KafkaProducer.doProduce('cricket', data)
        }
    }

    private static void shareevent(){
        11.times {
            Map data = [
                    time: new Date(),
                    campaignID:'ff8081814d48b725014d48bd61fe0003',
                    network:'CopyAndPasteShareURL',
                    action: 'Post',
                    srcShortDomain:'localhost',
                    publisherID:'297edbff44b187b80144b2cc5b2c0000'
            ]
            KafkaProducer.doProduce('shareevent', data)
        }

    }

    private static void conv(){
        List<String> campaignIDs = ['ff808181488c660401488c77695203aa', 'ff80818148a770470148a77146e90000', 'ff80818148bfce380148c00917660000']
        100.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size() - 1))
            Map data = [
                    time: new Date()-5,
                    campaignID: campaignID,
                    "message":"some message",
                    "sentiment":10,
                    "commentID":UUID.randomUUID(),
                    "user":UUID.randomUUID(),
                    "network":["facebook","youtube"].get((int)Math.random()*2)
            ]
            KafkaProducer.doProduce('conversationevent', data)
        }
    }

    private static void ytStats() {
        List<String> campaignIDs = ['ff8081814a0ec2f8014a0ecb5a4f0007'/*, 'ff80818148a770470148a77146e90000', 'ff80818148bfce380148c00917660000'*/]
        100.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size() - 1))
            Map data = [
                    time: new Date(),
                    campaignID: campaignID,
                    videoID: 'hadfadf',
                    insightTrafficSourceType: 'NO_LINK_EMBEDDED',
                    estimatedMinutesWatched: (int) (Math.random() * 10),
                    views: (int) (Math.random() * 1000)
            ]
            KafkaProducer.doProduce('youtubetrafficsourcetypestats', data)
        }
    }

    private static void playerEvent(){
        println(new Date())
        List<String> campaignIDs = ['ff8081814e4105ed014e41c0cf930024'/*,'ff8081814e4105ed014e41c052460018','ff8081814e4105ed014e41c0cf930024'*/]
        100.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size()))
            Map data = [
                    time: new Date(),
                    campaignID: campaignID,
                    action: 'CountsAsView',
                    publisherID: '8a80813a468241a701468245fab80000',
                    srcShortDomain:'viralgains.com',
                    elapsedSeconds:30,
                    avgViewability: ((int)(Math.random() * 100))
            ]
            KafkaProducer.doProduce('playerevent', data)
        }
    }

    private static void rtbwin(){
        println(new Date())
        List<String> campaignIDs = ['ff8081814e4105ed014e41c0cf930024'/*,'ff8081814e4105ed014e41c052460018','ff8081814e4105ed014e41c0cf930024'*/]
        100.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size()))
            int n =(int)(100000 * Math.random())
            Map data = [
                    time: new Date(),
                    adUnitId: campaignID,
                    winAmountMilliCentsDim: n,
                    winAmountMilliCents :n,
                    vendorId: '8a80813a468241a701468245fab80000'
            ]
            KafkaProducer.doProduce('rtbwin', data)
        }
    }

    private static void cmevent() {
        List<String> campaignIDs = ['ff8081814a0ec2f8014a0ecb5a4f0007']
        10.times {
            String campaignID = campaignIDs.get((int) Math.random() * (campaignIDs.size() - 1))
            Map data = [
                    time: new Date(),
                    campaignID: campaignID,
                    publisherID: ['297edbff44b187b80144b2cc5b2c0000', '8a80813a468241a701468245fab80000'][(int) Math.random() * 2],
                    action: ['Capture', 'Offer'][(int) Math.random() * 2],
                    cpv: 20,
                    cpvAdv: 5
            ]
            KafkaProducer.doProduce('cmevent', data)
        }
    }
}
