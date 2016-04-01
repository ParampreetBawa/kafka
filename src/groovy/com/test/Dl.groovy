package com.test

/**
 * Created by parampreet on 10/10/15.
 */
class Dl {
    Node start = null
    int size = 0
    class Node {
        Object data
        Node next
        Node prev
    }

    void add(Object o ) {
        Node node = new Node(data: o)
        if(start == null)
            start = node;

        else {
            Node temp = start
            while(start.next != null) {
                start = start.next
            }

            start.next = node
            node.prev = start

            start = temp;
        }

        size++
    }

    boolean removeFront() {
        if(start == null)
            return false
        if(start.next == null) {
            start = null
            size--
            return true
        }

        start = start.next;
        size--
        return true
    }

    void removeLast() {

    }

    int size() {
        return size;
    }

    void print() {
        Node temp = start
        while(start != null) {
            print(start.data)
            start = start.next
        }
        start = temp;
    }

    public static void main(String[] args) {
        Dl dl = new Dl();
        dl.add(1)
        dl.add(2)
        dl.add(3)
        dl.add(4)

        dl.print()
        dl.removeFront()
        dl.print()
    }
}
