package no.odgaard;

class GoPoint extends Point {

    private int boardSize;

    GoPoint (int x, int y, GameBoard gameBoard) {
        super(x, y);
        setCoordinates(x, y);
        boardSize = gameBoard.getBoardSize();
    }

    private void setCoordinates(int x, int y) {
        validatePoint(x, y);
    }

    public static boolean isValidPoint(int x, int y, GameBoard gameBoard) {
        if (x < 0 || y < 0)
            return false;
        if (x > gameBoard.getBoardSize() - 1 || y > gameBoard.getBoardSize() - 1) {
            return false;
        }
        return true;
    }

    private void validatePoint(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Negative point");
        if (x > this.boardSize - 1 || y > this.boardSize - 1) {
            throw new IllegalArgumentException("Unsupported point");
        }
    }
}
