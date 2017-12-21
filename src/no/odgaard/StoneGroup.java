package no.odgaard;

import java.util.HashSet;

class StoneGroup {

    private HashSet<GameNode> stones;
    private int liberties;

    StoneGroup (GameNode firstNode) {
        stones = new HashSet<>();
        stones.add(firstNode);
        this.liberties = firstNode.getLiberties();
    }

    boolean contains(GameNode node) {
        return stones.contains(node);
    }

    void addNode(GameNode node) {
        if (!contains(node)) {
            stones.add(node);
        }
    }

    HashSet<GameNode> getStones() { return stones; }



    int getLiberties() {
        return liberties;
    }

    public void setLiberties(int liberties) {
        validateLiberties(liberties);
        this.liberties = liberties;
    }

    private void validateLiberties(int liberties) {
        if (liberties < 0) {
            throw new IllegalArgumentException("Liberties can't be negative");
        }
    }
}
