package no.odgaard;

class Move {
    private GoPoint point;
    private Player player;

    Move (GoPoint point, Player player) {
        this.point = point;
        this.player = player;
    }

    public int getX() {
        return point.getX();
    }
    public int getY() {
        return point.getY();
    }
    public GoPoint getPoint() {
        return point;
    }

    public int getPlayerNumber() {
        return player.getPlayerNumber();
    }

    public Player getPlayer() {
        return player;
    }
    void setPlayer(Player player) {
        this.player = player;
    }
}
