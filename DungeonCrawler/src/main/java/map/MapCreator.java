/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kasper
 */
public class MapCreator {

	private int x;
	private int y;
	private int[][] map;
	private int numberOfRooms;
	private Random random = new Random();
	private Room lastRoom;
	private Room[] rooms;

	/**
	 * Produces rooms randomly. After the first room is created, we randomly add
	 * a corridor to the last made room. Repeat that process until we have
	 * correct amount of rooms. 1 represents floor and 0 represents wall.
	 *
	 *
	 * @param y Height of the map.
	 * @param x Width of the map.
	 * @param numberOfRooms Number of rooms.
	 * @param playerStartY Location of the first room on the Y axel.
	 * @param playerStartX Location of the first room on the X axel.
	 */
	public MapCreator(int y, int x, int numberOfRooms, int playerStartY, int playerStartX) {
		this.numberOfRooms = numberOfRooms;

		this.x = x;
		this.y = y;
		//Initializes the map
		this.map = new int[y + 1][x + 1];
		for (int i = 0; i < y; i++) {
			for (int i2 = 0; i2 < x; i2++) {
				map[i][i2] = 0;
			}
		}
		//We make the first starting room and place it to the lastRoom variable to save space.
		this.lastRoom = createRoom(playerStartY, playerStartX, 4, 4);
		rooms = new Room[numberOfRooms + 1];
		rooms[0] = lastRoom;
		//We add the first room to the map.
		for (int i = lastRoom.y1; i < lastRoom.y2; i++) {
			for (int i2 = lastRoom.x1; i2 < lastRoom.y2; i2++) {
				map[i][i2] = 1;
			}
		}
		produceRandomRooms();

	}

	public int[][] getMap() {
		return this.map;
	}

	public void print() {
		for (int i = 0; i < y; i++) {
			for (int i2 = 0; i2 < x; i2++) {
				System.out.print(map[i][i2]);
			}
			System.out.println("");

		}
	}

	/**
	 * *
	 * Produces rooms randomly, until we have the correct amount of rooms.
	 */
	public void produceRandomRooms() {
		int now = 1;
		while (now < numberOfRooms) {

			int y = random.nextInt(this.y);
			int x = random.nextInt(this.x);
			int y1 = random.nextInt(this.y - y) + 1;
			int x1 = random.nextInt(this.y - y) + 1;

			if (y1 > 10) {
				y1 = 10;
			}
			if (x1 > 10) {
				x1 = 10;
			}
			Room newRoom = createRoom(y, x, x1, x1);

			if (addRoom(newRoom)) {
				rooms[now] = newRoom;
				now++;
			}
		}
	}

	/**
	 * *
	 * produces a new Room object.
	 *
	 * @param y Starting location of the room on Y axel.
	 * @param x Starting location of the room on Y axel.
	 * @param height rooms height.
	 * @param width rooms width.
	 *
	 * @return New room object.
	 */
	public Room createRoom(int y, int x, int height, int width) {
		return new Room(y, x, height, width);
	}

	/**
	 * *
	 * Tries to add a new room to the arrays "Map" and "Rooms" and connects the
	 * new room with the last room made.
	 *
	 * @param newRoom new room to add.
	 *
	 * @return If the new room doesn't intersect any older rooms, function
	 * returns True, otherwise False.
	 */
	public boolean addRoom(Room newRoom) {
		if (newRoom.y2 <= y && newRoom.x2 <= x) {
			//Check if the room intersects with any of the current rooms
			for (Room room : rooms) {
				if (room != null) {
					if (newRoom.intersect(room)) {
						return false;
					}
				}
			}
			//System.out.println(newRoom);
			//add the room to the map
			for (int i = newRoom.y1; i < newRoom.y2; i++) {
				for (int i2 = newRoom.x1; i2 < newRoom.x2; i2++) {
					map[i][i2] = 1;
				}
			}
			//Build a hallway between the last room and the new room
			if (random.nextInt(2) == 0) {
				createHorizontalTunnel(lastRoom.x1, newRoom.x1, lastRoom.y1);
				createVerticalTunnel(lastRoom.y1, newRoom.y1, newRoom.x1);
			} else {
				createVerticalTunnel(lastRoom.y1, newRoom.y1, lastRoom.x1);
				createHorizontalTunnel(lastRoom.x1, newRoom.x1, newRoom.y1);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * *
	 * Produces a vertical tunnel. Used by the addRoom function. To ensure the
	 * tunnel connects to the rooms and the for-loop works, we use Math.min()
	 * and Math.Max() to go in ascending order.
	 *
	 * @param y1 Last rooms starting position on Y axel.
	 * @param y2 New rooms starting position on Y axel.
	 * @param x New or old rooms starting position on X axel.
	 */
	public void createVerticalTunnel(int y1, int y2, int x) {
		for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
			map[y][x] = 1;
		}
	}

	/**
	 * *
	 * Produces a horizontal tunnel. Used by the addRoom function. To ensure the
	 * tunnel connects to the rooms and the for-loop works, we use Math.min()
	 * and Math.Max() to go in ascending order.
	 *
	 * @param x1 Last rooms starting position on X axel.
	 * @param x2 New rooms starting position on X axel.
	 * @param y New or old rooms starting position on Y axel.
	 */
	public void createHorizontalTunnel(int x1, int x2, int y) {
		for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
			map[y][x] = 1;
		}
	}

}
