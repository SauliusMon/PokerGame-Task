package com.saulius.pokergame.enums;

/*
To compare cards after 9 easier, assigning int value to each of them
*/
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
