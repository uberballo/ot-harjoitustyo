/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
	
	/**
	 * 
	 * 
	 * @param y Height of the map.
	 * @param x Width of the map.
	 * @param rooms Number of rooms.
	 * @param playerStartY Starting position of the player on Y axel. 
	 * @param playerStartX Starting position of the player on X axel. 

	 */
	public Game(int y, int x, int rooms, int playerStartY, int playerStartX) {

		this.map = new Map(y, x, rooms, playerStartY, playerStartX);
		this.currentMap = map.getMap();
		this.currentY = playerStartY;
		this.currentX = playerStartX;
		this.currentMap[playerStartY][playerStartX] = 2;
		this.totalScore = 0;
		this.score = 1000;
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

	public void moveCharacterRight() {
		if (currentMap[currentY + 1][currentX] == 1) {
			currentY++;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY - 1][currentX] = 1;
			decreaseScore();
		}
	}

	public void moveCharacterLeft() {
		if (currentMap[currentY - 1][currentX] == 1) {
			currentY--;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY + 1][currentX] = 1;
			decreaseScore();
		}
	}

	public void moveCharacterDown() {
		if (currentMap[currentY][currentX + 1] == 1) {
			currentX++;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX - 1] = 1;
			decreaseScore();
		}
	}

	public void moveCharacterUp() {
		if (currentMap[currentY][currentX - 1] == 1) {
			currentX--;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX + 1] = 1;
			decreaseScore();
		}
	}

}
