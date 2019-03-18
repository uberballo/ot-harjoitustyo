/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
	
	Kassapaate kassa;
	Maksukortti kortti;
	
	@Before
	public void setUp() {
		kassa = new Kassapaate();
		kortti = new Maksukortti(1000);
	}
	
	@Test
	public void kassaPalauttaaOikeanMaaranRahaa(){
		assertEquals(100000,kassa.kassassaRahaa());
	}

	@Test
	public void edullisiaLounaitaMyytyAlussaNolla(){
		assertEquals(0, kassa.edullisiaLounaitaMyyty());
	}

	@Test
	public void maukkaitaLounaitaMyytyAlussaNolla(){
		assertEquals(0, kassa.maukkaitaLounaitaMyyty());
	}

	@Test
	public void kassaPalauttaaOikeanSummanJaLisaaRahatKassaan(){
		assertEquals(260,kassa.syoEdullisesti(500));
		assertEquals(100240, kassa.kassassaRahaa());
	}

	@Test
	public void myytyjenLounaidenMaaraKasvaa(){
		kassa.syoEdullisesti(240);
		assertEquals(1, kassa.edullisiaLounaitaMyyty());
	}
	@Test
	public void rahatPalautetaanJosNeEiRiitaLounaaseen(){
		assertEquals(200, kassa.syoEdullisesti(200));
		assertEquals(100000, kassa.kassassaRahaa());
		assertEquals(0, kassa.edullisiaLounaitaMyyty());
	}

	@Test
	public void kassaMyyJosKortillaOnTarpeeksiRahaa(){
		assertEquals(true, kassa.syoMaukkaasti(kortti));
		assertEquals(600, kortti.saldo());
	}

	@Test
	public void KortillaOstettuLounasKasvattaaMyytyjenLounaidenMaaraa(){
		kassa.syoMaukkaasti(kortti);
		assertEquals(1, kassa.maukkaitaLounaitaMyyty());
	}

	@Test
	public void tapahtumaaEiLasketaJosKortillaEiOleTarpeeksiRahaa(){
		Maksukortti tempKortti = new Maksukortti(300);
		assertEquals(false, kassa.syoMaukkaasti(tempKortti));
		assertEquals(0, kassa.maukkaitaLounaitaMyyty());
		assertEquals(300, tempKortti.saldo());
	}

	@Test
	public void kassanRahamaaraEiMuutuJosOstetaanKortilla(){
		kassa.syoMaukkaasti(kortti);
		assertEquals(100000, kassa.kassassaRahaa());
	}

	@Test
	public void kassanRahamaaraKasvaaKunLadataaMaksukorttia(){
		kassa.lataaRahaaKortille(kortti, 1000);
		assertEquals(101000, kassa.kassassaRahaa());
	}

	@Test
	public void korttinSaldoEiMuutuJosLadataanNegatiivinenMaara(){
		kassa.lataaRahaaKortille(kortti, -1000);
		assertEquals(1000, kortti.saldo());
	}	
	@Test
	public void kassaPalauttaaOikeanSummanKunOstetaanMaukasLounas(){
		assertEquals(100,kassa.syoMaukkaasti(500));
		assertEquals(100400, kassa.kassassaRahaa());
	}

	@Test
	public void maukkaudenLounaidenMaaraKasvaaKunOstetaan(){
		kassa.syoMaukkaasti(400);
		assertEquals(1, kassa.maukkaitaLounaitaMyyty());
	}
	@Test
	public void rahatPalautetaanJosNeEivatRiitaMaukkaaseen(){
		assertEquals(200, kassa.syoMaukkaasti(200));
		assertEquals(100000, kassa.kassassaRahaa());
		assertEquals(0, kassa.maukkaitaLounaitaMyyty());
	}

	@Test
	public void kassaMyyJosKortillaOnTarpeeksiRahaaEdulliseenLounaaseen(){
		assertEquals(true, kassa.syoEdullisesti(kortti));
		assertEquals(760, kortti.saldo());
	}

	@Test
	public void KortillaOstettuLounasKasvattaaMyytyjenEdullistenLounaidenMaaraa(){
		kassa.syoEdullisesti(kortti);
		assertEquals(1, kassa.edullisiaLounaitaMyyty());
	}

	@Test
	public void edullisenLounaanOstotapahtumaaEiLasketaJosKortillaEiOleTarpeeksiRahaa(){
		Maksukortti tempKortti = new Maksukortti(200);
		assertEquals(false, kassa.syoEdullisesti(tempKortti));
		assertEquals(0, kassa.edullisiaLounaitaMyyty());
		assertEquals(200, tempKortti.saldo());
	}

}
