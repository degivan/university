package ru.ifmo.degtiarenko.java.exam.server;

import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration;
import ru.ifmo.degtiarenko.java.exam.board.BoardConfiguration.Priority;
import ru.ifmo.degtiarenko.java.exam.board.Board;
import ru.ifmo.degtiarenko.java.exam.players.*;

/**
 * Created by Degtjarenko Ivan on 28.10.2016.
 */
public class GameServer {
    public static void main(String[] args) {
        BoardConfiguration configuration = new BoardConfiguration();
        PlayerImpl first = new RationalComputerPlayer(Priority.FIRST);
        PlayerImpl second = new BasicComputerPlayer(Priority.SECOND);
        for(Board.ShipTypes type : Board.ShipTypes.values()) {
            System.out.println("Add " + type.getMaxAmountOfShips() + " " + type.name());
            for(int i = 0; i < type.getMaxAmountOfShips(); i++) {
                System.out.println("Player time to put ship on battlefield!");
                first.addShip(configuration, type);
                System.out.println("Computer time to put ship on battlefield");
                second.addShip(configuration, type);
                System.out.println((type.getMaxAmountOfShips() - i - 1) + " " + type.name() + " left to add.");
            }
        }
        System.out.println("FIRST BOARD:");
        configuration.getMyBoard(first).print();
        System.out.println("SECOND BOARD:");
        configuration.getMyBoard(second).print();
        while(true) {
            first.makeMove(configuration);
            if(checkLoser(configuration, second, "First player wins!"))
                break;
            second.makeMove(configuration);
            if(checkLoser(configuration, first, "Second player wins!"))
                break;
        }
    }

    private static boolean checkLoser(BoardConfiguration configuration, Player opposite, String message) {
        if(opposite.loses(configuration)) {
            System.out.println(message);
            return true;
        }
        return false;
    }
}
