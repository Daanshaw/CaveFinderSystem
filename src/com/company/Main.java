package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {


        DistanceCalculator dc = new DistanceCalculator();

        String caveRouteFile = args[0];
        Path systemPath = FileSystems.getDefault().getPath("./", caveRouteFile + ".cav");



        File f = new File(String.valueOf(systemPath));
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String firstLine = br.readLine();

        String[] cavePositions = firstLine.split(",");

        Integer[] numbersFromFile = Stream.of(cavePositions).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);


        int numberOfCaves = numbersFromFile[0];

        int numberOfConnections = numberOfCaves * 2 + 1;
        int[] connectionsArray = new int[numberOfConnections];
        int noOfConnections = numberOfCaves * numberOfCaves;
        int[] connections = new int[numberOfCaves * numberOfCaves];


        Queue<Integer> queue = new LinkedList<>();


        double[] cavePosY = new double[numberOfCaves];
        double[] cavePosX = new double[numberOfCaves];

        //loop to extract positions of each cave

        for (int i = 1; i < connectionsArray.length; i++) {

            connectionsArray[i] = numbersFromFile[i];

            //System.out.println(pozycjeJaskin[i]);

        }

        //loop to extract connectivities of caves
        ArrayList<Cave> polaczeniaJaskini1 = new ArrayList<Cave>();


        int h = 0;

        for (int i = numberOfConnections; i < numbersFromFile.length; i++) {

            connections[h] = numbersFromFile[i];


            h++;


        }

        for (int n = 0; n < connections.length; n++) {

            queue.add(connections[n]);

        }


        //loop to extract every second element i.e. X1


        double[] partial = new double[numbersFromFile.length >> 1];

        for (int i = 1; i < numbersFromFile.length; i += 2) {
            partial[(i - 1) >> 1] = numbersFromFile[i];
        }

        //loop to extract every second element i.e. Y1


        double[] partial1 = new double[numbersFromFile.length >> 1];

        for (int i = 1; i < numbersFromFile.length; i += 1) {
            partial1[(i - 1) >> 1] = numbersFromFile[i];
        }

        for (int i = 0; i < numberOfCaves; i++) {

            cavePosX[i] = partial[i];
            cavePosY[i] = partial1[i];


        }


        int[] numerJaskini = new int[numberOfCaves];
        for (int i = 0; i < numberOfCaves; i++) {

            numerJaskini[i] = numberOfCaves - numberOfCaves + i + 1;


        }


        //loop to create new cave objects and assign them the positions
        int o = 0;


        ArrayList<Integer> links = new ArrayList<Integer>();


        List<Integer> xxx = new ArrayList<Integer>();
        List<Cave> cave = new ArrayList<Cave>();
        int i = 0;
        while (i < numerJaskini.length) {


            cave.add(new Cave(numerJaskini[i], cavePosX[i], cavePosY[i], links));

            i++;

        }

        //loop to add the connections to caves

        int caveNumber = 0;

        int p = 0;

        for (int b = 0; b < numberOfCaves; b++) {


            while (!queue.isEmpty() && p < noOfConnections) {

                for (int j = 0; j < numberOfCaves; j++) {

                    cave.get(caveNumber).links.add(queue.poll());
                    p++;
                }
                caveNumber++;
            }


        }


        int caveObjectNo = 0;
        int caveFrom = 1;

        int caveTo = 0;

        double cost = 0;


        List<Point> nodes = new ArrayList<Point>();
        Map map = new Map(true);

        //loop to populate the point list

        for (int v = 1; v < numberOfCaves + 1; v++) {
            String snd = String.valueOf(v);
            Point nd = new Point(v, snd);
            nodes.add(nd);
        }


        //loop to extract links, and create a 'map' of the caves

        for (int q = 0; q < noOfConnections; q++) {
            caveFrom = 0;
            for (int a = 0; a < numberOfCaves; a++) {
                if (caveObjectNo < numberOfCaves && caveTo < numberOfCaves) {
                    if (cave.get(caveObjectNo).links.get(a) == 1) {

                        cost = dc.calculateDistance(cave.get(caveFrom).x, cave.get(caveFrom).y, cave.get(caveTo).x, cave.get(caveTo).y);


                        map.addLink(nodes.get(caveFrom), nodes.get(caveTo), cost);

                        caveFrom++;
                    } else if (cave.get(caveObjectNo).links.get(a) == 0) {


                        caveFrom++;
                    }


                }
            }
            caveTo++;
            caveObjectNo++;


        }


        Point start = nodes.get(0);
        Point end = nodes.get(numberOfCaves - 1);


        String finalPath = map.path(start, end);

        Writer writer = new Writer();

        writer.csnFileWriter(finalPath.trim(), caveRouteFile + ".csn");


    }
}

