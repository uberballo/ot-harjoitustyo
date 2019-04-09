/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import map.Map;
import map.MapCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kasper
 */
public class MapCreatorTest {

	Map map;
	int[][] currentMap;
	int y;
	int x;
	int rooms;

	public MapCreatorTest() {
		y = 80;
		x = 80;
		rooms = 10;
		map = new Map(y, x, rooms);
		currentMap = map.getMap();
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void mapCreatorCreatesAMap() {
		assertNotNull(currentMap);
	}

	@Test
	public void mapIsCorrectSize() {
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				assertNotNull(currentMap[i][j]);
			}
		}
	}

	@Test
	public void mapReturnsCorrectValues() {
		assertEquals(map.getX(), x);
		assertEquals(map.getY(), y);

	}

}
