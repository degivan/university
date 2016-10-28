package ru.ifmo.degtiarenko.java.exam.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class Board {
    public final static int LENGTH = 10;
    public final static int WIDTH  = 10;


    private final Field[][] board = new Field[LENGTH][WIDTH];
    private int fieldsCounter = 0;
    private List<Ship> ships = new ArrayList<>();
    private final int[][] indexes = new int[LENGTH][WIDTH];

    public Board() {
        for(int i = 0; i < LENGTH; i++) {
            Arrays.fill(board[i], Field.NOTHING);
        }
    }

    public Board(Board board) {
        for(int i = 0; i < LENGTH; i++) {
            for(int j = 0; j < WIDTH; j++) {
                this.board[i][j] = board.board[i][j];
                if(this.board[i][j] == Field.SHIP)
                    fieldsCounter++;
            }
        }
    }

    public void addShip(ShipTypes type, boolean horizontal, Coordinate start) {
        Ship ship = new Ship(type, horizontal, start) ;
        ships.add(ship);
        for(Coordinate coord: ship.getCoords())
            fillField(coord.getX(), coord.getY(), ships.size() - 1);
    }

    public void fillField(int toEast, int toSouth, int index) {
        board[toEast][toSouth] = Field.SHIP;
        indexes[toEast][toSouth] = index;
        fieldsCounter++;
    }

    public Field getField(int toEast, int toSouth) {
        return board[toEast][toSouth];
    }
    public int getFieldsCounter() {
        return fieldsCounter;
    }

    public void print() {
        for(int i = 0; i < LENGTH; i++) {
            for(int j = 0; j < WIDTH; j++) {
                char symbol = '.';
                if(board[i][j] == Field.SHIP) {
                    symbol = '#';
                } else if(board[i][j] == Field.BOMBED) {
                    symbol = 'x';
                }
                System.out.print(symbol);
            }
            System.out.print('\n');
        }
    }

    public int makeAttack(Coordinate attack) {
        int hit = -1;
        if(board[attack.getX()][attack.getY()] == Field.SHIP) {
            hit++;
            Ship ship = getShip(attack);
            ship.hit();
            if(ship.isDead()) {
                hit++;
                bombAround(ship);
            }
            fieldsCounter--;
        }
        board[attack.getX()][attack.getY()] = Field.BOMBED;
        return hit;
    }

    private void bombAround(Ship ship) {
        for(Coordinate coordinate: ship.getCoords())
            for(int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
                for(int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
                    if(checkCoordOnField(new Coordinate(i, j)))
                        board[i][j] = Field.BOMBED;
                }
            }
    }

    private Ship getShip(Coordinate coord) {
        return ships.get(indexes[coord.getX()][coord.getY()]);
    }

    public void hideShips() {
        for(int i = 0; i < LENGTH; i++) {
            for(int j = 0; j < WIDTH; j++)
                if(board[i][j] == Field.SHIP)
                    board[i][j] = Field.NOTHING;
        }
    }

    public enum ShipTypes {
        CARRIER(4),
        BATTLESHIP(3),
        CRUISER(2),
        SUBMARINE(1);

        private final int deckAmount;

        ShipTypes(int deckAmount) {
            this.deckAmount = deckAmount;
        }

        public int getDeckAmount() {
            return deckAmount;
        }

        public int getMaxAmountOfShips() {
            return 5 - deckAmount;
        }
    }

    public enum Field {
        SHIP, NOTHING, BOMBED
    }

    public static boolean checkCoordOnField(Coordinate coord) {
        return checkInRange(coord.getX(), 0, Board.WIDTH - 1) && checkInRange(coord.getY(), 0, Board.LENGTH - 1);
    }

    private static boolean checkInRange(int number, int lowerBound, int upperBound) {
        return number >= lowerBound &&  number <= upperBound;
    }

    public Coordinate findClosePoint(Coordinate lastHit) {
        for(int i = lastHit.getX() - 1; i <= lastHit.getX() + 1; i++) {
            if(checkFieldIsEmpty(i, lastHit.getY(), board))
                return new Coordinate(i, lastHit.getY());
        }
        for(int i = lastHit.getY() - 1; i <= lastHit.getY() + 1; i++) {
            if(checkFieldIsEmpty(lastHit.getX(), i, board))
                return new Coordinate(lastHit.getX(), i);
        }
        return null;
    }

    private static boolean checkFieldIsEmpty(int x, int y, Field[][] board) {
        return checkCoordOnField(new Coordinate(x, y)) && board[x][y] == Field.NOTHING;
    }
}
