package com.fight

/**
 * Created by parampreet on 10/2/15.
 */
class Threshold {
    private static Map<Integer,Float> speedRadiunMap = [:]

    static Map<Integer,Float> getThreshold() {
        return new HashMap<>(speedRadiunMap);
    }

    static Map<Integer,Float> setThreshold(Map map) {
        speedRadiunMap = new HashMap<>(map)
    }
}
