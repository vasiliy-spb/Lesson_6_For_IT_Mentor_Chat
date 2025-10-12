package dev.cheercode;

import dev.cheercode.factory.EightRoomDungeonFactory;
import dev.cheercode.units.Hunter;
import dev.cheercode.units.Wumpus;

public class Main {
    public static void main(String[] args) {
        Wumpus wumpus = new Wumpus();
        Hunter hunter = new Hunter();

        Dungeon dungeon = new EightRoomDungeonFactory(wumpus, hunter).create();

        Game game = new Game(dungeon, wumpus, hunter);
        game.start();

//        printDungeon(dungeon);
    }

    private static void printDungeon(Dungeon dungeon) {
        int min = dungeon.getMinRoomNumber();
        int max = dungeon.getMaxRoomNumber();
        for (int number = min; number <= max; number++) {
            System.out.println("Room number: " + number);
            if (dungeon.hasUnit(number)) {
                System.out.println(dungeon.getUnits(number));
            }

            System.out.println(dungeon.getLinkedRoomNumbers(number));
        }
        System.out.println();
    }
}