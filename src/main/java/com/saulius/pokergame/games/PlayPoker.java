package com.saulius.pokergame.games;

import com.saulius.pokergame.hands.PokerHands;
import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayPoker {

    /*
    If there would be more than 2 players, usage of some kind of Collection<Integer> could be used.
    */
    private int pointsForPlayer1;
    private int pointsForPlayer2;

    public void playPokerFromTextFile (File fileToRead, int amountOfCardsInARow, int amountOfPlayers) {
        try {
            Scanner myReader = new Scanner(fileToRead);
            while (myReader.hasNextLine()) {
                List<PlayerCardDeck> playerCardDecks = stringToCardDecks(myReader.nextLine(), amountOfCardsInARow, amountOfPlayers);
                int whichDeckIsBetter = PokerHands.lookForHandInCardDeck(playerCardDecks.get(0)).compareTo(PokerHands.lookForHandInCardDeck(playerCardDecks.get(1)));
                /*
                It adds 1 point to the winning player, or does nothing if it's a tie, though tie is a very improbable scenario
                */
                if (whichDeckIsBetter > 0) {
                    pointsForPlayer1++;
                }
                else if (whichDeckIsBetter < 0) {
                    pointsForPlayer2++;
                }
            }
            myReader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println(pointsForPlayer1 + " " + pointsForPlayer2);
    }

    //Converts 2 passed characters to Card by using Enum values
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

    //Converts line of cards to a CardDeck
    public List<PlayerCardDeck> stringToCardDecks (String cardsString, int amountOfCardsInARow, int amountOfPlayers) {
        List<PlayerCardDeck> playersCardDecks = new ArrayList<>(amountOfPlayers);

        String[] individualCards = cardsString.split(" ", amountOfCardsInARow);

        int playerDeckSize = amountOfCardsInARow / amountOfPlayers;

        /*
        Could easily implement functionality here if there is more than 2 players
        */
        PlayerCardDeck cardDeckOfPlayer1 = new PlayerCardDeck("Player 1", playerDeckSize);
        PlayerCardDeck cardDeckOfPlayer2 = new PlayerCardDeck("Player 2", playerDeckSize);

        for (String cardString : individualCards) {
            Card card = charToCardConverter(cardString.charAt(0), cardString.charAt(1));
            if (cardDeckOfPlayer1.canAddCard()) {
                cardDeckOfPlayer1.addCard(card);
            }
            else {
                cardDeckOfPlayer2.addCard(card);
            }
        }
        playersCardDecks.add(cardDeckOfPlayer1);
        playersCardDecks.add(cardDeckOfPlayer2);

        return playersCardDecks;
    }
}
