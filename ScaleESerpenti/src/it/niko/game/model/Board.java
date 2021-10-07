package it.niko.game.model;

import it.niko.game.GameBoxes;

public interface Board {

    int boxEffect(int pos);

    GameBoxes boxContent(int pos);

    boolean addSnake(int head, int tail);

    boolean removeSnake(int head, int tail);

    boolean addLadder(int base, int top);

    boolean removeLadder(int base, int top);

    boolean addSpecialBox(int pos, GameBoxes type);

    boolean removeSpecialBox(int pos);
}