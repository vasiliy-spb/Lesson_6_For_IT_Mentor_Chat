package dev.cheercode.units;

public abstract class AliveUnit extends Unit {
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
}
