package com.fight

/**
 * Created by parampreet on 10/2/15.
 */
class Geo {
    List<Point> points =[]

    void print(Point planePos = null) {
        List<Point> pointsCp = new ArrayList<>(points)
        if(planePos)
            pointsCp.add(planePos)
        List<Point> desc = points.sort({it.y}).reverse()
        Map<Long, List<Point>> yx = desc.groupBy {it.y}

        StringBuilder buffer = new StringBuilder()
        for(Long y: yx.keySet()) {
            List<Point> xx = yx.get(y)
            List xxx = xx.collect({it.x})

            Long max = xxx.max()
            for(long xi = 0; xi <= max; xi++) {
                if(planePos && planePos.x == xi && planePos.y == y) {
                    buffer.append("P")
                }else if(xxx.contains(xi))
                    buffer.append("*")
                else
                    buffer.append(" ")
            }

            buffer.append("\n")
        }

        print buffer.toString()
    }
}
