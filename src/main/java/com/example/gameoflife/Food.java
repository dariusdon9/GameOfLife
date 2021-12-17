package com.example.gameoflife;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.FileWriter;
import java.io.IOException;

import static javafx.scene.paint.Color.YELLOW;

public class Food {
    static int food;
    static int sexedcell;
    public int x;
    public int y;
    public String name;
    static String asexualcell;
    public Rectangle rectangle = new javafx.scene.shape.Rectangle(10, 10, YELLOW);
    GridPane pane = new GridPane();

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void FoodGetUpdate(Food food, int x, int y, GridPane pane, String name) {
        this.x = x;
        this.y = y;
        this.pane = pane;
        this.name = name;

        food.setX(x);
        food.setY(y);
        pane.add(rectangle, food.getX(), food.getY());
        //implement random food units
        //Food.food =+1;
        String csvFilePath = "src/main/java/com/example/gameoflife/" + name + ".csv";
        try (FileWriter fw = new FileWriter(csvFilePath)) {
            fw.write("Updated food slot");
            fw.write(food.getX() + "," + food.getY() + ",");
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FoodGetDelete(Food food, int x, int y, GridPane pane) {
        this.x = x;
        this.y = y;
        this.pane = pane;
        food.setX(x);
        food.setY(y);
        Food food1 = new Food(food.getX(), food.getY());
        //when cell eat, food dissapear
        pane.getChildren().remove(food1);
        Food.food = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void setFood(int food) {
        Food.food = food;
    }
    //Will be implemented after cell classes
    /* public consume(){
    }*/
}