package com.test.army

/**
 * Created by parampreet on 9/1/15.
 */
class Army {
    List<Bot> bots = []
    Bot findBotIfExist(Pos pos) {
        for(Bot bot: bots) {
            if(bot.pos.equals(pos)){
                return bot;
            }
        }
        return null
    }

    void startShoot() {
        for(Bot bot : bots) {
            if(bot.died)
                continue;
            bot.shoot()
        }
    }

    void addBot(Bot bot) {
        bots << bot
    }

    List getOccupiedPos() {
        List list = []
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                list.add(x*10 + y)
            }
        }

        bots.each {
            if(it.pos && !it.died) {
                list.remove(it.pos.x* 10 + it.pos.y)
            }
        }

        return list
    }

    boolean allDead() {
        boolean allDead = true
        for(Bot bot: bots) {
            if(!bot.died)
                allDead = false
        }
        return allDead
    }
}
