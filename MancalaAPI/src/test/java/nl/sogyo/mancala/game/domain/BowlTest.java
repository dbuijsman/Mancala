package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;
import nl.sogyo.mancala.*;
//Test12345
public class BowlTest {
    @Test
    public void boardSizeTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("Size board must be greater than 1", bowl.getNeighbour() != bowl);
        Assert.assertTrue("Size board must be greater than 2",bowl.getNeighbour(2) != bowl);
        Assert.assertTrue("Size board must be greater than 7", bowl.getNeighbour(7) != bowl);
        Assert.assertTrue("Size board must be 14", bowl.getNeighbour(14) == bowl);
    }
    @Test
    public void FirstSixPositionsAreBowlsTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("Bowl on position 0 must be a bowl", bowl instanceof Bowl);
        Assert.assertTrue("Bowl on position 1 must be a bowl", bowl.getNeighbour() instanceof Bowl);
        Assert.assertTrue("Bowl on position 2 must be a bowl", bowl.getNeighbour(2) instanceof Bowl);
        Assert.assertTrue("Bowl on position 3 must be a bowl", bowl.getNeighbour(3) instanceof Bowl);
        Assert.assertTrue("Bowl on position 4 must be a bowl", bowl.getNeighbour(4) instanceof Bowl);
        Assert.assertTrue("Bowl on position 5 must be a bowl", bowl.getNeighbour(5) instanceof Bowl);
    }
    @Test
    public void FirstSixPositionsAfterFirstKahalaAreBowlsTest(){
        Bowl bowl = new Bowl();
        AbstractBowl firstBowlToCount = bowl.getNeighbour(7); //First position after first Kalaha
        Assert.assertTrue("Bowl on position 7 must be a bowl", firstBowlToCount instanceof Bowl);
        Assert.assertTrue("Bowl on position 8 must be a bowl", firstBowlToCount.getNeighbour() instanceof Bowl);
        Assert.assertTrue("Bowl on position 9 must be a bowl", firstBowlToCount.getNeighbour(2) instanceof Bowl);
        Assert.assertTrue("Bowl on position 10 must be a bowl", firstBowlToCount.getNeighbour(3) instanceof Bowl);
        Assert.assertTrue("Bowl on position 11 must be a bowl", firstBowlToCount.getNeighbour(4) instanceof Bowl);
        Assert.assertTrue("Bowl on position 12 must be a bowl", firstBowlToCount.getNeighbour(5) instanceof Bowl);
    }
    @Test
    public void bowlsPlayer1StartsWith4BeadsTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("Bowl on position 0 must have 4 beads", bowl.getBeads() ==4);
        Assert.assertTrue("Bowl on position 1 must have 4 beads", bowl.getNeighbour().getBeads()==4);
        Assert.assertTrue("Bowl on position 2 must have 4 beads", bowl.getNeighbour(2).getBeads()==4);
        Assert.assertTrue("Bowl on position 3 must have 4 beads", bowl.getNeighbour(3).getBeads()==4);
        Assert.assertTrue("Bowl on position 4 must have 4 beads", bowl.getNeighbour(4).getBeads()==4);
        Assert.assertTrue("Bowl on position 5 must have 4 beads", bowl.getNeighbour(5).getBeads()==4);
    }
    @Test
    public void bowlsPlayer2StartsWith4BeadsTest(){
        Bowl bowl = new Bowl();
        AbstractBowl firstBowlToCount = bowl.getNeighbour(7); //First position after first Kalaha
        Assert.assertTrue("Bowl on position 7 must have 4 beads", firstBowlToCount.getBeads()==4);
        Assert.assertTrue("Bowl on position 8 must have 4 beads", firstBowlToCount.getNeighbour().getBeads()==4);
        Assert.assertTrue("Bowl on position 9 must have 4 beads", firstBowlToCount.getNeighbour(2).getBeads()==4);
        Assert.assertTrue("Bowl on position 10 must have 4 beads", firstBowlToCount.getNeighbour(3).getBeads()==4);
        Assert.assertTrue("Bowl on position 11 must have 4 beads", firstBowlToCount.getNeighbour(4).getBeads()==4);
        Assert.assertTrue("Bowl on position 12 must have 4 beads", firstBowlToCount.getNeighbour(5).getBeads()==4);
    }
    @Test
    public void playEmptiesChosenBowlTest(){
        Bowl bowl = new Bowl();
        bowl.play();
        Assert.assertTrue("The chosen bowl must be emptied", bowl.getBeads()==0);
    }
    @Test
    public void putBeadsInOwnBowlsTest(){
        Bowl bowl = new Bowl();
        bowl.play();
        Assert.assertTrue("Distributing must put a bead in the next bowl", bowl.getNeighbour().getBeads()==5);
        Assert.assertTrue("Distributing must put a bead in the next 2 bowls", bowl.getNeighbour(2).getBeads()==5);
        Assert.assertTrue("Distributing must put a bead in the next 3 bowls", bowl.getNeighbour(3).getBeads()==5);
        Assert.assertTrue("Distributing must put a bead in the next 4 bowls", bowl.getNeighbour(4).getBeads()==5);
        Assert.assertTrue("Distributing must stop after 4 bowls", bowl.getNeighbour(5).getBeads()==4);
    }
    @Test
    public void putBeadOpponentBowlTest(){
        Bowl bowl = new Bowl();
        Bowl bowlToPlay = (Bowl) bowl.getNeighbour(4);
        bowlToPlay.play();
        Assert.assertTrue("Distributing must put a bead in the opponent bowl", bowlToPlay.getNeighbour(3).getBeads()==5);
        Assert.assertTrue("Distributing must put a bead in the opponent bowl", bowlToPlay.getNeighbour(4).getBeads()==5);
    }
    @Test
    public void lastBeadOwnEmptyBowlEmptiesBothBowlsTest(){
        Bowl bowl = new Bowl(6,2);
        Bowl emptyBowl = (Bowl) bowl.getNeighbour(2);
        emptyBowl.play(); //Makes own bowl empty
        ((Bowl) emptyBowl.getNeighbour(5)).play(); //Letting the opponent play
        bowl.play();
        Assert.assertTrue("Ending in a empty bowl should empty own and opposite bowl", emptyBowl.getBeads()==0 & emptyBowl.getNeighbour(8).getBeads()==0);
    }
    @Test
    public void lastBeadOpponentEmptyBowlNoStealTest(){
        Bowl bowl = new Bowl(3,3);
        ((Bowl) bowl.getNeighbour()).play(); //
        ((Bowl) bowl.getNeighbour(6)).play(); //Ends in bowl of above line
        Assert.assertTrue("Ending in a empty bowl should not steal any beads", bowl.getNeighbour().getBeads()==1 & bowl.getNeighbour(5).getBeads()==3);
    }
    @Test
    public void checkIsAllEmptyFalseIfPlayerSideStillHasBeadsTest(){
        Bowl bowl = new Bowl();
        bowl.play();
        Assert.assertTrue("Check shouldn't give true if side is not empty",!bowl.isSideCurrentPlayerEmpty());
    }
    @Test
    public void checkOpponentsSideIsEmptyWhenLastBeadIsInOwnBowlTest(){
        Bowl bowl = new Bowl(4,1);
        bowl.play();
        ((Bowl) bowl.getNeighbour(5)).play();
        ((Bowl) bowl.getNeighbour(1)).play();
        ((Bowl) bowl.getNeighbour(6)).play();
        ((Bowl) bowl.getNeighbour(2)).play();
        ((Bowl) bowl.getNeighbour(3)).play();
        ((Bowl) bowl.getNeighbour(5)).play();
        Assert.assertTrue("Check shouldn't give true if side is not empty",bowl.isSideCurrentPlayerEmpty());
    }
    @Test
    public void checkOwnSideIsEmptyWhenLastBeadIsInOwnKalahaTest(){
        Bowl bowl = new Bowl(1,1);
        bowl.play(); //Makes own side empty
        Assert.assertTrue("Check shouldn't give true if side is not empty",bowl.isSideCurrentPlayerEmpty());
    }
}
