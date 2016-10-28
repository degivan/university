package ru.ifmo.degtiarenko.java.exam.players;

import ru.ifmo.degtiarenko.java.exam.board.Board;
import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.Coordinate;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class RationalComputerPlayer extends BasicComputerPlayer{
    private Coordinate lastHit = null;

    public RationalComputerPlayer(BoardConfiguration.Priority priority) {
        super(priority);
    }

    @Override
    public Coordinate getNextAttack(Board enemyBoard) {
        Coordinate coord = null;
        if(lastHit != null)
            coord = enemyBoard.findClosePoint(lastHit);
        if(coord == null) {
            coord = getNextCoordinate();
            lastHit = null;
        }
        return coord;
    }

    @Override
    public Coordinate makeMove(BoardConfiguration configuration) {
        if(lastHit == null) {
            lastHit = super.makeMove(configuration);
            return lastHit;
        } else {
            return super.makeMove(configuration);
        }
    }


}
