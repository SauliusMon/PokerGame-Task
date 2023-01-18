package com.saulius.pokergame.entities;

import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;

public class Card implements Comparable<Card>{

    private final int cardValue;
    private final CardSuit cardSuit;

    public Card(int cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public int getCardValue() {
        return cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public String toString() {
        return cardValue + cardSuit.toString();
    }

    /*
    Sorts set by card value. If values are the same, checks whether suits differ (cards should be always unique per deck)
    If suits would matter for the game, instead of ordinal(), getValue() could be used.
    Here suits are only used so TreeSet would accept cards of the same value and different suits.
    */
    @Override
    public int compareTo(Card card) {
        return this.getCardValue() != card.getCardValue() ?
                this.getCardValue() - card.getCardValue() :
                this.getCardSuit().ordinal() - card.getCardSuit().ordinal();
    }

    //Converts 2 passed characters to Card by using Enum values
    public static Card charToCardConverter (Character cardValueChar, Character cardSuitChar) {
        int cardValue = 0;
        CardSuit cardSuit = null;

        if (Character.isDigit(cardValueChar)) {
            cardValue = cardValueChar - '0';
        } else {
            for (CardValue cardSuitEnum : CardValue.values()) {
                if (cardValueChar.toString().equals(cardSuitEnum.toString())) {
                    cardValue = cardSuitEnum.getValue();
                    break;
                }
                //Invalid card if code gets here
            }
        }
        for (CardSuit cardSuitEnum : CardSuit.values()) {
            if (cardSuitChar.toString().equals(cardSuitEnum.toString())) {
                cardSuit = cardSuitEnum;
                break;
            }
        }
        return new Card(cardValue, cardSuit);
    }
}
