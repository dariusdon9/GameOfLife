package com.example.gameoflife;

public class AsexualCell extends Cell {
	String type;
	String name;

	public AsexualCell(int x, int y) {
		super(x, y);
		this.type = type;
		SexualCell.paneGlobal.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Color.BLUE), x, y);
		System.out.println("Asexuate Cell " + this.number + " is created");
	}

	@Override
	public void reproduce() throws InterruptedException {
		System.out.println("Asexuate Cell " + this.number + " is reproducing");
		Object find;
		this.name = name;
		int i, j;
		int children = 0;
		AsexualCell child;
		for (i = -2; i <= 2; i++)
			for (j = -2; j <= 2; j++) {
				find = map.get(xLocation + i, yLocation + j);
				if (find instanceof Free) {
					synchronized (find) {
						//if (children < 2) 
						{
							children++;
							child = new AsexualCell(xLocation + i, yLocation + j);
							map.set(xLocation + i, yLocation + j, child);

							//child.start();
						}
					}
					child.start();
				}
				if (children == 2) {
					break;
				}
			}
		if (children == 0) {
			throw new IllegalStateException("Not enough space for both childrens");
		}
		this.kill();
	}

}
