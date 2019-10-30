package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;
import nl.sogyo.mancala.*;

public class PlayerTest {
    @Test
    public void totalPlayersTest(){
        Player player1 = new Player();
        Assert.assertTrue("Number of players must be greater than 1", player1.getOpponent() != player1);
        Assert.assertTrue("Number of players must be smaller than 2", player1.getOpponent().getOpponent() == player1);
    }
    @Test
    public void turnPlayerTest() {
        Player player1 = new Player();
        Assert.assertTrue("Only Player1 can start",player1.getIsMyTurn() & !player1.getOpponent().getIsMyTurn());
    }
    @Test
    public void player1HasOwnSideTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("Bowl on position 0 must be from player1", bowl.getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 1 must be from player1", bowl.getNeighbour().getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 2 must be from player1", bowl.getNeighbour(2).getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 3 must be from player1", bowl.getNeighbour(3).getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 4 must be from player1", bowl.getNeighbour(4).getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 5 must be from player1", bowl.getNeighbour(5).getOwner() == bowl.getOwner());
        Assert.assertTrue("Bowl on position 6 must be from player1", bowl.getNeighbour(6).getOwner() == bowl.getOwner());
    }
    @Test
    public void player2HasOwnSideTest(){
        Bowl bowl = new Bowl();
        AbstractBowl firstBowlToCount = bowl.getNeighbour(7); //First position after first Kalaha
        Assert.assertTrue("Bowl on position 7 must be from player2", firstBowlToCount.getOwner() != bowl.getOwner());
        Assert.assertTrue("Bowl on position 8 must be from player2", firstBowlToCount.getNeighbour().getOwner() == firstBowlToCount.getOwner());
        Assert.assertTrue("Bowl on position 9 must be from player2", firstBowlToCount.getNeighbour(2).getOwner() == firstBowlToCount.getOwner());
        Assert.assertTrue("Bowl on position 10 must be from player2", firstBowlToCount.getNeighbour(3).getOwner() == firstBowlToCount.getOwner());
        Assert.assertTrue("Bowl on position 11 must be from player2", firstBowlToCount.getNeighbour(4).getOwner() == firstBowlToCount.getOwner());
        Assert.assertTrue("Bowl on position 12 must be from player2", firstBowlToCount.getNeighbour(5).getOwner() == firstBowlToCount.getOwner());
        Assert.assertTrue("Bowl on position 13 must be from player2", firstBowlToCount.getNeighbour(6).getOwner() == firstBowlToCount.getOwner());
    }
    @Test
    public void lastBeadInOwnBowlChangeTurnTest(){
        Bowl bowl = new Bowl();
        bowl.play();
        Assert.assertTrue("Ending in own bowl should change turns", !bowl.getOwner().getIsMyTurn() & bowl.getOwner().getOpponent().getIsMyTurn());
    }
    @Test
    public void lastBeadInOpponentBowlChangeTurnTest(){
        Bowl bowl = new Bowl();
        Bowl bowlToPlay = (Bowl) bowl.getNeighbour(4);
        bowlToPlay.play();
        Assert.assertTrue("Ending in own bowl should change turns", !bowl.getOwner().getIsMyTurn() & bowl.getOwner().getOpponent().getIsMyTurn());
    }
    @Test
    public void lastBeadInKalahaNoChangeTurn(){
        Bowl bowl = new Bowl();
        Bowl bowlToPlay = (Bowl) bowl.getNeighbour(2);
        bowlToPlay.play();
        Assert.assertTrue("Ending in a kalaha shouldn't change turns",bowl.getOwner().getIsMyTurn() & !bowl.getOwner().getOpponent().getIsMyTurn());
    }
}
