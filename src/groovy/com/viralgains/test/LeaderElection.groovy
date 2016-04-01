package com.viralgains.test

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.utils.CloseableUtils

/**
 * Created by parampreet on 8/1/15.
 */
class LeaderElection {
    public static void main(String[] args) {
        List clients = []
        List exClients = []
        2.times { i ->
            CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3))
            ExClient exClient = new ExClient(client, "/test", "Client #-${i}")
            client.start();
            clients << client
            exClients << exClient
        }
//        doRandom(clients,exClients)
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    static void doRandom(List<CuratorFramework> clients,List<ExClient> exCLients) {
        Thread.sleep(5000)
        int r = 0//new Random().nextInt(clients.size())
        CloseableUtils.closeQuietly(clients.get(r));
        CloseableUtils.closeQuietly(exCLients.get(r));

        println("client 1 shutdown")
        Thread.sleep(5000)
//        f(r,clients,exCLients)
//        println("client 0 restarted")

//        Thread.sleep(5000)
//
//        r = 1
//        CloseableUtils.closeQuietly(clients.get(r));
//        CloseableUtils.closeQuietly(exCLients.get(r));
//        println("cleint 1 shutdown")
//        Thread.sleep(5000)

    }
    static void f(i,clients,exclients){
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3))
        ExClient exClient = new ExClient(client, "/test", "Client #-${i}")
        client.start();
        exClient.start()

        clients << client
        exclients << exClient
    }
}
