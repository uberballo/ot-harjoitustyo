package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

	@Test
	public void saldoVaheneeOikein(){
		kortti.otaRahaa(500);
		assertEquals("saldo: 5.0", kortti.toString());
	}

	@Test
	public void saldoEiVaheneJosEiOleTarpeeksiRahaa(){
		kortti.otaRahaa(10000);
		assertEquals("saldo: 10.0", kortti.toString());
	}

	@Test
	public void palauttaaTrueJosSaldoRiittaa(){
		assertEquals(true,kortti.otaRahaa(500));
	}
	@Test
	public void palauttaaFalseJosSaldoEiRiita(){
		assertEquals(false,kortti.otaRahaa(100000));
	}
	@Test
	public void saldoPalauttaaOikeanSumman(){
		assertEquals(1000,kortti.saldo());
	}
	@Test
	public void rahanLatausToimii(){
		kortti.lataaRahaa(1000);
		assertEquals(2000, kortti.saldo());
	}
}
