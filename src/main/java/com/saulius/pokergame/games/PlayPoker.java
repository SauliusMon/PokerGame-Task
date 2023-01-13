package com.saulius.pokergame.games;

import com.saulius.pokergame.comparators.PokerHands;
import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.CardDeck;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;

import java.io.File;
import java.util.Scanner;

public class PlayPoker {

    private int pointsForPlayer1;
    private int pointsForPlayer2;

    public void playPokerFromTextFile (File fileToRead, int amountOfCardsInRow) {
        try {
            Scanner myReader = new Scanner(fileToRead);
            while (myReader.hasNextLine()) {
                String[] individualCards = myReader.nextLine().split(" ", amountOfCardsInRow);
                 /*
                    Easily modifiable for more players. Would need to declare additional array for PlayerCardDeck and
                    player points though.
                 */
                int playerDeckSize = amountOfCardsInRow / 2;
                PlayerCardDeck cardDeckOfPlayer1 = new PlayerCardDeck("Player 1", playerDeckSize);
                PlayerCardDeck cardDeckOfPlayer2 = new PlayerCardDeck("Player 2", playerDeckSize);

                for (String cardString : individualCards) {
                    Card card = charToCardConverter(cardString.charAt(0), cardString.charAt(1));
                    if (!cardDeckOfPlayer1.addCard(card)) {
                        cardDeckOfPlayer2.addCard(card);
                    }
                }
                PokerHands.lookForHandInCardDeck(cardDeckOfPlayer1);
                PokerHands.lookForHandInCardDeck(cardDeckOfPlayer2);
            }

            myReader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Card charToCardConverter (Character cardValueChar, Character cardSuitChar) {
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
