package com.intelligrape.kafka

/**
 * Created by parampreet on 23/12/14.
 */
class TestLink {
    TestLink next
    TestLink randomLink

    public static void main(String[] args){
        TestLink o1 = new TestLink()
        TestLink o2 = new TestLink()
        TestLink o3 = new TestLink()
        TestLink o4 = new TestLink()


        o1.next = o2
        o2.next = o3
        o3.next = o4


        o1.randomLink = o3
        o3.randomLink = o2
        o2.randomLink = o4
        o4.randomLink = o1


        genDup(o1)


        Map<String,TestLink> graph = new HashMap<String,TestLink>()

        graph.put(1,o1)
        graph.put
    }

    public static void genDup(TestLink o1){

    }
}


