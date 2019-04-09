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

	int currentY;
	int currentX;

	Map map;
	int[][] currentMap;

	public Game(int y, int x, int rooms, int playerStartY, int playerStartX) {

		this.map = new Map(y, x, rooms);
		this.currentMap = map.getMap();
		this.currentY = playerStartY;
		this.currentX = playerStartX;
		currentMap[playerStartY][playerStartX] = 2;
	}

	public void moveCharacterUp() {
		if (currentMap[currentY + 1][currentX] == 1) {
			currentY++;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY - 1][currentX] = 1;
		}
	}

	public void moveCharacterDown() {
		if (currentMap[currentY - 1][currentX] == 1) {
			currentY--;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY + 1][currentX] = 1;
		}
	}

	public void moveCharacterRight() {
		if (currentMap[currentY][currentX + 1] == 1) {
			currentX++;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX - 1] = 1;
		}
	}

	public void moveCharacterLeft() {
		if (currentMap[currentY][currentX - 1] == 1) {
			currentX--;
			currentMap[currentY][currentX] = 2;
			currentMap[currentY][currentX + 1] = 1;
		}
	}

}
