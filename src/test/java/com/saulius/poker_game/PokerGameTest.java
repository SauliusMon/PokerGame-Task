package com.saulius.poker_game;

import com.saulius.poker_game.enums.CardSuit;
import com.saulius.poker_game.enums.CardValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

public class PokerGameTest {

    @Test
    public void testingEnumsValuesLength() {
        Assertions.assertEquals(5, CardValue.values().length, "CardValue has wrong amount of Enums: " + CardValue.values().length + " amount.");
        Assertions.assertEquals(4, CardSuit.values().length, "CardSuit has wrong amount of Enums: " + CardSuit.values().length + " amount.");
    }



}
