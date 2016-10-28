package ru.ifmo.degtiarenko.java.exam.players;


import ru.ifmo.degtiarenko.java.exam.board.Board;
import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.Coordinate;

import java.util.Random;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class BasicComputerPlayer extends PlayerImpl {
    private final Random random = new Random();

    public BasicComputerPlayer(BoardConfiguration.Priority priority) {
        super(priority);
    }

    @Override
    protected Coordinate getNextCoordinate() {
        return new Coordinate(random.nextInt(Board.WIDTH), random.nextInt(Board.LENGTH));
    }

    @Override
    protected boolean getNextHorizontal() {
        return random.nextBoolean();
    }

    @Override
    protected Coordinate getNextAttack(Board enemyBoard) {
        return getNextCoordinate();
    }
}
