package ru.ifmo.degtiarenko.java.exam.board;

import ru.ifmo.degtiarenko.java.exam.players.PlayerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class BoardConfiguration {
    List<Board> boards = new ArrayList<>();

    public BoardConfiguration() {
        boards.add(new Board());
        boards.add(new Board());
    }

    private Board getBoard(int index) {
        return boards.get(index);
    }
    public Board getMyBoard(PlayerImpl player) {
        return getBoard(player.getPriority().getIndex());
    }

    public Board getEnemyBoard(PlayerImpl player) {
        Board board = new Board(getBoard((player.getPriority().getIndex() + 1) % 2));
        board.hideShips();
        return board;
    }

    public int makeAttackFrom(PlayerImpl player, Coordinate attack) {
        return getBoard((player.getPriority().getIndex() + 1) % 2).makeAttack(attack);
    }

    public enum Priority {
        FIRST(0),
        SECOND(1);

        private final int index;

        Priority(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
