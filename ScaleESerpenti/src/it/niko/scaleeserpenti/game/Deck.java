package it.niko.scaleeserpenti.game;

import java.util.List;

public interface Deck {

    void addCard(GameCards card);

    GameCards drawCard();

    int numCard();

    List<GameCards> getCards();

    void shuffle();
}