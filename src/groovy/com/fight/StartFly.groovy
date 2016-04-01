package com.fight

/**
 * Created by parampreet on 10/2/15.
 */
class StartFly {
    public static void main(String[] args) {
        setupThreshHold()

        Geo geo = genGeo()
        geo.print()
        Plane plane = new Plane(velocity: 200,total: geo,threshold: Threshold.getThreshold())
        plane.startFlying()
    }

    static Geo genGeo() {
        int y = 0
        int x = -1
        Geo geo = new Geo()
        100.times {
            x++
            geo.points << new Point(x: x, y: y)
        }
        Random random = new Random()
        for( ; x <= 500; x++) {
            int temp = random.nextInt(3)
            boolean upOrDown = (random.nextInt(2) == 0)
            if(!upOrDown) {
                y += temp
            }else
                y -= temp

            if( y < 0)
                y = 0
            geo.points << new Point(x:x, y: y)
        }
        //geo.print()
        geo
    }

    static void setupThreshHold() {
        Map map = [0: (80 * 3.14 / 360),20: (70 * 3.14 / 360),40: (70 * 3.14 / 360),50: (70 * 3.14 / 360),60: (70 * 3.14 / 360)]
        Threshold.setThreshold(map)
    }
}
