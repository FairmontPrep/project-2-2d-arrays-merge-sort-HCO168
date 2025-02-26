package picture;

import java.awt.*;

public class ColorAndPos {
    private int rgb;
    private int x;
    private int y;
    public ColorAndPos(int rgb,int x, int y) {
        this.rgb = rgb;
        this.x = x;
        this.y = y;
    }
    public int getRgb() {
        return rgb;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
