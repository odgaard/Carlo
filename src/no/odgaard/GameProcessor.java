package no.odgaard;

import java.util.ArrayList;

class GameProcessor {

    private ArrayList<Game> activeGames;

    GameProcessor() {
        activeGames = new ArrayList<>();
    }

    void startGame(Game game) {
        activeGames.add(game);
    }

    void createNewGame(int boardSize, Handicap handicap, TimeSettings timeSettings) {
        Game newGame = new Game(boardSize, handicap, timeSettings);
        startGame(newGame);
    }

    Game[] getActiveGames() {
        Game[] gameArray = new Game[activeGames.size()];
        return activeGames.toArray(gameArray);
    }

    void pauseGame(Game game) {
        game.pauseGame();
    }

    void resumeGame(Game game) {
        game.resumeGame();
    }

    void endGame(Game game) {
        game.endGame();
    }
}
