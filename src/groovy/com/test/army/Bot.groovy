package com.test.army

/**
 * Created by parampreet on 9/1/15.
 */
class Bot {
    Random random = new Random()
    Arena arena;//shared
    Army targetArmy;
    Pos pos = new Pos();
    boolean died = false;
    void shoot() {
        //rnadom pos

        Pos targetPos = new Pos(x:random.nextInt(5),y:random.nextInt(5))

        arena.shoot(targetArmy, targetPos);
    }
    void hit() {
        die();
    }

    void die() {
        this.died = true;
        Log.log("bot at ${pos} died")
    }
}
