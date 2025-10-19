package dev.cheercode.units;

import dev.cheercode.Dungeon;
import dev.cheercode.DungeonUtils;

public class Hunter extends AliveUnit {
    private int arrows;

    public Hunter(int arrows) {
        this.arrows = arrows;
    }

    public boolean hasArrows() {
        return arrows > 0;
    }

    public int getArrows() {
        return arrows;
    }

    public void move(Dungeon dungeon, int roomNumber) {
        if (!isAlive()) {
            throw new IllegalArgumentException("Hunter is not alive: " + this);
        }

        dungeon.removeUnit(this);
        dungeon.addUnit(this, roomNumber);
    }

    public void shot(Dungeon dungeon, int roomNumber) {
        if (!hasArrows()) {
            throw new IllegalArgumentException("Hunter has no arrows.");
        }

        arrows--;
        if (!isWumpusRoom(dungeon, roomNumber)) {
            return;
        }

        Wumpus wumpus = getWumpus(dungeon, roomNumber);
        wumpus.kill();
    }

    private Wumpus getWumpus(Dungeon dungeon, int roomNumber) {
        return DungeonUtils.getUnit(dungeon, roomNumber, Wumpus.class);
    }

    private boolean isWumpusRoom(Dungeon dungeon, int roomNumber) {
        return DungeonUtils.isUnitRoom(dungeon, roomNumber, Wumpus.class);
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "arrows=" + arrows +
                "; isAlive=" + isAlive() +
                '}';
    }
}
