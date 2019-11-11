package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenOnnistuu() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void ostaminenVahentaaSaldoa() {
        boolean onnistuiko = kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
        assertTrue(onnistuiko);
    }
    
    @Test
    public void saldoEiVaheneJosEiRahaa() {
        boolean onnistuiko = kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
        assertFalse(onnistuiko);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
