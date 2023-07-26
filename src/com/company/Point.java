package com.company;

import java.util.LinkedList;

public class Point {

    int z;
    String caveNo;
    private boolean caveVisited;
    LinkedList<Path> edges;

    Point(int n, String name) {
        this.z = n;
        this.caveNo = name;
        caveVisited = false;
        edges = new LinkedList<>();
    }

    boolean isCaveVisited() {
        return caveVisited;
    }

    void visit() {
        caveVisited = true;
    }

    void notVisited() {
        caveVisited = false;
    }
}
