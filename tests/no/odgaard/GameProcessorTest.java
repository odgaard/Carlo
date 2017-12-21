package no.odgaard;

import org.junit.*;

import static org.junit.Assert.*;

public class GameProcessorTest {

    private static GameProcessor gameProcessor;

    private int boardSize;
    private Handicap handicap;
    private TimeSettings timeSettings;
    private Game game1;

    @BeforeClass
    public static void setUpOnce() {
        gameProcessor = new GameProcessor();
    }

    @Before
    public void setUp() {
        boardSize = 9;
        game1 = new Game(boardSize, handicap, timeSettings);
        handicap = new Handicap(0);
        timeSettings = new TimeSettings();
    }

    @After
    public void tearDown() {
        boardSize = 0;
        game1 = null;
        handicap = null;
        timeSettings = null;
    }

    @Test
    public void createNewGame(){
        gameProcessor.createNewGame(boardSize, handicap, timeSettings);
        assertNotEquals(null, gameProcessor.getActiveGames());
    }

    @Test
    public void endGame() {
        assertFalse(game1.hasEnded());

        gameProcessor.endGame(game1);
        assertTrue(game1.hasEnded());
    }

    @Test
    public void pauseGame() {
        game1.resumeGame();
        assertFalse(game1.isPaused());

        gameProcessor.pauseGame(game1);
        assertTrue(game1.isPaused());
    }

    @Test public void resumeGame() {
        assertTrue(game1.isPaused());

        gameProcessor.resumeGame(game1);
        assertFalse(game1.isPaused());
    }
}