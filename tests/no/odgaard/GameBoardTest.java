package no.odgaard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test (expected = NegativeArraySizeException.class)
    public void GameBoardNegativeBoardSize() throws Exception {
        GameBoard newGameBoard = new GameBoard(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void GameBoardUnsupportedSizeTooBig() throws Exception {
        GameBoard newGameBoard = new GameBoard(26);
    }

    @Test (expected = IllegalArgumentException.class)
    public void GameBoardUnsupportedSizeTooSmall() throws Exception {
        GameBoard newGameBoard = new GameBoard(4);
    }
}