package src.shared.model;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    public void setX(double x) {
        this.x = x;
    }
    @Serial
    private static final long serialVersionUID = 1L;

    public void setY(long y) {
        this.y = y;
    }

    private double x;
    private long y; //Максимальное значение поля: 178

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;


    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}