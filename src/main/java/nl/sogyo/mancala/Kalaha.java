package nl.sogyo.mancala;

public class Kalaha extends AbstractBowl {
    Kalaha(Player owner, int numberOfBowlsOnOneSide, int numberOfStartingBeads, Bowl bowl){
        this.beads = 0;
        this.owner = owner;
        if(owner.getIsMyTurn()) {
            this.neighbour = new Bowl(numberOfBowlsOnOneSide, numberOfBowlsOnOneSide, numberOfStartingBeads, owner.getOpponent(), bowl);
        } else {
            this.neighbour = bowl;
        }
    }

    void distribute(int beadsInHand){
        if(owner.getIsMyTurn()){
            this.beads++;
            beadsInHand--;
        }
        if (beadsInHand > 0){
            neighbour.distribute(beadsInHand);
        }
    }
    void putBeadsInKalahaCurrentPlayer(int beadsInHand){
        if (owner.getIsMyTurn()){
            beads = beads + beadsInHand;
        } else{
            neighbour.putBeadsInKalahaCurrentPlayer(beadsInHand);
        }
    }
    AbstractBowl findOpposite(){
        return this;
    }

    public boolean isSideCurrentPlayerEmpty(){
        if(!owner.getIsMyTurn()){
            return neighbour.isMySideEmpty();
        } else {
            return neighbour.isSideCurrentPlayerEmpty();
        }
    }
    boolean isMySideEmpty(){
        return true;
    }

}
