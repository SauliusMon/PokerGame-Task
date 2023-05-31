package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.enums.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterToCardTest {

    @Test
    public void characterToCardConversion_JH() {
        Card testingCard0 = Card.charToCardConverter('J', 'H');
        Assertions.assertEquals(11, testingCard0.getCardValue(), "Converting characters to Card sets wrong value to the Card.");
        Assertions.assertEquals(CardSuit.H, testingCard0.getCardSuit(), "Converting characters to Card sets wrong suit to the Card.");
    }

    @Test
    public void characterToCardConversion_4C() {
        Card testingCard1 = Card.charToCardConverter('4', 'C');
        Assertions.assertEquals(4, testingCard1.getCardValue(), "Converting characters to Card sets wrong value to the Card.");
        Assertions.assertEquals(CardSuit.C, testingCard1.getCardSuit(), "Converting characters to Card sets wrong suit to the Card.");
    }

    @Test
    public void characterToCardConversion_KS() {
        Card testingCard2 = Card.charToCardConverter('K', 'S');
        Assertions.assertEquals(13, testingCard2.getCardValue(), "Converting characters to Card sets wrong value to the Card.");
        Assertions.assertEquals(CardSuit.S, testingCard2.getCardSuit(), "Converting characters to Card sets wrong suit to the Card.");
    }

    @Test
    public void characterToCardConversion_9D() {
        Card testingCard3 = Card.charToCardConverter('9', 'D');
        Assertions.assertEquals(9, testingCard3.getCardValue(), "Converting characters to Card sets wrong value to the Card.");
        Assertions.assertEquals(CardSuit.D, testingCard3.getCardSuit(), "Converting characters to Card sets wrong suit to the Card.");
    }
}
