/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import map.Map;

/**
 *
 * @author kasper
 */
public class Game {

	private int currentY;
	private int currentX;
	private Map map;
	private int[][] currentMap;
	private int totalScore;
	private int score;
	private int amountOfCoins;
	private Random random;

	/**
	 * Game-class contains all the games functionality and information. contains
	 * the object Map. On the map, 0 represents the walls, 1 represents the
	 * floors and 2 represents the player.
	 *
	 * @param y Height of the map.
	 * @param x Width of the map.
	 * @param rooms Number of rooms.
	 * @param playerStartY Starting position of the player on Y axel.
	 * @param playerStartX Starting position of the player on X axel. *
	 */
	public Game(int y, int x, int rooms, int playerStartY, int playerStartX) {

		this.map = new Map(y, x, rooms, playerStartY, playerStartX);
		this.currentMap = map.getMap();
		this.currentY = playerStartY;
		this.currentX = playerStartX;
		this.currentMap[playerStartY][playerStartX] = 2;
		this.totalScore = 0;
		this.score = 1000;
		this.amountOfCoins = 5;
		this.random = new Random();

		addCoins();
		addStairs();

	}

	/**
	 * *
	 * Adds coins to the map until we have correct amount of coins.
	 */
	public void addCoins() {
		int currentAmountOfCoins = 0;

		while (true) {
			for (int i = 0; i < map.getY(); i++) {
				for (int j = 0; j < map.getX(); j++) {
					if (random.nextDouble() < 0.05 && currentMap[i][j] == 1) {
						currentMap[i][j] = 3;
						currentAmountOfCoins++;
					}
					if (currentAmountOfCoins == amountOfCoins) {
						return;
					}
				}
			}
		}
	}

	public void addStairs() {
		while (true) {
			for (int i = 0; i < map.getY(); i++) {
				for (int j = 0; j < map.getX(); j++) {
					if (random.nextDouble() < 0.01 && currentMap[i][j] == 1) {
						currentMap[i][j] = 4;
						return;
					}
				}
			}
		}
	}

	public void setScore(int x) {
		this.score = x;
	}

	public void increaseScore() {
		this.score++;
	}

	public void decreaseScore() {
		this.score--;
	}

	public int[][] getMap() {
		return this.currentMap;
	}

	public int getScore() {
		return this.score;
	}

	public int getTotalScore() {
		return this.totalScore;
	}

	public int getSumScore() {
		return this.totalScore + this.score;
	}

	public void printLocation() {
		System.out.println("Current x, y :" + currentX + " " + currentY);
	}

	public void checkPosition() {
		if (currentMap[currentY][currentX] == 3) {
			this.score += 100;
		}
	}

	//Currently not working. 
	public void moveCharacter(String direction) {
		switch (direction) {
			case ("W"):
				if (currentMap[currentY][currentX - 1] == 1) {
					currentX--;
					currentMap[currentY][currentX] = 2;
					currentMap[currentY][currentX + 1] = 1;
					decreaseScore();
				}

		}
	}

	/**
	 * Moves the character Right and calls function decreaseScore();
	 */
	public void moveCharacterRight() {
		if (currentMap[currentY + 1][currentX] != 0) {
			currentY++;
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY - 1][currentX] = 1;
			decreaseScore();
		}
	}

	/**
	 * Moves the character left and calls function decreaseScore();
	 */
	public void moveCharacterLeft() {
		if (currentMap[currentY - 1][currentX] != 0) {
			currentY--;
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY + 1][currentX] = 1;
			decreaseScore();
		}
	}

	/**
	 * Moves the character down and calls function decreaseScore();
	 */
	public void moveCharacterDown() {
		if (currentMap[currentY][currentX + 1] != 0) {
			currentX++;
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX - 1] = 1;
			decreaseScore();
		}
	}

	/**
	 * Moves the character up and calls function decreaseScore();
	 */
	public void moveCharacterUp() {
		if (currentMap[currentY][currentX - 1] != 0) {
			currentX--;
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX + 1] = 1;
			decreaseScore();
		}
	}

}
