package com.saulius.pokergame.comparators;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.CardDeck;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;

import java.util.*;

public class PokerHands {

    public static int lookForHandInCardDeck (PlayerCardDeck playerCardDeck) {
        boolean areCardsOfTheSameSuit = true;
        boolean areCardsOfConsecutiveValues;

        List<Card> cardsList = playerCardDeck.getDeckOfCards();

        //Sorts cards by ascending values
        cardsList.sort(Comparator.comparingInt(Card::getCardValue));
        /*
        Checks whether all cards are of the same suit
        */
        CardSuit cardSuit = cardsList.get(0).getCardSuit();
        HashMap<Integer, Integer> cardValueWithAmountOfAppearances = new HashMap<>();

        //        /*
//        Finds every single pair, three of a kind and four of a kind
//        */
//        List<Integer> secondCardStorage = new ArrayList<>();
//        for(int i = 0; i < cardsList.size() - 1; i++) {
//            int firstCardValue = cardsList.get(i).getCardValue();
//            for (int x = i + 1; x < cardsList.size(); x++) {
//                int secondCardValue = cardsList.get(x).getCardValue();
//                if (firstCardValue == secondCardValue) {
//                    secondCardStorage.add(secondCardValue);
//                }
//            }
//            if (secondCardStorage.size() != 0) {
//                if (secondCardStorage.size() == 3) {
//                    //Size == 3 means the CardDeck has Four of a Kind - integer was even with primary value 3 times in a row.
//                    return 8;
//                }
//                else if (secondCardStorage.size() == 2) {
//                    hasThreeOfTheKind = true;
//                    threeOfTheKindValue = secondCardStorage.get(0);
//                }
//                else if (secondCardStorage.size() == 1) {
//                    cardPairs.add(secondCardStorage.get(0));
//                }
//                secondCardStorage.clear();
//            }
//        }


        //Performance wise it should be way better
        //It's possible because List is already sorted
        for (int i = 0; i < cardsList.size() - 1; i++) {
                Card card = cardsList.get(i);
            if (card.getCardValue() == cardsList.get(i + 1).getCardValue()) {
                int valueOfPair = card.getCardValue();
                if (cardValueWithAmountOfAppearances.containsKey(valueOfPair)) {
                    cardValueWithAmountOfAppearances.replace(valueOfPair, cardValueWithAmountOfAppearances.get(valueOfPair) + 1);
                }
                else {
                    cardValueWithAmountOfAppearances.put(card.getCardValue(), 2);
                }
            }

            if (cardSuit != card.getCardSuit()) {
                areCardsOfTheSameSuit = false;
            }
        }

        if (areCardsOfTheSameSuit) {
            areCardsOfTheSameSuit = cardSuit == cardsList.get(cardsList.size() - 1).getCardSuit();
        }
        
        /*
        Checks if it's a Royal Flush
        */
        int smallestCardValue = cardsList.get(0).getCardValue();
        int highestCardValue = cardsList.get(cardsList.size() - 1).getCardValue();
        if (smallestCardValue == 10 && highestCardValue == 14 && areCardsOfTheSameSuit) {
            return 10;
        }

         /*
        Checks whether all cards have consecutive values
        */
        areCardsOfConsecutiveValues = smallestCardValue + cardsList.size() - 1 == highestCardValue;

        /*
        Checks if it's a Straight Flush
        */
        if (areCardsOfTheSameSuit && areCardsOfConsecutiveValues) {
            return 9;
        }

        if (cardValueWithAmountOfAppearances.containsValue(4)) {
            return 8;
        }

        if (cardValueWithAmountOfAppearances.size() == 2 && cardValueWithAmountOfAppearances.containsValue(3)) {
            return 7;
        }

        /*
        Checks if it's a Flush
        */
        if (areCardsOfTheSameSuit) {
            return 6;
        }
        /*
        Checks if it's a Straight
        */
        if (areCardsOfConsecutiveValues) {
            return 5;
        }

        if (cardValueWithAmountOfAppearances.containsValue(3)) {
            return 4;
        }

        if (cardValueWithAmountOfAppearances.size() == 2) {
            return 3;
        }

        if (cardValueWithAmountOfAppearances.size() != 0) {
            return 2;
        }




//
//        /*
//        Checks if it's a Full House
//        Note: threeOfTheKindValue integer is needed to check whether the pair it found isn't a member of ThreeOfTheKind.
//        Example: In a code above, while iterating through loop, integer might detect only 1 value equal to it's.
//        Reason being, the three card value would be before an iteration integer.
//        Might need to change it later.
//        */
//        if (hasThreeOfTheKind && cardPairs.size() != 0 && threeOfTheKindValue != cardPairs.get(0)) {
//            return 7;
//        }
//
//
//        /*
//        Checks if it's a Three of a Kind
//        */
//        if (hasThreeOfTheKind) {
//            return 4;
//        }
//        /*
//        Checks if it's Two Pairs
//        */
//        if (cardPairs.size() == 2) {
//            return 3;
//        }
//        /*
//        Checks if it's One Pairs
//        */
//        if (cardPairs.size() == 1) {
//            return 2;
//        }
        /*
        Returns 1 if it's a High Card
        */
        return 1;
    }
}
