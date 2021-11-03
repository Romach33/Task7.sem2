package com.dima.GraphsAnalyzingProject;

import java.util.ArrayList;

public class CycleSaver {
    public static ArrayList<Graph> localGraphSaver = new ArrayList<Graph>();
    public static ArrayList<Integer> valuesSaved = new ArrayList<>();
    public static void initSaver() {
        localGraphSaver = new ArrayList<>();
    }
    static boolean[][] localAdj;
    public static boolean newCycle = false;

    static Graph graphLocal;
    static boolean used[] = new boolean[1000000];

    public static void analyzeGraph(Graph graph) {
        localAdj = graph.getAdj();
            valuesSaved.add(0);
            dfs(localAdj[0], -1, 0);
            valuesSaved.remove(valuesSaved.size()-1);
            valuesSaved.remove(valuesSaved.size()-1);
        for (int i = 0; i < valuesSaved.size(); i++) {
            System.out.println("#"+i + " = "+valuesSaved.get(i));
            for (int j = i+1; j < valuesSaved.size(); j++) {
                if(valuesSaved.get(i).equals(valuesSaved.get(j))) {
                    System.out.println("Cycle between a = " + i + " and b = " + j);
                    Graph newest = new AdjMatrixGraph();
                    for (int k = i; k < j; k++) {
                        newest.addAdge(valuesSaved.get(k), valuesSaved.get(k+1));

                        //System.out.println("added");
                    }
                    localGraphSaver.add(newest);
                }
            }
        }
    }

    private static void dfs(boolean[] ways, Integer parent, int self) {
        for (int i = 0; i < ways.length; i++) {
            if(ways[i]) {
                if(i!=parent) {
                    boolean checkerOfCycle = false;
                    for (int j = 0; j < valuesSaved.size(); j++) {
                        if(i==valuesSaved.get(j)) {
                            checkerOfCycle = true;
                        }
                    }
                    if(checkerOfCycle) {
                        valuesSaved.add(i);
                    } else {
                        valuesSaved.add(i);
                        dfs(localAdj[i], self, i );
                    }
                }
            }
        }
    }
}