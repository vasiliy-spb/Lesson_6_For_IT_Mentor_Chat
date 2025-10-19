package dev.cheercode.units;

import dev.cheercode.Dungeon;

import java.util.Random;

public class Bat extends Unit {
    private final Random random = new Random();

    public void transportHunter(Dungeon dungeon, Hunter hunter) {
        int nextRoomNumber = getNextRoomNumber(dungeon);

        dungeon.removeUnit(hunter);
        dungeon.addUnit(hunter, nextRoomNumber);
    }

    protected int getNextRoomNumber(Dungeon dungeon) {
        int minRoomNumber = dungeon.getMinRoomNumber();
        int maxRoomNumber = dungeon.getMaxRoomNumber();

        return random.nextInt(minRoomNumber, maxRoomNumber + 1);
    }

    @Override
    public String toString() {
        return "Bats{}";
    }
}
