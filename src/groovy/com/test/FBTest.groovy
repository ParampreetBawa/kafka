package com.test

/**
 * Created by parampreet on 10/8/15.
 */
class FBTest {
    public static void main(String[] args) {
        def fbId = 157007217971788

        HttpURLConnection con = new URL("http://graph.facebook.com/${fbId}/picture?type=large").openConnection()
        con.setInstanceFollowRedirects(true)
        println con.responseCode
        println con.getHeaderField("Location")
        con = new URL(con.getHeaderField("Location")).openConnection()
        println con.responseCode
        BufferedInputStream bis = new BufferedInputStream(con.inputStream)
        Thread.sleep(100000)

    }
}
