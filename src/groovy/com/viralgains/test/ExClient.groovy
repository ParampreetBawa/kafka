package com.viralgains.test

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.leader.LeaderSelector
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by parampreet on 8/1/15.
 */
class ExClient extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name;
    private final LeaderSelector leaderSelector;
    private final AtomicInteger leaderCount = new AtomicInteger();

    ExClient(CuratorFramework client,String path, String name) {
        this.leaderSelector = new LeaderSelector(client,path,this)
        this.name = name
    }

    public void start() {
        leaderSelector.start()
    }

    @Override
    void close() throws IOException {
        leaderSelector.close()
    }

    @Override
    void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        System.out.println(name + " has been leader " + leaderCount.getAndIncrement() + " time(s) before.");
//        Thread.sleep(10000)
    }
}
