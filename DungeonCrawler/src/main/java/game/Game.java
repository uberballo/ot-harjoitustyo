/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
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
	private int time;
	private int amountOfCoins;
	private int numberOfRooms;
	private Random random;

	/**
	 * Game-class contains all the games functionality and information.
	 * contains the object Map. On the map: 0 represents the walls. 1
	 * represents the floors 2 represents the player. 3 represents the coins
	 * 4 represents the stairs
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
		this.time = 1000;
		this.amountOfCoins = 2 + rooms;
		this.numberOfRooms = rooms;
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
					if (random.nextInt(1000) < 10 && currentMap[i][j] == 1) {
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

	/**
	 * *
	 * Adds a pair of stairs to the map
	 */
	public void addStairs() {
		while (true) {
			for (int i = 0; i < map.getY(); i++) {
				for (int j = 0; j < map.getX(); j++) {
					if (random.nextInt(1000) < 1 && currentMap[i][j] == 1) {
						currentMap[i][j] = 4;
						return;
					}
				}
			}
		}
	}

	public void setTime(int x) {
		this.time = x;
	}

	public void increaseTime() {
		this.time++;
	}

	public void increaseTime(int moreTime){
		this.time+=moreTime;
	}

	public void decreaseTime() {
		this.time -= 1*numberOfRooms;
	}

	public int[][] getMap() {
		return this.currentMap;
	}

	public int getTime() {
		return this.time;
	}


	public int getRoomNumber(){
		return this.numberOfRooms;
	}

	public void printLocation() {
		System.out.println("Current x, y :" + currentX + " " + currentY);
	}

	public void checkPosition() {
		if (currentMap[currentY][currentX] == 3) {
			this.time += 100;
		} else if (currentMap[currentY][currentX] == 4) {

			//Make a new map here, increase number of rooms by 1 and number of coins by 1
			this.numberOfRooms++;
			this.amountOfCoins++;
			this.map = new Map(map.getY(), map.getX(), this.numberOfRooms, currentY, currentX);
			this.currentMap = map.getMap();
			increaseTime(200);
			addCoins();
			addStairs();
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
					decreaseTime();
				}

		}
	}

	/**
	 * Moves the character Right and calls function decreaseScore();
	 */
	public void moveCharacterRight() {
		if (currentMap[currentY + 1][currentX] != 0) {
			currentY++;
			decreaseTime();
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY - 1][currentX] = 1;
		}
	}

	/**
	 * Moves the character left and calls function decreaseScore();
	 */
	public void moveCharacterLeft() {
		if (currentMap[currentY - 1][currentX] != 0) {
			currentY--;
			decreaseTime();
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY + 1][currentX] = 1;
		}
	}

	/**
	 * Moves the character down and calls function decreaseScore();
	 */
	public void moveCharacterDown() {
		if (currentMap[currentY][currentX + 1] != 0) {
			currentX++;
			decreaseTime();
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX - 1] = 1;
		}
	}

	/**
	 * Moves the character up and calls function decreaseScore();
	 */
	public void moveCharacterUp() {
		if (currentMap[currentY][currentX - 1] != 0) {
			currentX--;
			decreaseTime();
			checkPosition();
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX + 1] = 1;
		}
	}

}
