/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTest;

import game.Game;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
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
	
	@Before
	public void setUp() {
		y = 80;
		x = 80;
		rooms = 10;
		this.game = new Game(y, x, rooms, y / 2, x / 2);
	}
	
	

	@Test
	public void movementDecreasesTime() {
		assertEquals(game.getTime(), 1000);

		game.moveCharacterUp();
		game.moveCharacterDown();

		Assert.assertNotEquals(game.getTime(),1000);
	}

	@Test
	public void characterMovesOnTheMap(){
		int locationBeforeMovementX = game.getCurrentX();
		int locationBeforeMovementY = game.getCurrentY();
		game.moveCharacterDown();
		game.moveCharacterRight();
		Assert.assertNotEquals(game.getCurrentX(), locationBeforeMovementX);
		Assert.assertNotEquals(game.getCurrentY(), locationBeforeMovementY);
	}

	@Test
	public void characterMovesOnTheMapSecond(){
		int locationBeforeMovementX = game.getCurrentX();
		int locationBeforeMovementY = game.getCurrentY();
		game.moveCharacterDown();
		game.moveCharacterRight();
		game.moveCharacterUp();
		game.moveCharacterLeft();
		Assert.assertEquals(game.getCurrentX(), locationBeforeMovementX);
		Assert.assertEquals(game.getCurrentY(), locationBeforeMovementY);
	}

	@Test
	public void gameIsNotOverWhenJustStarted(){
		Assert.assertEquals(game.checkIfGameIsOver(), false);
	}
}
