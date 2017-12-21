package no.odgaard;

import java.io.Serializable;

class GameBoard implements Serializable {

    private GameNode[][] gameNodeArray;
    private int boardSize;

    public static final int MAX_BOARD_SIZE = 25;
    public static final int MIN_BOARD_SIZE = 5;

    GameBoard(int boardSize) {
        validateBoardSize(boardSize);
        this.boardSize = boardSize;
        this.gameNodeArray = new GameNode[boardSize][boardSize];
        populateEmptyBoard();
    }

    private void validateBoardSize(int boardSize){
        if (boardSize < 0) {
            throw new NegativeArraySizeException("Negative board size");
        }
        if (boardSize > MAX_BOARD_SIZE || boardSize < MIN_BOARD_SIZE) {
            throw new IllegalArgumentException("Unsupported board size");
        }
    }

    private void populateEmptyBoard() {
        int liberties;
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                liberties = 4;
                if(x == 0 || x == boardSize-1)
                    liberties -= 1;
                if(y == 0 || y == boardSize-1)
                    liberties -= 1;
                gameNodeArray[y][x] = new GameNode(new Move(new GoPoint(x, y, this), new Player(0)), liberties);
            }
        }
    }

    public void removeStoneGroup(StoneGroup stoneGroup) {

    }

    GameNode getGameNode(GoPoint p) {
        return gameNodeArray[p.getY()][p.getX()];
    }
    int getBoardSize() { return boardSize; }

    boolean isOccupied(GoPoint p) {
        return getGameNode(p).getPlayerNumber() != 0;
    }
}