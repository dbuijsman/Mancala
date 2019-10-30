package nl.sogyo.mancala.domain;

public class Bowl extends AbstractBowl {
    public Bowl (){
        this(6,4);
    }
    public Bowl(int numberOfBowlsOnOneSide, int numberOfStartingBeads){
        this.beads = numberOfStartingBeads;
        this.owner = new Player();
        if(numberOfBowlsOnOneSide>1) {
            this.neighbour = new Bowl(numberOfBowlsOnOneSide - 1, numberOfBowlsOnOneSide, numberOfStartingBeads, owner, this);
        } else {
            this.neighbour = new Kalaha(owner, numberOfBowlsOnOneSide, numberOfStartingBeads, this);
        }
    }
    Bowl(int counter, int numberofBowlsOnOneSide, int numberOfStartingBeads, Player owner, Bowl bowl){
        this.beads = numberOfStartingBeads;
        this.owner = owner;
        if(counter>1) {
            this.neighbour = new Bowl(counter - 1, numberofBowlsOnOneSide, numberOfStartingBeads, owner, bowl);
        } else {
            this.neighbour = new Kalaha(owner, numberofBowlsOnOneSide, numberOfStartingBeads, bowl);
        }
    }

    public void play(){
        neighbour.distribute(beads);
        empty();
    }

    void distribute(int beadsInHand){
        this.beads++;
        beadsInHand--;
        if (beadsInHand > 0){
            neighbour.distribute(beadsInHand);
        } else {
            lastBead();
        }
    }
    private void empty(){
        this.beads = 0;
    }
    void lastBead(){
        if(beads==1 & owner.getIsMyTurn()){
            putBeadsInKalahaCurrentPlayer(beads);
            empty();
            Bowl opposite = (Bowl) findOpposite();
            opposite.putBeadsInKalahaCurrentPlayer(opposite.getBeads());
            opposite.empty();
        }
        owner.nextTurn();
    }
    void putBeadsInKalahaCurrentPlayer(int beadsInHand){
        neighbour.putBeadsInKalahaCurrentPlayer(beadsInHand);
    }
    AbstractBowl findOpposite(){
        return neighbour.findOpposite().getNeighbour();
    }
    public boolean isSideCurrentPlayerEmpty(){
        return neighbour.isSideCurrentPlayerEmpty();
    }
    boolean isMySideEmpty(){
        if(beads==0){
            return neighbour.isMySideEmpty();
        } else {
            return false;
        }
    }
    public int[] getTotalScore(){
        return neighbour.getTotalScore();
    }
    public int[] getTotalScore(int beads){
        return neighbour.getTotalScore(beads);
    }

}
