package no.odgaard;

import java.io.Serializable;

class GameNode implements Serializable{

    private Move move;
    private StoneGroup stoneGroup;
    private int liberties;


    GameNode(Move move, int liberties) {
        this.move = move;
        setLiberties(liberties);
    }

    void setLiberties(int liberties) {
        validateLiberties(liberties);
        this.liberties = liberties;
    }

    private void validateLiberties(int liberties) {
        if (liberties < 0) {
            throw new IllegalArgumentException("Liberties can't be negative");
        }
        if (liberties > 4) {
            throw new IllegalArgumentException("Liberties can't be higher than 4");
        }
    }

    void editNode(Player player) {
        move.setPlayer(player);
    }

    void addToStoneGroup(StoneGroup stoneGroup) {
        stoneGroup.addNode(this);
        this.stoneGroup = stoneGroup;
    }

    StoneGroup getStoneGroup() {
        return stoneGroup;
    }

    int getLiberties() {
        return liberties;
    }
    int getPlayerNumber() {
        return move.getPlayerNumber();
    }

    GoPoint getPoint() { return move.getPoint(); }
    int getX() { return move.getX(); }
    int getY() { return move.getY(); }
}
