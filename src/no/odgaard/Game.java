package no.odgaard;

import java.util.ArrayList;

class Game {

    private ArrayList<GameState> gameStates;
    private GameState nextGameState;

    private Handicap handicap;
    private TimeSettings timeSettings;
    private int boardsize;

    private boolean isPaused;
    private boolean hasEnded;

    Game (int boardsize, Handicap handicap, TimeSettings timeSettings) {
        this.boardsize = boardsize;
        this.handicap = handicap;
        this.timeSettings = timeSettings;

        isPaused = true;
        hasEnded = false;
    }

    void goToNextGameState() {
        gameStates.add((GameState)DeepCopy.copy(nextGameState));
    }

    void resetNextGameState() {
        nextGameState = (GameState)DeepCopy.copy(getCurrentGameState());
    }

    GameState getCurrentGameState() {
        return gameStates.get(-1);
    }

    void pauseGame() {
        if (!isPaused) {
            isPaused = true;
        }
    }

    void resumeGame() {
        if(isPaused) {
            isPaused = false;
        }
    }

    void endGame() {
        if(!hasEnded) {
            hasEnded = true;
        }
    }

    boolean isPaused() {
        return isPaused;
    }

    boolean hasEnded() {
        return hasEnded;
    }
}
