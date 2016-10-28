package ru.ifmo.degtiarenko.java.exam.board;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class Coordinate {
    private final int xCoord;
    private final int yCoord;

    public Coordinate(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getY() {
        return yCoord;
    }

    public int getX() {
        return xCoord;
    }

    public static Coordinate countEnd(Coordinate start, boolean horizontal, Board.ShipTypes type) {
        if(horizontal) {
            return new Coordinate(start.getX() + type.getDeckAmount() - 1, start.getY());
        } else {
            return new Coordinate(start.getX(), start.getY() + type.getDeckAmount() - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (xCoord != that.xCoord) return false;
        return yCoord == that.yCoord;

    }

    @Override
    public int hashCode() {
        int result = xCoord;
        result = 31 * result + yCoord;
        return result;
    }
}
