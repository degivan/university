package ru.ifmo.degtiarenko.java.exam.board;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class Ship {
    private Coordinate[] coords;
    private int decksLeft;

    public Ship(Board.ShipTypes type, boolean horizontal, Coordinate start) {
        this.decksLeft = type.getDeckAmount();
        coords = new Coordinate[decksLeft];
        int hor = 0;
        int vert = 0;
        int ignored = horizontal ? (hor = 1) : (vert = 1);
        for(int i = 0; i < decksLeft; i++) {
            coords[i] = new Coordinate(start.getX() + hor * i, start.getY() + vert * i);
        }
    }

    public Coordinate[] getCoords() {
        return coords;
    }

    public void hit() {
        decksLeft--;
    }

    public boolean isDead() {
        return (decksLeft == 0);
    }
}
