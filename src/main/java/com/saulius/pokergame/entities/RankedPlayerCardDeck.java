package com.saulius.pokergame.entities;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class RankedPlayerCardDeck <T extends RankedPlayerCardDeck> implements Comparable<T>{

    private final int deckRanking;
    private final TreeSet<Card> rankedCardsList;
    private final TreeSet<Card> unrankedCardsList;

    public RankedPlayerCardDeck(int deckRanking, TreeSet<Card> rankedCardsList, TreeSet<Card> unrankedCardsList) {
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
    public int compareTo(T cardDeckObject) {
        int handRanking = this.getDeckRanking() - cardDeckObject.getDeckRanking();

        //If the hands aren't of the same ranking, it returns integer
        if (handRanking != 0) {
            return handRanking > 1 ? 1 : -1;
        }
        //This is done because comparing RoyalFlush always results in a tie, so there is no point to compare card values
        if (this.getDeckRanking() == 10) {
                return handRanking;
        }
        return this.compareCardSets(cardDeckObject);
    }

    /*
    Using reverse iterator here, because cards are sorted from smallest to highest
    */
    private int compareCardSets (T cardDeckObject) {
        if (this.getRankedCardsList() != null) {
            Iterator<Card> firstRankedSetIterator = this.getRankedCardsList().descendingIterator();
            Iterator secondRankedSetIterator = cardDeckObject.getRankedCardsList().descendingIterator();
            while (firstRankedSetIterator.hasNext()) {
                int comparedCards = comparable.compare(firstRankedSetIterator.next(), (Card) secondRankedSetIterator.next());
                if (comparedCards != 0) {
                    return comparedCards > 0 ? 1 : -1;
                }
            }
        }
        if (this.getUnrankedCardsList()!= null) {
            Iterator<Card> firstUnrankedSetIterator = this.getUnrankedCardsList().descendingIterator();
            Iterator secondUnrankedSetIterator = cardDeckObject.getUnrankedCardsList().descendingIterator();
            while (firstUnrankedSetIterator.hasNext()) {
                int comparedCards = comparable.compare(firstUnrankedSetIterator.next(), (Card) secondUnrankedSetIterator.next());
                if (comparedCards != 0) {
                    return comparedCards > 0 ? 1 : -1;
                }
            }
        }
        return 0;
    }

    /*
    This is needed because Cards are being compared by both value and suit.
    In a case suit matters in CardDeck ranking, it could be deleted and card compare method could be used.
    */
    private final Comparator<Card> comparable = new Comparator<Card>() {
        @Override
        public int compare(Card card1, Card card2) {
            return card1.getCardValue() - card2.getCardValue();
        }
    };
}
