package jp.gr.java_conf.alpius.imagefx.filter;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import jp.gr.java_conf.alpius.imagefx.geom.Rect;

import java.awt.geom.Point2D;

public interface ImageFilter {
    void filter(Image src, WritableImage dst);
    Rect getBounds(WritableImage src);
    Point2D getPoint(Point2D srcPt);
}
