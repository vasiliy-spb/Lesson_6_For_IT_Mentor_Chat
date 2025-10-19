package dev.cheercode.units;

import dev.cheercode.Dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wumpus extends AliveUnit {
    private final Random random = new Random();

    public void move(Dungeon dungeon) {
        int nextRoom = getNextRoomNumber(dungeon);

        dungeon.removeUnit(this);
        dungeon.addUnit(this, nextRoom);
    }

    private int getNextRoomNumber(Dungeon dungeon) {
        int currentRoomNumber = dungeon.getRoomNumber(this);

        List<Integer> roomNumbers = new ArrayList<>(dungeon.getLinkedRoomNumbers(currentRoomNumber));
        roomNumbers.add(currentRoomNumber);

        int index = random.nextInt(roomNumbers.size());
        return roomNumbers.get(index);
    }

    @Override
    public String toString() {
        return "Wumpus{"
                + "isAlive=" + isAlive() +
                "}";
    }
}
