package ru.ifmo.degtiarenko.java.exam.players;

import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.Coordinate;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public interface Player {
    boolean loses(BoardConfiguration configuration);
    Coordinate makeMove(BoardConfiguration configuration);
}
