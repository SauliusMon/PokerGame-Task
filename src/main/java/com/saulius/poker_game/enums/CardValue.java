package com.saulius.poker_game.enums;

public enum CardValue {
    T(10), J(11), Q(12), K(13), A(14);

    private final int cardValue;

    CardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public int getValue() {
        return cardValue;
    }
}
