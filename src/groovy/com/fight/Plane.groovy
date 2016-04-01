package com.fight

/**
 * Created by parampreet on 10/2/15.
 */
class Plane {

    Long velocity = 0;
    Geo geoAhead
    Geo total
    Point currentLoc = new Point(x:0,y:1)
    Map<Integer,Float> threshold = [:]

    void startFlying() {
        new Thread(new Runnable() {
            @Override
            void run() {
                while(!crashed()) {
                    flying();
                }
            }
        }).start()
    }

    boolean crashed() {
        Point geo = total.points.find {it.x == currentLoc.x}
        currentLoc.y  <= geo.y
    }

    void flying() {
        currentLoc.x += (velocity / 100)
        total.print(currentLoc)
//        getGeoAhead()

        //TODO update geo
        // take decision
        //stay close to ground
        //take optimal decision with speed and distance from ground
        Thread.sleep(10)
    }

    Geo getGeoAhead() {
        List<Point> nextPoints = total.getPoints().find {it.x >= currentLoc.x && it.x <= currentLoc.x + 100}
        List dx = []
        nextPoints.each {
            it.y
        }
    }

    void updateGeo() {

    }

}
