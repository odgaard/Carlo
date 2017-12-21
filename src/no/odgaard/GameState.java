package no.odgaard;

import java.io.Serializable;
import java.util.HashSet;

class GameState implements Serializable {
    private GameBoard gameBoard;

    GameState(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    GameBoard getGameBoard() {
        return gameBoard;
    }

    boolean placeMove(Move move) {
        if(!gameBoard.isOccupied(move.getPoint())) {
            if(simulateMove(move))
                return true;
        }
        return false;
    }

    void checkLiberties(GameBoard gameBoard, GoPoint p) {
        GameNode gameNode = gameBoard.getGameNode(p);
        StoneGroup stoneGroup = gameNode.getStoneGroup();

        if(stoneGroup.getLiberties() == 1) {
            gameBoard.removeStoneGroup(stoneGroup); //Removes
            calculateLibertiesOfGroup(stoneGroup);
        }
    }

    private void calculateLibertiesOfGroup(StoneGroup stoneGroup) {
        int libertiesOfGroup = 0;
        for (GameNode node: stoneGroup.getStones()) {
            int newLiberties = calculateLibertiesOfNode(node.getPoint());
            node.setLiberties(newLiberties);
            libertiesOfGroup += newLiberties;
        }
        stoneGroup.setLiberties(libertiesOfGroup);
    }

    void checkLibertiesIfValidPoint(GameBoard gameBoard, int x, int y) {
        if (GoPoint.isValidPoint(x, y, gameBoard))
        checkLiberties(gameBoard, new GoPoint(x, y, gameBoard));
    }

    boolean simulateMove(Move move) {
        int x = move.getX();
        int y = move.getY();

        GameNode node = gameBoard.getGameNode(move.getPoint());
        node.editNode(move.getPlayer());

        checkLibertiesIfValidPoint(gameBoard, x + 1, y);
        checkLibertiesIfValidPoint(gameBoard, x - 1, y);
        checkLibertiesIfValidPoint(gameBoard, x, y + 1);
        checkLibertiesIfValidPoint(gameBoard, x, y - 1);

        if (isSelfAtari(move))
            return false;
        //Attach-to-friendly-group(node);
        //Still need to check for ko in game level
        return true;
    }

    boolean isSelfAtari(Move move) {
        if (calculateLibertiesOfNode(move.getPoint()) == 0)
            return true;
        return false;
    }

    private int calculateLibertiesOfNode(GoPoint p) {
        int liberties = 4;
        int x = p.getX();
        int y = p.getY();

        if (gameBoard.isOccupied(new GoPoint(x+1, y, gameBoard))){
            liberties -= 1;
        }
        if (gameBoard.isOccupied(new GoPoint(x-1, y, gameBoard))){
            liberties -= 1;
        }
        if (gameBoard.isOccupied(new GoPoint(x, y+1, gameBoard))){
            liberties -= 1;
        }
        if (gameBoard.isOccupied(new GoPoint(x, y-1, gameBoard))){
            liberties -= 1;
        }
        return liberties;
    }
}
