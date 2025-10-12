package dev.cheercode;

import dev.cheercode.dialog.Dialog;
import dev.cheercode.dialog.IntegerDialog;
import dev.cheercode.units.*;

import java.util.List;

public class Game {
    private Dungeon dungeon;
    private Wumpus wumpus;
    private Hunter hunter;

    public Game(Dungeon dungeon, Wumpus wumpus, Hunter hunter) {
        this.dungeon = dungeon;
        this.wumpus = wumpus;
        this.hunter = hunter;
    }

    public void start() {
        System.out.println("=== Hunt The Wumpus ===");
        System.out.println();

        while (true) {
            int hunterRoomNumber = dungeon.getRoomNumber(hunter);

//            Message message = MessageManager.define(dungeon, hunterRoomNumber);

            // String batsNearMessage = messageCenter.getBatsNearbyMessage();

            if (isBatsNearby(hunterRoomNumber)) {
                System.out.println("СЛЫШЕН ШУМ!");
            }

            if (isPitsNearby(hunterRoomNumber)) {
                System.out.println("ПОВЕЯЛО ХОЛОДОМ..");
            }

            if (isWumpusNearby(hunterRoomNumber)) {
                System.out.println("УЖАСНАЯ ВОНЬ!");
            }

            // String positionMessage = messageCenter.getPositionMessage(hunterRoomNumber);
            System.out.println("Вы находитесь в комнате: " + hunterRoomNumber);

            List<Integer> hunterLinkedRoomNumbers = dungeon.getLinkedRoomNumbers(hunterRoomNumber);
            System.out.println("Тоннели ведут: " + hunterLinkedRoomNumbers);

            Dialog<Integer> dialog = new IntegerDialog("Куда пойти?", "Неправильный ввод", hunterLinkedRoomNumbers);

            int number = dialog.input();

            dungeon.removeUnit(hunter);
            dungeon.addUnit(hunter, number);

            System.out.println();
        }
    }

    private boolean isWumpusNearby(int roomNumber) {
        return isNearby(roomNumber, Wumpus.class);
    }

    private boolean isPitsNearby(int roomNumber) {
        return isNearby(roomNumber, Pit.class);
    }

    private boolean isBatsNearby(int roomNumber) {
        return isNearby(roomNumber, Bats.class);
    }

    private boolean isNearby(int roomNumber, Class<? extends Unit> unitClazz) {
        List<Integer> linkedRoomNumbers = dungeon.getLinkedRoomNumbers(roomNumber);
        for (int number : linkedRoomNumbers) {
            if (!dungeon.hasUnit(number)) {
                continue;
            }

            List<Unit> units = dungeon.getUnits(number);
            for (Unit unit : units) {
                if (unitClazz.isInstance(unit)) {
                    return true;
                }
            }
        }
        return false;
    }
}
