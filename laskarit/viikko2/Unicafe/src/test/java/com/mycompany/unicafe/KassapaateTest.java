package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void oikeaMaaraRahaaAlussa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void oikeaMaaraLounaitaMyytyAlussa() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKasvattaaSaldoaJaMaaraa() {
        int vaihtoRaha = paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(0, vaihtoRaha);
    }
    
    @Test
    public void syoMaukkaastiKasvattaaSaldoaJaMaaraa() {
        int vaihtoRaha = paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, vaihtoRaha);
    }
    
    @Test
    public void josEiRahaaEiMuutosta() {
        int vaihtoRaha = paate.syoMaukkaasti(390);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(390, vaihtoRaha);
        
        vaihtoRaha = paate.syoEdullisesti(200);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(200, vaihtoRaha);
    }
    
    @Test
    public void kortillaVoiSyodaEdullisesti() {
        boolean onnistuiko = paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertTrue(onnistuiko);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortillaVoiSyodaMaukkaasti() {
        boolean onnistuiko = paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertTrue(onnistuiko);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortillaEiVoiOstaaJosEiRahaa() {
        Maksukortti toinen = new Maksukortti(390);
        boolean onnistuiko = paate.syoMaukkaasti(toinen);
        assertEquals(390, toinen.saldo());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertFalse(onnistuiko);
        assertEquals(100000, paate.kassassaRahaa());
        
        Maksukortti kolmas = new Maksukortti(0);
        onnistuiko = paate.syoEdullisesti(kolmas);
        assertEquals(0, kolmas.saldo());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertFalse(onnistuiko);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinLataaminenToimii() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void eiVoiLadataNegatiivisesti() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
}
