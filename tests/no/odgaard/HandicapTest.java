package no.odgaard;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandicapTest {

    @Test (expected = IllegalArgumentException.class)
    public void HandicapNegativeHandicapStones() throws Exception {
        int handicapStones = -1;
        Handicap handicap = new Handicap(handicapStones);
    }

    @Test (expected = IllegalArgumentException.class)
    public void HandicapUnsupportedHandicapStones() throws Exception {
        int handicapStones = 10;
        Handicap handicap = new Handicap(handicapStones);
    }

    @Test
    public void compareToCorrectComparision() throws Exception {
        int handicapStones1 = 0;
        int handicapStones2 = 1;

        Handicap handicap1 = new Handicap(handicapStones1);
        Handicap handicap2 = new Handicap(handicapStones2);

        assertEquals(-1, handicap1.compareTo(handicap2));
    }
}