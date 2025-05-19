package src.client.model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x; // может быть любым
    private long y;   // ≤ 178

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}