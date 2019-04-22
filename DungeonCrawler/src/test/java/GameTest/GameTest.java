/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTest;

import game.Game;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author kasper
 */
public class GameTest {

	private Game game;
	int[][] currentMap;
	int y;
	int x;
	int rooms;
	
	public GameTest(){
		y = 80;
		x = 80;
		rooms = 10;
		this.game = new Game(y,x,rooms,y/2,x/2);
	}

	@Test
	public void movementDecreasesScore(){
		assertEquals(game.getSumScore(), 1000);

		game.moveCharacterUp();
		game.moveCharacterDown();
		
		//This might cause problems, as the map is always randomly generated. Thus we only test going up and down.
		assertEquals(game.getSumScore(), 998);
	}
}
