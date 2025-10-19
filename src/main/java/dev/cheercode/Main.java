package dev.cheercode;

import dev.cheercode.factory.EightRoomDungeonFactory;
import dev.cheercode.units.Hunter;
import dev.cheercode.units.Wumpus;

public class Main {
    public static void main(String[] args) {
        Wumpus wumpus = new Wumpus();
        Hunter hunter = new Hunter(3);
        Dungeon dungeon = new EightRoomDungeonFactory(wumpus, hunter).create();


        Game game = new Game(dungeon, wumpus, hunter);
        game.start();
    }
}