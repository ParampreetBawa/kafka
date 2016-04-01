package com.test

import grails.converters.JSON

/**
 * Created by parampreet on 10/8/15.
 */
class Wc {
    public static void main(String[] args) {
        String text = new File("/home/parampreet/out4.txt").text
        Long c = 0
        Long evc = 0
        JSON.parse(text).each {
            c += it.event.count
            evc += it.event.eventcount
        }

        println(c)
        println(evc)
    }
}
