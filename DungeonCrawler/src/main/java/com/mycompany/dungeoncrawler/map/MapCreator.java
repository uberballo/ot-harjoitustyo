/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dungeoncrawler.map;

/**
 *
 * @author kasper
 */

import java.util.Random;



public class MapCreator {

	private int x;
	private int y;
	private int[][] map;
	private Random random = new Random();
	private Room LastRoom;
	private Room[] rooms;
	
	private int numberOfRooms = 6;

	public MapCreator(int y, int x) {

		for (int a = 0; a < 100; a++) {
		}

		this.x = x;
		this.y = y;
		//Initializes the map
		this.map = new int[y + 1][x + 1];
		for (int i = 0; i < y; i++) {
			for (int i2 = 0; i2 < x; i2++) {
				map[i][i2] = 0;
			}
		}
		//We make the first starting room and place it to the LastRoom variable to save space.
		this.LastRoom = createRoom((x / 2 - 1), (y / 2 - 1), 4, 4);
		rooms = new Room[numberOfRooms + 1];
		rooms[0] = LastRoom;
		//We add the first room to the map.
		for (int i = LastRoom.y1; i < LastRoom.y2; i++) {
			for (int i2 = LastRoom.x1; i2 < LastRoom.y2; i2++) {
				map[i][i2] = 1;
			}
		}
		produceRandomRooms();

	}

	public void print() {
		for (int i = 0; i < y; i++) {
			for (int i2 = 0; i2 < x; i2++) {
				System.out.print(map[i][i2]);
			}
			System.out.println("");

		}
	}

	public void produceRandomRooms() {
		int now = 1;
		while (now < numberOfRooms) {

			int y = random.nextInt(this.y);
			int x = random.nextInt(this.x);
			int y1 = random.nextInt(this.y - y) + 1;
			int x1 = random.nextInt(this.y - y) + 1;

			Room newRoom = createRoom(y, x, x1, x1);

			if (addRoom(newRoom)) {
				rooms[now] = newRoom;
				now++;
			}
		}
	}

	public Room createRoom(int y, int x, int height, int widht) {
		return new Room(y, x, height, widht);
	}

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
			System.out.println(newRoom);
			//add the room to the map
			for (int i = newRoom.y1; i < newRoom.y2; i++) {
				for (int i2 = newRoom.x1; i2 < newRoom.y2; i2++) {
					map[i][i2] = 1;
				}
			}
			//Build a hallway between the last room and the new room
			if (random.nextInt(1) == 1) {
				createHorizontalTunnel(LastRoom.x1, newRoom.x1, LastRoom.y1);
				createVerticalTunnel(LastRoom.y1, newRoom.y1, newRoom.x1);
			} else {
				createVerticalTunnel(LastRoom.y1, newRoom.y1, LastRoom.x1);
				createHorizontalTunnel(LastRoom.x1, newRoom.x1, newRoom.y1);
			}
			return true;
		} else {
			return false;
		}
	}

	public void createVerticalTunnel(int y1, int y2, int x) {
		for (int y = Math.min(y1, y2); y < Math.max(y1, y2); y++) {
			map[y][x] = 1;
		}
	}

	public void createHorizontalTunnel(int x1, int x2, int y) {
		for (int x = Math.min(x1, x2); x < Math.max(x1, x2); x++) {
			map[y][x] = 1;
		}
	}

}
