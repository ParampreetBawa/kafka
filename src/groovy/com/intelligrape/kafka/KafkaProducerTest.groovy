package com.intelligrape.kafka
/**
 * Created by parampreet on 11/10/14.
 */

class KafkaProducerTest {

    public static void main(String[] args){

        //Try();
//        convEvent();
        cmevent()
    }

    static cmevent(){
        String campaignID = 'ff808181488c660401488c77695203aa'
        String publisherID = '8a80813a468241a701468246348d0001'
        int cpv = 5
        int cpvAdv = 30
        for(int i = 0; i <= 20; i++){
            Map data = [
                    time: (new Date() - i),
                    campaignID: campaignID,
                    publisherID: publisherID,
                    action: 'Capture',
                    cpv: cpv,
                    cpvAdv: cpvAdv
            ]
            1.times {
                println(data.time)
                KafkaProducer.doProduce('cmevent', data)
            }
        }
    }
    static convEvent(){
        String campaignID = 'ff808181488c660401488c77695203aa'
        10.times {
            Map data = [time: new Date(),
                    campaignID: campaignID,
                    commentID:UUID.randomUUID(),
                    user:'user1',
                    network:'facebook',
                    message:'mesg1',
                    gender:'female'
                    ]
            KafkaProducer.doProduce('conversationevent', data)
        }
    }

    static void _try(){
        String playerID = '385ecae7-4d98-45ea-83dd-886033a24417'
        String matchID = UUID.randomUUID()
        String teamID = UUID.randomUUID()
        100.times {
            Map data = [time: new Date(System.currentTimeMillis()),
                    playerID: playerID,
                    matchID: matchID,
                    teamID: teamID,
                    country:"india",
                    wickets: 10]
            KafkaProducer.doProduce('test_event', data)
        }
    }
}
