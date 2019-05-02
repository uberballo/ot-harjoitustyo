/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kasper
 */
public class HighScoireDaoTest {

	HighScoreDao dao;
	String databaseName;
	
	@Before
	public void setUp() throws Exception{
		databaseName = "test";
		dao = new HighScoreDao(databaseName);
	}

	@Test
	public void scoresCanBeInsertedAndReturned(){
		dao.insert("temp", 10);
		ArrayList<Integer> list = dao.getScores();
		int firstValue = list.get(0);
		Assert.assertEquals(10, firstValue);
	}

	@After
	public void tearDown(){
		File file = new File(databaseName+".db");
		file.delete();
	}
	
}
