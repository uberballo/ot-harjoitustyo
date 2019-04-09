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
public class Room {

	public int x1;
	public int x2;
	public int y1;
	public int y2;

	public Room(int y, int x, int height, int width) {
		this.y1 = y;
		this.x1 = x;
		this.y2 = y + height;
		this.x2 = x + width;
	}

	public boolean intersect(Room room) {
		return ((x1 <= room.x2 && x2 >= room.x1) && (y1 <= room.y2 && y2 >= room.y1));
	}

	public String toString() {
		return "y1 = " + y1 + " x1 = " + x1 + " y2 = " + y2 + " x2 = " + x2;
	}

}
