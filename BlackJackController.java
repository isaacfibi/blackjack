package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.Scanner;

public class BlackJackController {

    static int winningStatsPlayer;
    static int winningStatsComputer;
    static int gameRound;

    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController();
        controller.startGame();
        System.out.println("Total round played: " + gameRound);
        System.out.println("Computer won " + winningStatsComputer + " times!");
        System.out.println("You won " + winningStatsPlayer + " times!");
    }

    public void startGame() {

        Player player1 = new Player();
        Player player2 = new Player();

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = scan.next();
        player1.setName(playerName);
        player2.setName("Computer");

        player1.setPotValue(10);

        System.out.println("================================");
        System.out.println("===========GAME START===========");
        System.out.println("================================");

        Scanner scanner;
        String playerInput = "yes";
        while (playerInput.equals("yes")) {
            while (player1.isEnoughFundToPlay()) {
                player1.putDownBet();
                playBlackJack(player1, player2);
                player1.winnerPot();
                player1.clearBet();
                if (player1.isEnoughFundToPlay()) {
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println("|Do you want to have another round?|");
                    System.out.println("====================================");
                    scanner = new Scanner(System.in);
                    playerInput = scanner.next();
                    if (playerInput.equals("yes")) {
                        continue;
                    } else {
                        System.out.println("================================");
                        System.out.println("===========GAME OVER============");
                        System.out.println("================================");
                        break;
                    }
                } else {
                    System.out.println("You don't have enough fund to play.");
                    System.out.println("================================");
                    System.out.println("===========GAME OVER============");
                    System.out.println("================================");
                    break;
                }
            }
            playerInput = "n";
        }
    }


    public void playBlackJack(Player player1, Player player2) {

        gameRound++;

        Deck deck = new Deck();

        //Clear previous round cards
        player1.getHand().cards.clear();
        player2.getHand().cards.clear();

        dealInitialCards(deck, player1);
        dealInitialCards(deck, player2);

        System.out.println("Current score for " + player1.getName() + ": " + player1.getHand().score());
        System.out.println("Current score for " + player2.getName() + ": " + player2.getHand().score());

        System.out.println();

        String text = "yes";

        Scanner scanner = new Scanner(System.in);

        while (text.equals("yes")) {
            if (player1.getHand().score() > 21) {
                System.out.println("You are busted!");
                break;
            }
            System.out.println();
            System.out.println(player1.getName() + ", Do you want another card?");
            text = scanner.next();

            while (!(text.equals("yes") || text.equals("no"))) {
                text = scanner.nextLine();
                System.out.println("Invalid input. Please enter yes or no. ");
            }

            if (text.equals("yes")) {
                deck.deal(player1);
                player1.getHand().print();
                System.out.println("Current score for " + player1.getName() + ": " + player1.getHand().score());
                player1.handleBet();
            }
        }

        while (player2.computerAI()) {
            System.out.println();
            System.out.println("Computer also like to have a card");
            deck.deal(player2);
            player2.getHand().print();
            System.out.println("Current score for " + player2.getName() + ": " + player2.getHand().score());
        }

        printFinalResult(player1);
        printFinalResult(player2);

        determineWinner(player1, player2);


    }

    private void printFinalResult(Player player) {
        System.out.println("Final hand of " + player.getName());
        player.getHand().print();
        System.out.println("Total score for " + player.getName() + " is " + player.getHand().score());
        System.out.println();
    }

    // Deal initial first two cards
    private void dealInitialCards(Deck deck, Player player) {

        deck.deal(player);
        deck.deal(player);
        player.getHand().print();
        System.out.println();

    }

    private void determineWinner(Player player1, Player player2) {
        if (player1.getHand().score() == 21) {
            System.out.println(player1.getName() + " is the winner!");
            player1.setWinningStatus("W");
            winningStatsPlayer++;
        } else if (player2.getHand().score() == 21) {
            System.out.println(player2.getName() + " is the winner!");
            player1.setWinningStatus("L");
            winningStatsComputer++;
        } else if (player1.getHand().compareTo21() && !player2.getHand().compareTo21()) {
            System.out.println(player1.getName() + " Busted!");
            System.out.println(player2.getName() + " is the winner!");
            player1.setWinningStatus("L");
            winningStatsComputer++;
        } else if (player2.getHand().compareTo21() && !player1.getHand().compareTo21()) {
            System.out.println(player2.getName() + " Busted!");
            System.out.println(player1.getName() + " is the winner!");
            player1.setWinningStatus("W");
            winningStatsPlayer++;
        } else if (player2.getHand().compareTo21() && player1.getHand().compareTo21()) {
            System.out.println(player1.getName() + " & " + player2.getName() + " are both" + " Busted!");
            System.out.println("The game is a draw!");
            player1.setWinningStatus("D");
        } else if (player1.getHand().score() > player2.getHand().score()) {
            System.out.println(player1.getName() + " is the winner!");
            player1.setWinningStatus("W");
            winningStatsPlayer++;
        } else if (player1.getHand().score() < player2.getHand().score()) {
            System.out.println(player2.getName() + " is the winner!");
            player1.setWinningStatus("L");
            winningStatsComputer++;
        } else {
            System.out.println("Draw!");
            player1.setWinningStatus("D");
        }
    }


}
