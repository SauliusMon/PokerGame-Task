package com.saulius.pokergame;

import com.saulius.pokergame.Entity.Card;
import com.saulius.pokergame.datareading.ReadingPokerTextFile;
import com.saulius.pokergame.enums.CardSuit;

import java.io.File;

public class Main {

    protected static final File pokerTextFile = new File("src/main/resources/textfiles/poker.txt");

    public static void main (String[] args)  {
       ReadingPokerTextFile.readFile(pokerTextFile);

       Card card = new Card(10, CardSuit.S);
       System.out.println(card.toString());

    }

}
