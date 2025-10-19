package dev.cheercode.command;

import dev.cheercode.Game;

public class MoveCommand implements Command {
    private final Game game;
    private final int roomNumber;

    public MoveCommand(Game game, int roomNumber) {
        this.game = game;
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute() {
        game.move(roomNumber);
    }
}
