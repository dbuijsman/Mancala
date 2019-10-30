package nl.sogyo.mancala.domain;

public abstract class AbstractBowl{
    int beads;
    AbstractBowl neighbour;
    Player owner;
    AbstractBowl(){
    }
    public int getBeads(){
        return beads;
    }
    public AbstractBowl getNeighbour(){
        return neighbour;
    }
    public Player getOwner(){
        return owner;
    }

    public abstract boolean isSideCurrentPlayerEmpty();
    abstract boolean isMySideEmpty();
    abstract AbstractBowl findOpposite();
    abstract void putBeadsInKalahaCurrentPlayer(int beadsInHand);
    abstract void distribute(int beadsInHand);

    public AbstractBowl getNeighbour(int times) {
        if (times > 0) {
            return neighbour.getNeighbour(times - 1);
        } else {
            return this;
        }
    }
    abstract int[] getTotalScore();
    abstract int[] getTotalScore(int beads);
}
