package ru.ifmo.degtiarenko.java.exam.players;

import ru.ifmo.degtiarenko.java.exam.board.Board;
import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.Coordinate;

import static ru.ifmo.degtiarenko.java.exam.board.Board.*;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public abstract class PlayerImpl implements Player {
    private final BoardConfiguration.Priority priority;

    protected PlayerImpl(BoardConfiguration.Priority priority) {
        this.priority = priority;
    }

    @Override
    public Coordinate makeMove(BoardConfiguration configuration) {
        Coordinate attack = null;
        Board enemyBoard = getEnemyBoard(configuration);
        while(true) {
            attack = getNextAttack(enemyBoard);
            if(!checkCoordOnField(attack) || !checkAttackWasBefore(enemyBoard, attack)) {
                System.out.println("Bad coordinates for attack! Try again: ");
            } else {
                int hit = configuration.makeAttackFrom(this, attack);
                if(hit == -1) {
                    System.out.println("Missed!");
                    break;
                } else if(hit == 0) {
                    System.out.println("Hit!One more shot:");
                    enemyBoard = getEnemyBoard(configuration);
                } else {
                    System.out.println("Killed! One more shot:");
                    enemyBoard = getEnemyBoard(configuration);
                }
                enemyBoard.print();
                if(enemyBoard.getFieldsCounter() == 0)
                    break;
            }
        }
        return attack;
    }

    private Board getEnemyBoard(BoardConfiguration configuration) {
        return configuration.getEnemyBoard(this);
    }

    private static boolean checkAttackWasBefore(Board enemyBoard, Coordinate attack) {
        return enemyBoard.getField(attack.getX(), attack.getY()) != Field.BOMBED;
    }

    protected abstract Coordinate getNextAttack(Board enemyBoard);

    public void addShip(BoardConfiguration configuration, ShipTypes type) {
        Board board = configuration.getMyBoard(this);
        while(true) {
            try {
                boolean horizontal = getNextHorizontal();
                Coordinate start = getNextCoordinate();
                if(addShipIfCorrect(horizontal, type, board, start)) {
                    System.out.println("Success");
                    break;
                }
            } catch(Exception e) {
                System.err.println("Failed to add ship: cannot recognize coordinate and vector");
                e.printStackTrace();
                throw new Error();
            }

        }
    }

    protected abstract Coordinate getNextCoordinate() throws Exception;

    protected abstract boolean getNextHorizontal() throws Exception;

    private boolean addShipIfCorrect(boolean horizontal, ShipTypes type, Board board, Coordinate start) {
        Coordinate end = Coordinate.countEnd(start, horizontal, type);
        if(!checkShipFits(start, end) || !checkShipNotTouch(start, end, board)) {
            System.out.println("Bad coordinates for battleship!Try again:");
        } else {
            board.addShip(type, horizontal, start);
            board.print();
            return true;
        }
        return false;
    }

    private static boolean checkShipNotTouch(Coordinate start, Coordinate end, Board board) {
        for(int i = start.getX() - 1; i <= end.getX() + 1; i++) {
            for(int j = start.getY() - 1; j <= end.getY() + 1; j++) {

                if(checkCoordOnField(new Coordinate(i, j)) && board.getField(i, j).equals(Field.SHIP))
                    return false;
            }
        }
        return true;
    }

    private static boolean checkShipFits(Coordinate start, Coordinate end) {
        return checkCoordOnField(start) && checkCoordOnField(end);
    }

    @Override
    public boolean loses(BoardConfiguration configuration) {
        return configuration.getMyBoard(this).getFieldsCounter() == 0;
    }

    public BoardConfiguration.Priority getPriority() {
        return priority;
    }
}
