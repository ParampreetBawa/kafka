package com.test.army

/**
 * Created by parampreet on 9/1/15.
 */
class Arena {
    static Random random = new Random()
    public static void main(String[] args) {
        startFight();
    }
    void shoot(Army target, Pos pos) {
        //find bot
        //kill
        Bot bot = target.findBotIfExist(pos)
        if(bot != null)
            bot.hit()
        else {
            Log.log("miss fire")
        }
    }

    public static void startFight(){
        Arena arena = new Arena();

        Army armyA = new Army();
        Army armyB = new Army();
        5.times {
            Bot bot = new Bot(arena: arena,targetArmy: armyB)
            armyA.addBot(bot)
        }
        assignPos(armyA)

        5.times {
            Bot bot = new Bot(arena: arena,targetArmy: armyA)
            armyB.addBot(bot)
        }
        assignPos(armyB)


        Thread thread = new Thread(new Runnable() {
            @Override
            void run() {
                while(!armyA.allDead() && !armyB.allDead()) {
                    armyA.startShoot()
                    Thread.sleep(100)
                }
            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            void run() {
                while(!armyB.allDead() && !armyA.allDead()) {
                    armyB.startShoot()
                    Thread.sleep(100)
                }
            }
        });
        snap(armyA,armyB);
        threadB.setName("B")
        thread.setName("A")
        threadB.start();
        thread.start();

        Thread areanWatcher = new Thread(new Runnable() {
            @Override
            void run() {
                while(true) {
                    if(armyB.allDead() || armyA.allDead())
                        break
                    snap(armyA,armyB);
//                    snap(armyB);
                    Thread.sleep(200)
                }
                println(armyA.allDead() ? "ArmyA win" : "Army B wins")
            }
        })
        areanWatcher.start()
    }

    static void snap(Army A,Army B) {

        Closure cl = {army,a->
            print("\nArmy = ${a}\n")
            print(" ___________________\n")
            for (int x = 0; x < 5; x++) {
                print("\n")
//            print("|___|___|___|___|___|\n")
                print("|")
                for (int y = 0; y < 5; y++) {
                    Bot bot = army.findBotIfExist(new Pos(x: x, y: y))
                    boolean pres = false
                    if (bot && !bot.died)
                        pres = true

                    print(pres ? ' X ' : '   ')
                    print("|")
                }
            }
        }
        cl(A,"A")
        cl(B,"B")
        print("\n\n++++++++++++++++++\n\n\n")
    }


    public static void assignPos(Army army) {
        List list = army.getOccupiedPos()
        for(Bot bot: army.bots) {
            int idx = random.nextInt(list.size()-1)
            int xy = list.get(idx)
            int y = xy %10
            xy /= 10
            int x = xy % 10

            bot.pos.x = x
            bot.pos.y = y


//            System.out.println("X: ${x}, Y: ${y}")
        }
    }
}
