package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

public class Card {

    public static final char[] suit = new char[]{'♠', '♦', '♥', '♣'};
    private int cardValue;
    private char rank;

    public Card (int cardValue, char rank){
        this.cardValue = cardValue;
        this.rank = rank;
    }

    public Card(){

    }

    public char getRank() {
        return rank;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }
}
