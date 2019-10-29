package nl.sogyo.mancala;
import java.util.Scanner;

public class MancalaPlay {
    private static Bowl bowl = new Bowl();
    public static void main(String[] args) {
        boolean gameOver = false;
        while(!gameOver){
            showBoard();
            Bowl selectedBowl = selectBowl();
            selectedBowl.play();
            gameOver = bowl.isSideCurrentPlayerEmpty();
        }
        showBoard();
        Player winner = winner();
        if(winner == null){
            System.out.print("It's a tie.");
        } else {
            System.out.print("Congratulations " + winner.getName() + ": you have won!");
        }

    }
    private static Bowl selectBowl(){
        if(bowl.getOwner().getIsMyTurn()){
            System.out.print(bowl.getOwner().getName() + ";");
        } else {
            System.out.print(bowl.getOwner().getOpponent().getName() + ":");
        }
        System.out.print(" select the bowl you want to play: ");
        AbstractBowl selectedBowl = bowl.getNeighbour(input());
        if (!selectedBowl.getOwner().getIsMyTurn() | !(selectedBowl instanceof Bowl) | selectedBowl.getBeads()==0){
            System.out.print("This bowl is not playable. ");
            selectedBowl = selectBowl();
        }
        return (Bowl) selectedBowl;
    }
    private static int input(){
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()){
            System.out.print("Please enter a integer: ");
            input.next();
        }
        return input.nextInt();
    }
    private static void showBoard(){
        spacingLengthBeadsInKalahaPlayer2();
        System.out.print(" |");
        for(int counter=12;counter>6;counter--){
            spacingBowl(counter);
            System.out.print(bowl.getNeighbour(counter).getBeads() + " |");
        }
        System.out.println("  ");
        System.out.println(bowl.getNeighbour(13).getBeads() + " |---|---|---|---|---|---| " + bowl.getNeighbour(6).getBeads());
        spacingLengthBeadsInKalahaPlayer2();
        System.out.print(" |");
        for(int counter=0;counter<6;counter++){
            spacingBowl(counter);
            System.out.print(bowl.getNeighbour(counter).getBeads() + " |");
        }
        System.out.println("  ");
    }
    private static void spacingLengthBeadsInKalahaPlayer2(){
        AbstractBowl kalahaPlayer2 = bowl.getNeighbour(13);
        for(int counter=0;counter<String.valueOf(kalahaPlayer2.getBeads()).length();counter++){
            System.out.print(" ");
        }
    }
    private static void spacingBowl(int bowlNumber){
        Bowl printingBowl = (Bowl) bowl.getNeighbour(bowlNumber);
        if(printingBowl.getBeads() < 10){
            System.out.print(" ");
        }
    }
    private static Player winner(){
        int beadsPlayer1 = bowl.getNeighbour(6).getBeads();
        int beadsPlayer2 = bowl.getNeighbour(13).getBeads();
        if(beadsPlayer1 > beadsPlayer2){
            return bowl.getNeighbour(6).getOwner();
        } else if(beadsPlayer2 > beadsPlayer1) {
            return bowl.getNeighbour(13).getOwner();
        } else {return null;}
    }
}
