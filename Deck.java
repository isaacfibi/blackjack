package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.ArrayList;

public class Deck {
    private Card[] cards;
    ArrayList<Integer> usedCards = new ArrayList<>();

    public Deck(){
        cards = cardGenerator();
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Card[] cardGenerator(){

        Card[] newDeck = new Card[52];
        int index = 0;

        for (int i = 0; i <= 3; i++){
            for (int j = 1; j <= 13; j++ ){
                Card set = new Card(j, Card.suit[i]);
                newDeck[index] = set;
                index++;
            }
        }
        return newDeck;
    }

    public void deal(Player player){

        boolean cardExist = false;

        while(!cardExist) {

            int num = (int) (Math.random() * 52);

            Card card = cards[num];

            if (!usedCards.contains(num)) {
                player.getHand().getCards().add(card);
                usedCards.add(num);
                cardExist = true;
            }
        }
    }

}
