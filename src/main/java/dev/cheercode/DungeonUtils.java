package dev.cheercode;

import dev.cheercode.units.Unit;

import java.util.List;

public class DungeonUtils {
    private DungeonUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends Unit> T getUnit(Dungeon dungeon, int roomNumber, Class<T> unitClazz) {
        List<Unit> units = dungeon.getUnits(roomNumber);
        for (Unit unit : units) {
            if (unitClazz.isInstance(unit)) {
                return (T) unit;
            }
        }

        throw new IllegalArgumentException("Unit is absent: " + unitClazz);
    }

    public static boolean isUnitRoom(Dungeon dungeon, int roomNumber, Class<? extends Unit> unitClazz) {
        List<Unit> units = dungeon.getUnits(roomNumber);
        for (Unit unit : units) {
            if (unitClazz.isInstance(unit)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnitNearby(Dungeon dungeon, int roomNumber, Class<? extends Unit> unitClazz) {
        List<Integer> linkedRoomNumbers = dungeon.getLinkedRoomNumbers(roomNumber);
        return linkedRoomNumbers.stream()
                .filter(dungeon::hasUnit)
                .flatMap(number -> dungeon.getUnits(number).stream())
                .anyMatch(unitClazz::isInstance);
    }
}
