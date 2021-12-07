package com.example.gameoflife;

import javafx.scene.layout.GridPane;

import java.util.Random;

import static java.lang.Math.random;
import static javafx.scene.paint.Color.*;

public class Cell extends Thread{
    public int x;
    public int y;
    private int T_full = 0;
    private int T_starve = 5;
    public int food;
    private Thread thread;
    public String type;
    public GridPane pane;
    public int timesFeed = 0;
    public boolean alive = true;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell(int x, int y, String type, GridPane pane) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.pane = pane;
    }

    public void start() {
//        pane.add(new javafx.scene.shape.Rectangle(10, 10, RED), this.x - 1, this.y - 1);
        thread = new Thread(this);
        thread.run();
    }

    public void run() {
        System.out.println("Thread runs");
        while(alive) {
            System.out.println("T_full" + T_full + " T_starve " + T_starve);
            // TODO implement moving and possibly searchFood method
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (T_full > 0)
                T_full--;
            else if (T_starve > 0)
                T_starve--;
            else {
                this.die();
                return;
            }
        }
    }

    public String getType(){
        return type;
    }

    public void setType(){
        this.type = type;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public  void setX(int x) {
        this.x = x;
    }

    public  void setY(int y) {
        this.y = y;
    }

    public int getTimesFeed() {
        return this.timesFeed;
    }

    public boolean feed() {
        if (Food.food > 0) {
            this.timesFeed++;
            Food.food--;
            return true;        //fed successfully
        } else
            return false;
    }

    public boolean isFed() {
        return this.timesFeed >= 10;
    }

    public void die() {
        this.alive = false;
        System.out.println("I died");
        Food.food = (int) (Food.food + (random() * ((5 - 1) + 1)) + 1);
        Thread.currentThread().interrupt();
        this.transformIntoFood();
    }

    public void transformIntoFood() {
        Random random = new Random();
        int randomPosition;
        int randomFoodUnits = 4;
        Food.food += randomFoodUnits;
        pane.add(new javafx.scene.shape.Rectangle(10, 10, YELLOW), this.x, this.y);

        while(randomFoodUnits != 0) {
            randomPosition = random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            System.out.println(randomFoodUnits + " + " + randomPosition);
            pane.add(new javafx.scene.shape.Rectangle(10, 10, YELLOW), this.x + randomPosition, this.y + randomPosition);
            randomFoodUnits--;
        }
    }
}
