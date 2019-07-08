package com.practice.blueTeam.Algo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PuzzleTest {
    public Puzzle temp;

    private class TilePos {
        public int x, y;

        public TilePos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[][] tiles;
    private Puzzle.TilePos blank;
    @Before
    public void setUp(){
        temp = new Puzzle();
    }

    @Test
    public void move() {
        Puzzle buf = new Puzzle(temp);
        buf.move(buf.whereIs(16));
        assertEquals(buf, temp);
    }

    @Test
    public void shuffle() {
        Puzzle buf = new Puzzle(temp);
        buf.shuffle(3);
        assertNotSame(buf, temp);
    }

    @Test
    public void isVaildMove(){
        assertTrue(temp.isValidMove(temp.whereIs(14)));
    }
    @Test
    public void isNotValidMove(){
        assertFalse(temp.isValidMove(temp.whereIs(-6)));
        assertFalse(temp.isValidMove(temp.whereIs(25)));
    }
    @Test
    public void puz(){
        assertEquals(Puzzle.SOLVED, new Puzzle());
    }
    @Test
    public void  estimateError(){
        assertEquals(0, temp.estimateError());
    }
    @Test
    public void getTile(){
        assertEquals(15, temp.getTile(temp.whereIs(15)));
    }
}