package dev.cheercode;

import dev.cheercode.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public static final int MAX_ROOMS = 3;
    private final int number;
    private List<Unit> units;
    private List<Room> linkedRooms;

    public Room(int number) {
        this.number = number;
        this.units = new ArrayList<>();
        this.linkedRooms = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void linkRoom(Room room) {
        if (linkedRooms.contains(room)) {
            return;
        }
        if (getLinkedRoomsSize() > MAX_ROOMS) {
            throw new IllegalArgumentException("Can't add new linked room to room: " + this);
        }

        linkedRooms.add(room);
        room.linkRoom(this);
    }

    public boolean hasAnyUnit() {
        return !units.isEmpty();
    }

    public void removeUnit(Unit unit) {
        boolean result = units.remove(unit);
        if (!result) {
            throw new IllegalArgumentException("Unit not found: " + unit);
        }
    }

    public int getLinkedRoomsSize() {
        return linkedRooms.size();
    }

    public int getNumber() {
        return number;
    }

    public List<Unit> getUnits() {
        return new ArrayList<>(units);
    }

    public List<Room> getLinkedRooms() {
        return new ArrayList<>(linkedRooms);
    }

    @Override
    public String toString() {
        List<String> linkedRoomNumbers = linkedRooms.stream()
                .map(Room::getNumber)
                .map(String::valueOf)
                .toList();

        return "Room{" +
                "number=" + number +
                ", units=" + units +
                ", linkedRoomNumbers=" + linkedRoomNumbers +
                '}';
    }
}
