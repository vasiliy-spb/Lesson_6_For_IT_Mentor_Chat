package dev.cheercode.command;

import dev.cheercode.Game;

public class CommandFactory {
    private final Game game;
    private final String moveKey;
    private final String shotKey;

    public CommandFactory(Game game, String moveKey, String shotKey) {
        this.game = game;
        this.moveKey = moveKey;
        this.shotKey = shotKey;
    }

    public Command get(int roomNumber, String key) {
        if (key.equalsIgnoreCase(moveKey)) {
            return new MoveCommand(game, roomNumber);
        }
        if (key.equalsIgnoreCase(shotKey)) {
            return new ShotCommand(game, roomNumber);
        }
        throw new IllegalArgumentException("Unknown key: " + key);
    }
}
