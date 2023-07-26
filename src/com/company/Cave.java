package com.company;

import java.util.ArrayList;

public class Cave {

    int caveNo;
    double x;
    double y;

    ArrayList<Integer> links;



    public Cave(int caveNo, double x, double y, ArrayList<Integer> links) {
        this.caveNo = caveNo;
        this.x = x;
        this.y = y;
        this.links = new ArrayList<>();

    }

    public Cave(int caveNo, double x, double y) {
        this.caveNo = caveNo;
        this.x = x;
        this.y = y;

    }


    @Override
    public String toString() {
        return "Cave+" + caveNo + "{" +
                "x=" + x +
                ", y=" + y +
                "}" + "\nConnections: " + links +
                '}';
    }

}
