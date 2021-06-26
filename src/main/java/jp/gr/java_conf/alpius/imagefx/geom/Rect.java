package jp.gr.java_conf.alpius.imagefx.geom;

import java.util.Objects;

public final class Rect {
    private final double l;
    private final double t;
    private final double r;
    private final double b;

    private Rect(double l, double t, double r, double b) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
    }

    public static Rect makeWH(double w, double h) {
        return makeXYWH(0, 0, w, h);
    }

    public static Rect makeXYWH(double x, double y, double w, double h) {
        return new Rect(
                x,
                y,
                x + w,
                y + h
        );
    }

    public static Rect makeLTRB(double l, double t, double r, double b) {
        return new Rect(l, t, r, b);
    }

    public double getMinX() {
        return l;
    }

    public double getMinY() {
        return t;
    }

    public double getMaxX() {
        return r;
    }

    public double getMaxY() {
        return b;
    }

    public double getWidth() {
        return b - t;
    }

    public double getHeight() {
        return r - l;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rect rect = (Rect) o;
        return Double.compare(rect.l, l) == 0 && Double.compare(rect.t, t) == 0 && Double.compare(rect.r, r) == 0 && Double.compare(rect.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(l, t, r, b);
    }

    @Override
    public String toString() {
        return "Rect{" +
                "minX=" + l +
                ", minY=" + t +
                ", maxX=" + r +
                ", maxY=" + b +
                '}';
    }
}
