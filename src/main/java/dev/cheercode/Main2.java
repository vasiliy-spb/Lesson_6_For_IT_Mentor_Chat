package dev.cheercode;

import dev.cheercode.factory.WumpusNextToRoomDungeonFactory;
import dev.cheercode.units.Bat;
import dev.cheercode.units.Hunter;
import dev.cheercode.units.Wumpus;

public class Main2 {
    public static void main(String[] args) {
        Wumpus wumpus = new Wumpus();
        Hunter hunter = new Hunter(3);
        Bat bat = new Bat();

        Dungeon dungeon = new WumpusNextToRoomDungeonFactory(wumpus, hunter).create();
        dungeon.addUnit(bat, 6);

        System.out.println(dungeon.getRoomNumber(hunter));
        bat.transportHunter(dungeon, hunter);
        System.out.println(dungeon.getRoomNumber(hunter));
        System.out.println(dungeon.getRoomNumber(bat));
        System.out.println();

    }
}