package ru.ifmo.degtiarenko.java.exam.players;

import ru.ifmo.degtiarenko.java.exam.board.Board;
import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.Coordinate;

import java.io.IOException;
import java.util.Scanner;

/**
 * Reads ship position in format: "'vertical'/'horizontal' left-most up-most". Positions should be in range from 0 to 9 inclusive.
 * Reads coordinate for attack in format "toEast toSouth".
 */
public class HumanPlayer extends PlayerImpl {
    private final Scanner scanner;

    public HumanPlayer(BoardConfiguration.Priority priority) {
        super(priority);
        scanner = new Scanner(System.in);
    }

    @Override
    protected Coordinate getNextAttack(Board enemyBoard) {
        return getNextCoordinate();
    }

    @Override
    protected Coordinate getNextCoordinate() {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Coordinate(x, y);
    }

    @Override
    protected boolean getNextHorizontal() throws IOException {
        return scanner.next().equals("vertical");
    }
}
