package labs_examples.objects_classes_methods.labs.oop.C_blackjack;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private String name;
    private Hand hand = new Hand();
    private int potValue;
    private String winningStatus;
    private int bet;

    public boolean isEnoughFundToPlay() {
        return getPotValue() > 0 && getPotValue() >= bet;
    }

    public String getWinningStatus() {
        return winningStatus;
    }

    public void setWinningStatus(String winningStatus) {
        this.winningStatus = winningStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getPotValue() {
        return potValue;
    }

    public void setPotValue(int potValue) {
        this.potValue = potValue;
    }

    public boolean computerAI(){
        if (hand.score()<16){
            return true;
        }
        else{
            return false;
        }
    }

    public void putDownBet(){

        int potValue = getPotValue();

        System.out.println("How much would you like to bet?");
        Scanner scanner = new Scanner(System.in);

        boolean notNumber = true;
        while(notNumber) {
            try {
                int betAmt = scanner.nextInt();
                while (!betCheck(betAmt)) {
                    System.out.println("Invalid. You have $" + getPotValue() + " Please enter an amount: ");
                    betAmt = scanner.nextInt();
                }

                System.out.println(getName() + " put down $" + betAmt);

                if ((potValue - betAmt) >= 0 && potValue > 0) {
                    bet = betAmt;
                } else {
                    System.out.println("Insufficient fund");
                    setPotValue(getPotValue() - betAmt);
                    System.out.println(getPotValue() + " pot value");
                }
                notNumber = false;
            } catch (InputMismatchException e) {
                System.out.println("You must enter a positive integer. Try again.");
                scanner.nextLine();
            }
        }
    }

    public void winnerPot(){
        if (getWinningStatus().equals("W")){
            setPotValue(getPotValue() + bet*2);
            System.out.println("You won $" + bet*2);
        }
        if (getWinningStatus().equals("L")||getWinningStatus().equals("D")){
            setPotValue(getPotValue() - bet);
            System.out.println("You lost $" + bet);
        }
        System.out.println("Final pot $" + getPotValue());
    }

    public boolean betCheck(int bet){
        return getPotValue() >= bet && bet > 0;
    }

    public void clearBet(){
        bet = 0;
    }

    public void handleBet() {
        // won't let user to bet if potvalue is 0 also won't let user to bet when they are already busted
        if ( (getPotValue()-bet) > 0 && hand.score() <= 21){
        System.out.println("You have $" + (getPotValue()-bet) + ", would you like to put on more bet?" );
        Scanner scanner = new Scanner(System.in);

        String text = scanner.next();

            while(!(text.equals("yes")||text.equals("no"))){
                text = scanner.nextLine();
                System.out.println("Invalid input. Please enter yes or no. ");
            }

        if (text.equals("yes")){
            System.out.println("How much do you want to bet? ");
            boolean notNumber = true;
            while(notNumber){
                try {
                    int betAmt = scanner.nextInt();
                    while (!betCheck(betAmt)){
                        System.out.println("Invalid. You have $" + (getPotValue()-bet) + " Please enter an amount: ");
                        betAmt = scanner.nextInt();
                    }
                    bet += betAmt;
                    notNumber = false;
                } catch(InputMismatchException e){
                    System.out.println("You must enter a positive integer. Try again.");
                    scanner.nextLine();
                }
            }
        }
        }
    }
}
