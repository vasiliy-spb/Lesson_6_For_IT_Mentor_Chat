package dev.cheercode.command;

import dev.cheercode.Game;

public class ShotCommand implements Command {
    private final Game game;
    private final int roomNumber;

    public ShotCommand(Game game, int roomNumber) {
        this.game = game;
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute() {
        game.shot(roomNumber);
    }
}
