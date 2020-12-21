package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.ArrayList;

public class Hand {
    protected ArrayList<Card> cards = new ArrayList<>();
    private int handValue;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int score(){
        handValue = 0;
        for (Card card : cards){
            if (card.getCardValue()<=10){
                handValue += card.getCardValue();
            }
            else {
                handValue += 10;
            }
        }
        return handValue;
    }

    public boolean compareTo21() {
        return handValue > 21;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();

        for (Card card: cards){
            sb.append(card.getCardValue()).append(card.getRank()).append(" ");
        }

        System.out.println(sb.toString());
    }
}
