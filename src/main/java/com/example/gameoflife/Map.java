package com.example.gameoflife;

public class Map {
    private final static Map instance = new Map();
    private final Object[][] matrix = new Object[50][50];

    public static Map getInstance() {
        return instance;
    }

    public void set(int x, int y, Object other) {
//		System.out.println("" + x + " " + y);
        matrix[x][y] = other;
    }

    public Object get(int x, int y) {
        if (x >= 0 && y >= 0 && x < 50 & y < 50)
            return matrix[x][y];
        return "error";
    }

    public synchronized void setIsBusy(SexualCell male, SexualCell female) {
        if (!male.getBusy() && !female.getBusy()) {
            male.setIsBusy(true);
            female.setIsBusy(true);
        }
    }
}
