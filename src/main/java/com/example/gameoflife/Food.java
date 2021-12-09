package com.example.gameoflife;

public class Food {
    static int food;
    static int sexedcell;
    public int x;
    public int y;
    public String name;
    static String asexualcell;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void setFood(int food) {
        Food.food = food;
    }
    //Will be implemented after cell classes
    /* public consume(){
    }*/
}