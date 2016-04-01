package com.test.army

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Created by parampreet on 9/1/15.
 */
class Pos {
    int x
    int y

    public String toString() {
        "X:${x} Y:${y}"
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(x)
        builder.append(y)
        return builder.hashCode()
    }

    @Override
    public boolean equals(Object o) {
        Pos pos = (Pos)o
        pos.x == this.x && pos.y == this.y
    }
}
