package dev.cheercode;

import dev.cheercode.factory.WumpusNextToRoomDungeonFactory;
import dev.cheercode.units.Bat;
import dev.cheercode.units.Hunter;
import dev.cheercode.units.Wumpus;

import java.util.stream.IntStream;

public class Main1 {
    public static void main(String[] args) {
        Wumpus wumpus = new Wumpus();
        Hunter hunter = new Hunter(3);

        Dungeon dungeon = new WumpusNextToRoomDungeonFactory(wumpus, hunter).create();

        System.out.println(dungeon.getRoomNumber(wumpus));
        wumpus.move(dungeon);
        System.out.println(dungeon.getRoomNumber(wumpus));
        System.out.println();

    }
}