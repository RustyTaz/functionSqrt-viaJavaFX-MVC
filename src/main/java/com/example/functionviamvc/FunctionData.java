package com.example.functionviamvc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class FunctionData {
    private final DoubleProperty x;
    private final DoubleProperty y;

    public FunctionData(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }
}
