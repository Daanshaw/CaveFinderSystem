package com.company;

class Path implements Comparable<Path> {

    Point start;
    Point end;
    double cost;

    Path(Point s, Point e, double c) {

        start = s;
        end = e;
        cost = c;
    }


    //method to compare weights

    public int compareTo(Path otherPath) {


        if (this.cost > otherPath.cost) {
            return 1;
        } else return -1;
    }

}





