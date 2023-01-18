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
                List<PlayerCardDeck> playerCardDecks = PlayerCardDeck.stringToCardDecks(myReader.nextLine(), amountOfCardsInARow, amountOfPlayers);
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
}
