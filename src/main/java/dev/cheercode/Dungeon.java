package dev.cheercode;

import dev.cheercode.units.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dungeon {
    private final Map<Integer, Room> rooms;
    private final int minRoomNumber;
    private final int maxRoomNumber;

    private Dungeon(Map<Integer, Room> rooms, int minRoomNumber, int maxRoomNumber) {
        this.rooms = rooms;
        this.minRoomNumber = minRoomNumber;
        this.maxRoomNumber = maxRoomNumber;
    }

    public void addUnit(Unit unit, int roomNumber) {
        validate(roomNumber);

        Room room = rooms.get(roomNumber);
        room.addUnit(unit);
    }

    public void removeUnit(Unit unit) {
        int roomNumber = getRoomNumber(unit);
        Room room = rooms.get(roomNumber);
        room.removeUnit(unit);
    }

    public List<Unit> getUnits(int roomNumber) {
        validate(roomNumber);

        Room room = rooms.get(roomNumber);
        if (!hasUnit(roomNumber)) {
            throw new IllegalArgumentException("Room is empty: " + room);
        }

        return room.getUnits();
    }

    public boolean hasUnit(int roomNumber) {
        validate(roomNumber);

        Room room = rooms.get(roomNumber);
        return room.hasAnyUnit();
    }

    public List<Integer> getLinkedRoomNumbers(int roomNumber) {
        validate(roomNumber);

        Room room = rooms.get(roomNumber);
        List<Room> linkedRooms = room.getLinkedRooms();

        return linkedRooms.stream()
                .map(Room::getNumber)
                .toList();
    }

    public int getMinRoomNumber() {
        return minRoomNumber;
    }

    public int getMaxRoomNumber() {
        return maxRoomNumber;
    }

    public int getRoomNumber(Unit unit) {
        for (Room room : rooms.values()) {
            if (room.getUnits().contains(unit)) {
                return room.getNumber();
            }
        }
        throw new IllegalArgumentException("Unit not found: " + unit);
    }

    private void validate(int roomNumber) {
        if (roomNumber < minRoomNumber || roomNumber > maxRoomNumber) {
            throw new IllegalArgumentException("Illegal room number" + roomNumber);
        }
    }

    public static class Builder {
        private final int minRoomNumber;
        private final int maxRoomNumber;
        private Map<Integer, Room> rooms;

        public Builder(int minRoomNumber, int maxRoomNumber) {
            this.minRoomNumber = minRoomNumber;
            this.maxRoomNumber = maxRoomNumber;
            this.rooms = new HashMap<>();
            for (int number = minRoomNumber; number <= maxRoomNumber; number++) {
                Room room = new Room(number);
                rooms.put(number, room);
            }
        }

        public Builder linkRoomByNumber(int baseRoomNumber, int... linkedRoomNumbers) {
            Room room = rooms.get(baseRoomNumber);
            for (int number : linkedRoomNumbers) {
                Room nextRoom = rooms.get(number);
                room.linkRoom(nextRoom);
            }
            return this;
        }

        public Dungeon build() {
            for (Room room : rooms.values()) {
                validate(room.getNumber());

                int countLinkedRooms = room.getLinkedRooms().size();
                if (countLinkedRooms != Room.MAX_ROOMS) {
                    throw new IllegalArgumentException("Illegal linked rooms number: " + countLinkedRooms);
                }
            }

            return new Dungeon(rooms, minRoomNumber, maxRoomNumber);
        }

        private void validate(int roomNumber) {
            if (roomNumber < minRoomNumber || roomNumber > maxRoomNumber) {
                throw new IllegalArgumentException("Illegal room number" + roomNumber);
            }
        }
    }
}
