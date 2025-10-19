package dev.cheercode;

import dev.cheercode.dialog.Dialog;
import dev.cheercode.dialog.IntegerDialog;
import dev.cheercode.dialog.StringDialog;
import dev.cheercode.units.*;

import java.util.List;

public class Game {
    private static final String MOVE_KEY = "m";
    private static final String SHOT_KEY = "s";
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

        Dialog<String> selectActionDialog = getSelectActionDialog();

        while (!isGameOver()) {
            int hunterRoomNumber = dungeon.getRoomNumber(hunter);
            List<Integer> hunterLinkedRoomNumbers = dungeon.getLinkedRoomNumbers(hunterRoomNumber);

            showPositionMessage(hunterRoomNumber, hunterLinkedRoomNumbers);

            String action = selectActionDialog.input();
            Dialog<Integer> moveDialog = new IntegerDialog("Куда?", "Неправильный ввод", hunterLinkedRoomNumbers);
            int roomNumber = moveDialog.input();

            if (isMoveAction(action)) {
                hunter.move(dungeon, roomNumber);

                if (isBatRoom(roomNumber)) {
                    System.out.println("ЛЕТУЧИЕ МЫШИ ПЕРЕНЕСЛИ ВАС КУДА-ТО!");
                    Bat bat = getBat(roomNumber);
                    bat.transportHunter(dungeon, hunter);

                    roomNumber = dungeon.getRoomNumber(hunter);
                }

                if (isPitRoom(roomNumber)) {
                    System.out.println("ВЫ ПРОВАЛИЛИСЬ В ЯМУ-У-У-У-у-у-у");
                    hunter.kill();
                    continue;
                }
            }

            if (isShotAction(action)) {
                hunter.shot(dungeon, roomNumber);
                if (!hunter.hasArrows()) {
                    System.out.println("ЗАКОНЧИЛИСЬ СТРЕЛЫ.. ВАШИ МИНУТЫ СОЧТЕНЫ..");
                }

                if (!wumpus.isAlive()) {
                    System.out.println("ВЫ ПОДСТРЕЛИЛИ ВУМПУСА!");
                    System.out.println("РАДУЙТЕСЬ ПОКА МОЖЕТЕ.. ВУМПУС ПОЙМАЕТ ВАС В СЛЕДУЮЩИЙ РАЗ!");
                    continue;
                }

                if (isWumpusNearby(hunterRoomNumber)){
                    System.out.println("ТРЕПЕЩИТЕ!! ВЫ РАЗБУДИЛИ ВУМПУСА...");
                    wumpus.move(dungeon);
                }
            }

            if (isWumpusRoom(roomNumber)) {
                System.out.println("ВАС РАСТЕРЗАЛ ВУМПУС!");
                hunter.kill();
                continue;
            }

            System.out.println();
        }

        if (isWon()) {
            System.out.println("ВЫ ПОБЕДИЛИ!");
        }
        if (isLose()) {
            System.out.println("ВЫ ПРОИГРАЛИ..");
        }
    }

    private void showPositionMessage(int hunterRoomNumber, List<Integer> hunterLinkedRoomNumbers) {
        if (isBatNearby(hunterRoomNumber)) {
            System.out.println("СЛЫШЕН ШУМ!");
        }

        if (isPitsNearby(hunterRoomNumber)) {
            System.out.println("ПОВЕЯЛО ХОЛОДОМ..");
        }

        if (isWumpusNearby(hunterRoomNumber)) {
            System.out.println("УЖАСНАЯ ВОНЬ!");
        }

        System.out.println("Вы находитесь в комнате: " + hunterRoomNumber);

        System.out.println("Тоннели ведут: " + hunterLinkedRoomNumbers);

        System.out.println("Количество стрел: " + hunter.getArrows());
    }

    private StringDialog getSelectActionDialog() {
        return new StringDialog(
                "Ходить или стрелять? (%s / %s)".formatted(MOVE_KEY, SHOT_KEY),
                "Неправильный ввод",
                List.of(MOVE_KEY, SHOT_KEY)
        );
    }

    private Bat getBat(int roomNumber) {
        return DungeonUtils.getUnit(dungeon, roomNumber, Bat.class);
    }

    private boolean isBatRoom(int roomNumber) {
        return DungeonUtils.isUnitRoom(dungeon, roomNumber, Bat.class);
    }

    private boolean isWumpusRoom(int roomNumber) {
        return DungeonUtils.isUnitRoom(dungeon, roomNumber, Wumpus.class);
    }

    private boolean isPitRoom(int roomNumber) {
        return DungeonUtils.isUnitRoom(dungeon, roomNumber, Pit.class);
    }

    private boolean isGameOver() {
        return isLose() || isWon();
    }

    private boolean isWon() {
        return !wumpus.isAlive();
    }

    private boolean isLose() {
        return !hunter.isAlive() || !hunter.hasArrows();
    }

    private boolean isShotAction(String action) {
        return action.equalsIgnoreCase(SHOT_KEY);
    }

    private boolean isMoveAction(String action) {
        return action.equalsIgnoreCase(MOVE_KEY);
    }

    private boolean isWumpusNearby(int roomNumber) {
        return DungeonUtils.isUnitNearby(dungeon, roomNumber, Wumpus.class);
    }

    private boolean isPitsNearby(int roomNumber) {
        return DungeonUtils.isUnitNearby(dungeon, roomNumber, Pit.class);
    }

    private boolean isBatNearby(int roomNumber) {
        return DungeonUtils.isUnitNearby(dungeon, roomNumber, Bat.class);
    }
}
