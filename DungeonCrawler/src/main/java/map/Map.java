/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

/**
 *
 * @author kasper
 */
public class Map {

	private int[][] map;
	private int y;
	private int x;

	/**
	 * *
	 * Map Object. Contains integer array[][] which contains the maps values. 0
	 * represents the walls, 1 represents the floors and 2 represents the
	 * characters.
	 *
	 * @param y Height of the map.
	 * @param x Width of the map.
	 * @param rooms Number of rooms.
	 * @param playerStartY Starting position of the player on Y axel.
	 * @param playerStartX Starting position of the player on X axel.
	 */
	public Map(int y, int x, int rooms, int playerStartY, int playerStartX) {
		this.y = y;
		this.x = x;
		MapCreator mapCreator = new MapCreator(y, x, rooms, playerStartY, playerStartX);
		this.map = mapCreator.getMap();
	}

	/**
	 * *
	 *
	 * @return returns integer array[][] which contains maps values.
	 */
	public int[][] getMap() {
		return this.map;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
