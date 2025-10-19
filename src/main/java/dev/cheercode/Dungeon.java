package dev.cheercode;

import dev.cheercode.units.Unit;

import java.util.*;
import java.util.stream.IntStream;

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
            return new ArrayList<>();
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
//        for (Room room : rooms.values()) {
//            if (room.getUnits().contains(unit)) {
//                return room.getNumber();
//            }
//        }
//        throw new IllegalArgumentException("Unit not found: " + unit);

        return rooms.values().stream()
                .filter(room -> room.getUnits().contains(unit))
                .findFirst()
                .map(Room::getNumber)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found: " + unit));
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
//            for (int number = minRoomNumber; number <= maxRoomNumber; number++) {
//                Room room = new Room(number);
//                rooms.put(number, room);
//            }

            IntStream.rangeClosed(minRoomNumber, maxRoomNumber)
                    .forEach(number -> rooms.put(number, new Room(number)));
        }

        public Builder linkRoomByNumber(int baseRoomNumber, int... linkedRoomNumbers) {
            Room room = rooms.get(baseRoomNumber);
//            for (int number : linkedRoomNumbers) {
//                Room nextRoom = rooms.get(number);
//                room.linkRoom(nextRoom);
//            }

            Arrays.stream(linkedRoomNumbers)
                    .forEach(number -> room.linkRoom(rooms.get(number)));

            return this;
        }

        public Dungeon build() {
//            for (Room room : rooms.values()) {
//                validate(room.getNumber());
//
//                int countLinkedRooms = room.getLinkedRooms().size();
//                if (countLinkedRooms != Room.MAX_ROOMS) {
//                    throw new IllegalArgumentException("Illegal linked rooms number: " + countLinkedRooms);
//                }
//            }

//            rooms.values().forEach(room -> validate(room.getNumber()));
//            rooms.values().forEach(room -> validateLinkedRoomsQuantity(room.getLinkedRoomsSize()));

            rooms.values().forEach(this::validate);

            return new Dungeon(rooms, minRoomNumber, maxRoomNumber);
        }

        private void validate(Room room) {
            validate(room.getNumber());
            validateLinkedRoomsQuantity(room.getLinkedRoomsSize());
        }

        private void validateLinkedRoomsQuantity(int quantity) {
            if (quantity != Room.MAX_ROOMS) {
                throw new IllegalArgumentException("Illegal linked rooms number: " + quantity);
            }
        }

        private void validate(int roomNumber) {
            if (roomNumber < minRoomNumber || roomNumber > maxRoomNumber) {
                throw new IllegalArgumentException("Illegal room number" + roomNumber);
            }
        }
    }
}
