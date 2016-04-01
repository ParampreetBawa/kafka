package com.viralgains.test
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.leader.LeaderLatch
import org.apache.curator.framework.recipes.leader.LeaderLatchListener
import org.apache.curator.framework.recipes.leader.Participant
import org.apache.curator.retry.ExponentialBackoffRetry

/**
 * @author yl
 * @date 2013-02-19
 */
public class LeaderLatchExample {

    private CuratorFramework client;
    private String latchPath;
    private String id;
    private LeaderLatch leaderLatch;

    public LeaderLatchExample(String connString, String latchPath, String id) {
        client = CuratorFrameworkFactory.newClient(connString, new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
        this.id = id;
        this.latchPath = latchPath;
    }

    public void start() throws Exception {
        client.start();
        client.getZookeeperClient().blockUntilConnectedOrTimedOut();
        leaderLatch = new LeaderLatch(client, latchPath, id);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            void isLeader() {
                println("is leader "+id)

            }

            @Override
            void notLeader() {
                println("not leader "+ id)
            }
        })
        leaderLatch.start();
    }

    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

    public Participant currentLeader() throws Exception {
        return leaderLatch.getLeader();
    }

    public void close() throws IOException {
        leaderLatch.close();
        client.close();
    }
    static void removeRec(CuratorFramework client, String path) {

        client.checkExists().forPath(path)
        List<String> childs = client.getChildren().forPath(path)
        println(childs)
        if(childs.size() > 0) {
            for (String child : childs) {
                removeRec(client, path + "/" + child)
            }
        }else {
            client.delete().forPath(path)
        }
    }

    public static void main(String[] args) throws Exception {
        String latchPath = "/latch";
        String connStr = "127.0.0.1:2181";
        LeaderLatchExample node1 = new LeaderLatchExample(connStr, latchPath, "node-1");

        CuratorFramework client = node1.client;
        client.start()

        removeRec(client,"/consumers/parampreet-rtb-group/offsets")





//        LeaderLatchExample node2 = new LeaderLatchExample(connStr, latchPath, "node-2");
//        node1.start();
//        node2.start();
//
//
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println("node-1 think the leader is " + node1.currentLeader());
//            System.out.println("node-2 think the leader is " + node2.currentLeader());
//            Thread.sleep(500);
//        }
//
//
//
//
////        System.out.println("now node-2 think the leader is " + node1.currentLeader());
////
//
//
//        Runtime.getRuntime().addShutdownHook({
//            println("shuttin down")
//            node1.close();
//            node2.close()
//        })
//        Thread.sleep(500000)
//



    }

}