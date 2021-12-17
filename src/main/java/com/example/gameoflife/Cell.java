package com.example.gameoflife;

import static com.example.gameoflife.SexualCell.paneGlobal;

public abstract class Cell implements Runnable {
	public static int cellNumbers = 1;
	protected int T_full;
	protected int T_starve;
	protected int eatCount;
	protected int xLocation;
	protected int yLocation;
	protected int number;
	protected Map map = Map.getInstance();
	private boolean alive = true;
	private FoodResource food = null;

	private Thread thread;

	public Cell(int x, int y) {
		number = cellNumbers;
		cellNumbers++;
		T_full = 0;
		T_starve = 5;
		eatCount = 0;
		xLocation = x;
		yLocation = y;
	}

	public int[] getLocation() {
		return new int[]{xLocation, yLocation};
	}

	public void eat() {
		System.out.println("Cell " + this.number + " is eating");
		T_full = 5;
		eatCount++;
	}

	public void move(int x, int y) {
		String ret = String.format("Cell %d is moving from %d,%d to %d,%d", number, xLocation, yLocation, x, y);
		System.out.println(ret);
		Object nextPosition = map.get(x, y);
		Object currentPosition = map.get(xLocation, yLocation);
		//monitor pe pozitia curenta si pozitia dorita
		if (nextPosition instanceof Free) {
			synchronized (currentPosition) {
				synchronized (nextPosition) {
					map.set(x, y, this);
					map.set(xLocation, yLocation, new Free());
				}
			}
			xLocation = x;
			yLocation = y;
			food = null;
		}
	}

	public void moveRandom() {
		int i = 0;
		int j = 0;
		while (i == 0 && j == 0 || (xLocation + i < 0 || yLocation + j < 0 || xLocation + i >= 50
				|| yLocation + j >= 50)) {
			i = (int) Math.floor(Math.random() * 5 - 2);
			j = (int) Math.floor(Math.random() * 5 - 2);
		}
		this.move(xLocation + i, yLocation + j);
	}

	public int getyLocation() {
		return yLocation;
	}

	public int getxLocation() {
		return xLocation;
	}

	public void searchFood() {
		System.out.println("Cell " + this.number + " is searching for food");
		Object find;
		int i, j;
		for (i = -2; i <= 2; i++) {
			for (j = -2; j <= 2; j++) {
				find = map.get(xLocation + i, yLocation + j);
				if (find instanceof FoodResource) {
					food = (FoodResource) find;
					synchronized (food) {//synchronized doar cand ia mancarea
						food.eat();
						if (food.availableFood() == 0) {
							map.set(xLocation + i, yLocation + j, new Free());
						}
					}
					this.eat();
					return;
				}
			}
		}
	}

	public abstract void reproduce() throws InterruptedException;

	public void run() {
		while (alive) {
			if (eatCount >= 10)
				try {
					this.reproduce();
				} catch (InterruptedException e) {
					System.out.println("--- Cell " + this.number + " error on reproducing ---");
					e.printStackTrace();
				}
			if (T_full == 0)
				this.searchFood();
			this.moveRandom();

			if (T_full > 0)
				T_full--;
			else if (T_starve > 0)
				T_starve--;
			else {
				this.kill();
				return;
			}
		}
	}

	protected void start() {
		thread = new Thread(this);
		thread.run();
	}

	protected void kill() {
		System.out.println("Cell " + this.number + " died");
		this.alive = false;
		Thread.currentThread().interrupt();
		map.set(xLocation, yLocation, new Free());
		paneGlobal.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Color.BLACK), xLocation, yLocation);
		return;
	}
}
