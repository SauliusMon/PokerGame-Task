package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.enums.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void card_10C() {
        Card testingCard0 = new Card(10, CardSuit.C);
        Assertions.assertEquals(CardSuit.C, testingCard0.getCardSuit(),"Card class has set wrong suit.");
        Assertions.assertEquals(10, testingCard0.getCardValue(),"Card class has set wrong value.");
        Assertions.assertEquals("10C", testingCard0.toString(), "Card class toString() method returns an invalid string.");
 }

    @Test
    public void card_12H() {
        Card testingCard1 = new Card(12, CardSuit.H);
        Assertions.assertEquals(CardSuit.H, testingCard1.getCardSuit(),"Card class has set wrong suit.");
        Assertions.assertEquals(12, testingCard1.getCardValue(),"Card class has set wrong value.");
        Assertions.assertEquals("12H", testingCard1.toString(), "Card class toString() method returns an invalid string.");
    }
}
