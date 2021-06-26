package jp.gr.java_conf.alpius.imagefx.filter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import jp.gr.java_conf.alpius.imagefx.geom.Rect;

import java.awt.geom.Point2D;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ConvolveFilter implements ImageFilter {
    private final Kernel kernel;
    private final EdgeType edgeType;
    private final ConvolveOp operation;

    public ConvolveFilter(Kernel kernel, EdgeType edgeType) {
        this.kernel = kernel;
        this.edgeType = edgeType;
        operation = new ConvolveOp(kernel, edgeType.getAWTConstant(), null);
    }

    @Override
    public void filter(Image src, WritableImage dst) {
        var res = SwingFXUtils.fromFXImage(src, null);
        res = operation.filter(res, res);
        dst.getPixelWriter()
                .setPixels(
                        0, 0, res.getWidth(), res.getHeight(),
                        PixelFormat.getIntArgbInstance(),
                        res.getRGB(
                                0, 0, res.getWidth(), res.getHeight(), null, 0, res.getWidth()
                        ),
                        0, res.getWidth()
                );
    }

    @Override
    public Rect getBounds(WritableImage src) {
        return Rect.makeWH(src.getWidth(), src.getHeight());
    }

    @Override
    public Point2D getPoint(Point2D srcPt) {
        return srcPt;
    }

    public Kernel getKernel() {
        return kernel;
    }

    public EdgeType getEdgeType() {
        return edgeType;
    }
}
