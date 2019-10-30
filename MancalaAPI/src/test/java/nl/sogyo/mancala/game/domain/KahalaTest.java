package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;
import nl.sogyo.mancala.*;

public class KahalaTest {
    @Test
    public void positionFirstKalahaTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("First Kalaha is in the wrong place", bowl.getNeighbour(6) instanceof Kalaha);
    }
    @Test
    public void positionSecondKalahaTest(){
        Bowl bowl = new Bowl();
        Assert.assertTrue("Second Kalaha is in the wrong place", bowl.getNeighbour(13) instanceof Kalaha);
    }
    @Test
    public void KalahasStartsWithNoBeadsTest(){
        Bowl bowl = new Bowl();
        AbstractBowl kalahaPlayer1 = bowl.getNeighbour(6);
        AbstractBowl kalahaPlayer2 = kalahaPlayer1.getNeighbour(7);
        Assert.assertTrue("Kalaha of Player 1 must start with no beads", kalahaPlayer1.getBeads()==0);
        Assert.assertTrue("Kalaha of Player 2 must start with no beads", kalahaPlayer2.getBeads()==0);
    }
    @Test
    public void putBeadInOwnKalahaTest() {
        Bowl bowl = new Bowl();
        Bowl bowlToPlay = (Bowl) bowl.getNeighbour(2);
        bowlToPlay.play();
        ((Bowl) bowlToPlay.getNeighbour()).play();
        Assert.assertTrue("With each play, distributing must put a bead in own kalaha", bowlToPlay.getNeighbour(4).getBeads()==2);
    }
    @Test
    public void putNoBeadInOpponentKalahaTest(){
        Bowl bowl = new Bowl(2,4);
        Bowl bowlToPlay = (Bowl) bowl.getNeighbour();
        bowlToPlay.play();
        Assert.assertTrue("While distributing, the opponents kalaha should be skipped", bowlToPlay.getNeighbour(4).getBeads()==0);
        Assert.assertTrue("While distributing, the opponents kalaha shouldn't stop the distribution", bowlToPlay.getNeighbour(5).getBeads()==5);
    }
    @Test
    public void lastBeadOwnEmptyBowlPutBeadsInKalahaTest(){
        Bowl bowl = new Bowl(6,2);
        Bowl emptyBowl = (Bowl) bowl.getNeighbour(2);
        emptyBowl.play(); //Makes own bowl empty
        ((Bowl) bowl.getNeighbour(7)).play(); //Letting the opponent play
        bowl.play();
        Assert.assertTrue("Ending in a empty bowl should put the beads in own kalaha", bowl.getNeighbour(6).getBeads()==3);
    }
}
