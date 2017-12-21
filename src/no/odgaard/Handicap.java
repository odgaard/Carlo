package no.odgaard;

class Handicap implements Comparable<Handicap>{
    private int handicapStones;

    Handicap (int handicapStones) {
        validateHandicap(handicapStones);
        this.handicapStones = handicapStones;
    }

    private void validateHandicap(int handicapStones) {
        if (handicapStones < 0) {
            throw new IllegalArgumentException("No negative handicap stones");
        }
        if (handicapStones > 9) {
            throw new IllegalArgumentException("Unsupported handicap stone amount");
        }
    }

    public int getHandicapStones() {
        return handicapStones;
    }

    @Override
    public int compareTo(Handicap other) {
        return Integer.compare(this.handicapStones, other.handicapStones);
    }
}
