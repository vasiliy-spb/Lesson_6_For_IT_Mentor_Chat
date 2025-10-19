package dev.cheercode.factory;

import dev.cheercode.Dungeon;
import dev.cheercode.units.Hunter;
import dev.cheercode.units.Wumpus;

public class WumpusNextToRoomDungeonFactory implements DungeonFactory {
    private final Wumpus wumpus;
    private final Hunter hunter;

    public WumpusNextToRoomDungeonFactory(Wumpus wumpus, Hunter hunter) {
        this.wumpus = wumpus;
        this.hunter = hunter;
    }

    @Override
    public Dungeon create() {
        Dungeon dungeon = new Dungeon.Builder(1, 8)
                .linkRoomByNumber(1, 2, 5, 6)
                .linkRoomByNumber(2, 1, 3, 7)
                .linkRoomByNumber(3, 2, 4, 8)
                .linkRoomByNumber(4, 3, 5, 8)
                .linkRoomByNumber(5, 1, 4, 6)
                .linkRoomByNumber(6, 1, 5, 7)
                .linkRoomByNumber(7, 2, 6, 8)
                .linkRoomByNumber(8, 3, 4, 7)
                .build();

        dungeon.addUnit(hunter, 1);
        dungeon.addUnit(wumpus, 5);

        return dungeon;
    }
}
