package no.odgaard;

class Player {
    private int playerNumber;

    Player(int playerNumber) {
        setPlayerNumber(playerNumber);
    }

    private void setPlayerNumber(int playerNumber) {
        validatePlayerNumber(playerNumber);
        this.playerNumber = playerNumber;
    }

    private void validatePlayerNumber(int playerNumber) {
        if (playerNumber < 0) {
            throw new IllegalArgumentException("Player number can't be negative");
        }
        if (playerNumber > 2) {
            throw new IllegalArgumentException("Player number outside of range");
        }
    }

    int getPlayerNumber() {
        return playerNumber;
    }
}
