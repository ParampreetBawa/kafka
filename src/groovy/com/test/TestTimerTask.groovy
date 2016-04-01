package com.test

/**
 * Created by parampreet on 7/31/15.
 */
class TestTimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer(true);
        MyTimerTask task = new MyTimerTask(name: "first");
        timer.scheduleAtFixedRate(task,0,5*1000)
        task = new MyTimerTask(name: "second");
        timer.scheduleAtFixedRate(task,0,10*1000)
        Thread.sleep(1000000)
    }
}

class MyTimerTask extends TimerTask {
    Long time =0
    String name
    void run() {
        println("name: ${this.name}, Thread :"+Thread.currentThread().name + " duration: ${(System.currentTimeMillis() - time)/1000}");
        time = System.currentTimeMillis()

    }
}
