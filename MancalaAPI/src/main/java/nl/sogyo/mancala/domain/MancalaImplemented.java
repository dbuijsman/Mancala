package nl.sogyo.mancala.domain;

public class MancalaImplemented implements Mancala {
    private Bowl bowl;
    public MancalaImplemented() {
        this.bowl = new Bowl();
    }
    /**
     * Method for returning the specified players name.
     *
     * @param playerIndex Index of the player, 1 for the first player and 2 for the second player
     * @return Name of de specified player.
     * @throws IllegalStateException If the players index is not 1 or 2.
     */
    public String getPlayerName(int playerIndex) throws IllegalStateException{
        switch(playerIndex){
            case 1:
                return bowl.getOwner().getName();
            case 2:
                return bowl.getOwner().getOpponent().getName();
            default:
                throw new IllegalStateException("PlayerIndex should be 1 or 2.");
        }
    }
    /**
     * Method for giving the specified player his or her name.
     *
     * @param name Name for the player.
     * @param playerIndex Index of the player, 1 for the first player and 2 for the second player
     * @throws IllegalStateException If the players index is not 1 or 2.
     */
    public void setPlayerName(String name, int playerIndex) throws IllegalStateException{
        switch(playerIndex){
            case 1:
                bowl.getOwner().setName(name);
                break;
            case 2:
                bowl.getOwner().getOpponent().setName(name);
                break;
            default:
                throw new IllegalStateException("PlayerIndex should be 1 or 2.");
        }
    }

    /**
     * Method indicating if the specified player has the next turn of not.
     *
     * @param playerIndex Index of the player, 1 for the first player and 2 for the second player.
     * @return True if the specified player has the next turn, otherwise False.
     * @throws IllegalStateException If the players index is not 1 or 2.
     */
    public boolean isToMovePlayer(int playerIndex) throws IllegalStateException{
        switch(playerIndex){
            case 1:
                return bowl.getOwner().getIsMyTurn();
            case 2:
                return bowl.getOwner().getOpponent().getIsMyTurn();
            default:
                throw new IllegalStateException("PlayerIndex should be 1 or 2.");
        }
    }

    /**
     * Method for playing the specified recess. Index is as specified below:
     *
     *    13 12 11 10  9  8
     * 14                    7
     *     1  2  3  4  5  6
     *
     * @param index Index of the recess to be played.
     * @return 15 item long Array with the current state of the game. The 15th item indicates which player has the next turn (possible values are 1 or 2).
     */
    public int[] playRecess(int index){
        AbstractBowl bowlToPlay = bowl.getNeighbour(index-1);
        if( bowlToPlay.getOwner().getIsMyTurn() && bowlToPlay instanceof Bowl){
            ((Bowl) bowlToPlay).play();
        }
        return exportGameState();
    }

    /**
     * Method for returning the amount of stones in de specified pit. Index is as specified below:
     *
     *    13 12 11 10  9  8
     * 14                    7
     *     1  2  3  4  5  6
     *
     * @param index Index of the pit.
     * @return Amount of stone.
     */
    public int getStonesForPit(int index){
        return bowl.getNeighbour(index-1).getBeads();
    }

    /**
     * Method for retrieving the current state of the game.
     *
     *    13 12 11 10  9  8
     * 14                    7
     *     1  2  3  4  5  6
     *
     * @return 15 item long Array with the current state of the game. The 15th item indicates which player has the next turn (possible values are 1 or 2).
     */
    public int[] exportGameState(){
        int[] gameState = new int[15];
        for(int index=1; index<=14; index++){
            gameState[index-1] = getStonesForPit(index);
        }
        gameState[14] = isToMovePlayer(1) ? 1 : 2;
        return gameState;
    }

    /**
     * Method for retrieving whether the game has ended or not.
     *
     * @return True is the game has ended otherwise False.
     */
    public boolean isEndOfGame(){
        return bowl.isSideCurrentPlayerEmpty();
    }

    /**
     * Method for retrieving the name of the player that has won the game.
     *
     * @return Name of the winner, or 'null' if the game has not ended yet.
     */
    public String getWinnersName(){
        if(isEndOfGame()){
            int[] score = bowl.getTotalScore();
            if(score[0]>score[1]){
                return bowl.getOwner().getName();
            } else if (score[0]<score[1]){
                return bowl.getOwner().getOpponent().getName();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
