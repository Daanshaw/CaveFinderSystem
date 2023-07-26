package com.company;

import java.util.*;

class Map {
    private final Set<Point> points;
    private final boolean directed;

    Map(boolean directed) {
        this.directed = directed;
        points = new HashSet<>();
    }


    public void addPoint(Point... z) {

        points.addAll(Arrays.asList(z));
    }

    //method to add the connections and check for no duplicates

    public void addLink(Point start, Point end, double weight) {

        points.add(start);
        points.add(end);


        linkCheck(start, end, weight);

        if (!directed && start != end) {
            linkCheck(start, end, weight);
        }
    }

    private void linkCheck(Point a, Point b, double weight) {

        for (Path edge : a.edges) {
            if (edge.start == a && edge.end == b) {

                edge.cost = weight;
                return;
            }
        }

        a.edges.add(new Path(a, b, weight));
    }


    public boolean hasLink(Point source, Point destination) {
        LinkedList<Path> edges = source.edges;
        for (Path edge : edges) {

            if (edge.end == destination) {
                return true;
            }
        }
        return false;
    }


    //method to find the path
    public String path(Point start, Point end) {

        HashMap<Point, Point> jumpedTo = new HashMap<>();
        jumpedTo.put(start, null);


        HashMap<Point, Double> bestPath = new HashMap<>();


        for (Point cave : points) {
            if (cave == start)
                bestPath.put(start, 0.0);
            else bestPath.put(cave, Double.POSITIVE_INFINITY);
        }


        for (Path edge : start.edges) {
            bestPath.put(edge.end, edge.cost);
            jumpedTo.put(edge.end, start);
        }

        start.visit();


        while (true) {
            Point presentCave = caveNeighbour(bestPath);

            if (presentCave == null) {
                String noPath = "0";

                return noPath;
            }

            // if destination cave found, this is the path
            if (presentCave == end) {


                Point to = end;


                String path = end.caveNo;
                while (true) {
                    Point from = jumpedTo.get(to);
                    if (from == null) {

                        break;
                    }


                    path = from.caveNo + " " + path;
                    to = from;
                }
                //System.out.println(path);

                return path;
            }
            presentCave.visit();

            //checking for the best path

            for (Path edge : presentCave.edges) {
                if (edge.end.isCaveVisited())
                    continue;

                if (bestPath.get(presentCave)
                        + edge.cost
                        < bestPath.get(edge.end)) {
                    bestPath.put(edge.end,
                            bestPath.get(presentCave) + edge.cost);
                    jumpedTo.put(edge.end, presentCave);
                }
            }
        }
    }

    private Point caveNeighbour(HashMap<Point, Double> shortestPathMap) {

        double cheapeastDistace = Double.POSITIVE_INFINITY;
        Point closestReachableNode = null;
        for (Point node : points) {
            if (node.isCaveVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < cheapeastDistace) {
                cheapeastDistace = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }


}

