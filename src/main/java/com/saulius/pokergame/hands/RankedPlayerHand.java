package com.saulius.pokergame.hands;

import com.saulius.pokergame.entities.Card;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
This class is used to compare 2 different player hands.
*/
public class RankedPlayerHand implements Comparable<RankedPlayerHand>{

    //Deck ranking - is a hand power ranking from 1 to 10
    private final int deckRanking;
    //Ranked cards - are cards which were ranked while assigning deck ranking value
    private final TreeSet<Card> rankedCardsList;
    //Unranked cards - are cards which weren't used in a hand power ranking and didn't affect deck ranking
    private final TreeSet<Card> unrankedCardsList;

    public RankedPlayerHand(int deckRanking, TreeSet<Card> rankedCardsList, TreeSet<Card> unrankedCardsList) {
        this.deckRanking = deckRanking;
        this.rankedCardsList = rankedCardsList;
        this.unrankedCardsList = unrankedCardsList;
    }

    public int getDeckRanking() {
        return deckRanking;
    }

    public TreeSet<Card> getRankedCardsList() {
        return rankedCardsList;
    }

    public TreeSet<Card> getUnrankedCardsList() {
        return unrankedCardsList;
    }

    @Override
    public int compareTo(RankedPlayerHand cardDeckObject) {
        int handRanking = this.getDeckRanking() - cardDeckObject.getDeckRanking();

        //If the hands aren't of the same ranking, it returns integer
        if (handRanking != 0) {
            return handRanking > 0 ? 1 : -1;
        }
        //This is done because comparing RoyalFlush always results in a tie, so there is no point to compare card values
        if (this.getDeckRanking() == 10) {
                return handRanking;
        }
        //Starts comparing ranked and unranked Deck sets by value - this happens only when both players have similar hands
        return this.compareCardSets(cardDeckObject);
    }

    /*
    Using reverse iterator here, because cards are sorted from smallest to highest
    */
    private int compareCardSets (RankedPlayerHand cardDeckObject) {
        if (this.getRankedCardsList() != null) {
            Iterator<Card> firstRankedSetIterator = this.getRankedCardsList().descendingIterator();
            Iterator<Card> secondRankedSetIterator = cardDeckObject.getRankedCardsList().descendingIterator();
            while (firstRankedSetIterator.hasNext()) {
                int comparedCards = cardValueComparator.compare(firstRankedSetIterator.next(), secondRankedSetIterator.next());
                if (comparedCards != 0) {
                    return comparedCards > 0 ? 1 : -1;
                }
            }
        }
        if (this.getUnrankedCardsList() != null) {
            Iterator<Card> firstUnrankedSetIterator = this.getUnrankedCardsList().descendingIterator();
            Iterator<Card> secondUnrankedSetIterator = cardDeckObject.getUnrankedCardsList().descendingIterator();
            while (firstUnrankedSetIterator.hasNext()) {
                int comparedCards = cardValueComparator.compare(firstUnrankedSetIterator.next(), secondUnrankedSetIterator.next());
                if (comparedCards != 0) {
                    return comparedCards > 0 ? 1 : -1;
                }
            }
        }
        //Chance of a case, where hands would be even after comparisons, is almost non-existent
        return 0;
    }

    /*
    This is needed because Cards are being compared by both value and suit.
    In a case where suit does matter in a CardDeck ranking, it could be deleted and card compare method could be used.
    */
    private final Comparator<Card> cardValueComparator = Comparator.comparingInt(Card::getCardValue);
}
