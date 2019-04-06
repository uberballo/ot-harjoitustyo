/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.Map;

/**
 *
 * @author kasper
 */
public class Map {

	private int[][] map;
	private int y;
	private int x;
	
	public Map(int y, int x, int rooms){
		this.y = y;
		this.x = x;
		MapCreator mapCreator = new MapCreator(y, x, rooms);
		this.map = mapCreator.getMap();
	}

	public int[][] getMap(){
		return this.map;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
	
}
