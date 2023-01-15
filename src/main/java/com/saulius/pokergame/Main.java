package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.games.PlayPoker;

import java.io.File;
import java.util.HashMap;

public class Main {

    private static final File pokerTextFile = new File("src/main/resources/textfiles/poker.txt");
    private static final int amountOfCardsInRow = 10;

    public static void main (String[] args)  {
       new PlayPoker().playPokerFromTextFile(pokerTextFile, 10);
    }

}
