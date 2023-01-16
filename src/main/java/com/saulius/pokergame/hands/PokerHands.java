package com.saulius.pokergame.hands;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.entities.RankedPlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;

import java.util.*;

public class PokerHands {

    @SuppressWarnings("rawtypes")
    public static RankedPlayerCardDeck lookForHandInCardDeck (PlayerCardDeck playerCardDeck) {
        boolean areCardsOfTheSameSuit = true;
        boolean areCardsOfConsecutiveValues;

        TreeSet<Card> cardsTreeSet = playerCardDeck.getDeckOfCards();
        TreeSet<Card> rankedCards = new TreeSet<>();
        TreeSet<Card> unrankedCards = new TreeSet<>();

        /*
        Finds every single pair, three of a kind, four of a kind and whether cards are of the same suit.
        For performance’s sake it happens at the same time: this way CardDeck needs to be iterated through only once.
        Because of a low chance for CardDeck to have the highest tier hand, making those calculations earlier shouldn't diminish performance.

        Because the List is sorted in ascending order, in a case there is a pair in CardDeck, the latter should be neighbors.
        E.g, if there is a pair of 10 and 11, 10s will be neighbors and go before 11.

        Loop iterates until second to last member, and checks whether first and second values are even.
        If they are, they are put into HashMap, or the amount of appearance times in CardDeck increases by one.
        */
        HashMap<Integer, Integer> cardValueWithAmountOfAppearances = new HashMap<>();

        Card previousCard = cardsTreeSet.first();
        CardSuit cardSuit = previousCard.getCardSuit();

        for (Card treeSetCard : cardsTreeSet) {
            if (!treeSetCard.equals(previousCard)) {
                if (treeSetCard.getCardValue() == previousCard.getCardValue()) {
                    int valueOfPair = treeSetCard.getCardValue();
                    if (cardValueWithAmountOfAppearances.containsKey(valueOfPair)) {
                        cardValueWithAmountOfAppearances.replace(valueOfPair, cardValueWithAmountOfAppearances.get(valueOfPair) + 1);
                    } else {
                        cardValueWithAmountOfAppearances.put(treeSetCard.getCardValue(), 2);
                    }
                    //Because it appears more than once, adding it to ranked cards set
                    rankedCards.add(treeSetCard);
                }
                else {
                    //If it doesn't reappear, it means it's unranked or all cards are ranked (Flush, Straight)
                    unrankedCards.add(treeSetCard);
                }
                if (cardSuit != treeSetCard.getCardSuit()) {
                    areCardsOfTheSameSuit = false;
                }
                previousCard = treeSetCard;
            }
        }
        
        /*
        Checks if it's a Royal Flush
        */
        int smallestCardValue = cardsTreeSet.first().getCardValue();
        int highestCardValue = cardsTreeSet.last().getCardValue();
        if (smallestCardValue == 10 && highestCardValue == 14 && areCardsOfTheSameSuit) {
            return new RankedPlayerCardDeck(10, playerCardDeck.getDeckOfCards(), new TreeSet<>());
        }

        /*
        Checks whether all cards have consecutive values
        */
        areCardsOfConsecutiveValues = smallestCardValue + cardsTreeSet.size() - 1 == highestCardValue;

        /*
        Checks if it's a Straight Flush
        */
        if (areCardsOfTheSameSuit && areCardsOfConsecutiveValues) {
            return new RankedPlayerCardDeck(9, playerCardDeck.getDeckOfCards(), new TreeSet<>());
        }

        /*
        Checks if it's a Four of a Kind
        Same value reappears 4 times in a CardDeck
        In this case there is no point to know the value of last card.
        Reason being, four of a kind can't be of the same value for both players
         */
        if (cardValueWithAmountOfAppearances.containsValue(4)) {
            return new RankedPlayerCardDeck(8, rankedCards, new TreeSet<>());
        }

        /*
        Checks if it's a full house.
        It's a full house if it found 2 values reappearing (size = 2) and one of those has appeared 3 times (containsValue(3))
        In a case its only 2 reappearing values, it's Two pairs.
         */
        if (cardValueWithAmountOfAppearances.size() == 2 && cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerCardDeck(7, playerCardDeck.getDeckOfCards(), new TreeSet<>());

        }
        /*
        Checks if it's a Flush
        */
        if (areCardsOfTheSameSuit) {
            return new RankedPlayerCardDeck(6, playerCardDeck.getDeckOfCards(), new TreeSet<>());

        }
        /*
        Checks if it's a Straight
        */
        if (areCardsOfConsecutiveValues) {
            return new RankedPlayerCardDeck(5, playerCardDeck.getDeckOfCards(), new TreeSet<>());

        }
        /*
        Checks if it's a Three of a Kind
        For Three of a kind to have same value in both player hands, there should be 6 same value unique cards - which is impossible
        */
        if (cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerCardDeck(4, rankedCards, new TreeSet<>());

        }
        /*
        Checks if it's Two Pairs
        */
        if (cardValueWithAmountOfAppearances.size() == 2) {
            return new RankedPlayerCardDeck(3, rankedCards, unrankedCards);
        }
        /*
        Checks if it's One Pair
        */
        if (cardValueWithAmountOfAppearances.size() != 0) {
            return new RankedPlayerCardDeck(2, rankedCards, unrankedCards);

        }
        /*
        Returns 1 if it's a High Card
        If it's a High Card, it means all Cards are unranked.
        */
        return new RankedPlayerCardDeck(1, new TreeSet<>(), playerCardDeck.getDeckOfCards());
    }
}
