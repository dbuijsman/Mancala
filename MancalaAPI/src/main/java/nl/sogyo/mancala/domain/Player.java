package nl.sogyo.mancala.domain;

public class Player{
    private String name;
    private Player opponent;
    private boolean isMyTurn;
    public Player(){
        this.isMyTurn = true;
        this.opponent = new Player(this);
    }
    private Player(Player opponent){
        this.isMyTurn = !opponent.getIsMyTurn();
        this.opponent = opponent;
    }
    public void setName(String name){this.name = name;}
    public String getName(){ return name;}
    public Player getOpponent(){
        return opponent;
    }
    public boolean getIsMyTurn(){
        return isMyTurn;
    }

    void nextTurn(){
            changeTurn();
            opponent.changeTurn();
    }
    private void changeTurn(){
        isMyTurn = !isMyTurn;
    }
}
