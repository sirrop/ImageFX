package jp.gr.java_conf.alpius.imagefx;

import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;
import javafx.scene.effect.Effect;

/**
 * WritableImageに描画を行うための環境です。
 * <P>
 *     このオブジェクトはJavaFX Application Thread上で動作します。
 * </P>
 * <p>
 *     また、このオブジェクトを使用してWritableImageに描画をしている間、
 *     このオブジェクト以外で対象のWritableImageの表現が変化しないようにしてください。
 *     WritableImageのBufferを直接触っているのではなく、内部で同期させているだけのため、
 *     このオブジェクトによらない変更を行った後にこのオブジェクトを使用すると、変更が失われる場合があります。
 * </p>
 * <p>
 *     どうしてもこのオブジェクト以外による変更が必要な場合は、{@link Graphics#updateSurface()}を使用して、
 *     手動で変更をこのオブジェクトに通知してください。
 * </p>
 * @since 1.0
 */
public class Graphics {
    private final WritableImage image;
    private final Canvas surface;
    private final GraphicsContext context;

    public Graphics(WritableImage image) {
        this.image = image;
        surface = new Canvas();
        surface.setWidth(image.getWidth());
        surface.setHeight(image.getHeight());
        context = surface.getGraphicsContext2D();
    }


    public void setFill(Paint paint) {
        context.setFill(paint);
    }
    public Paint getFill() {
        return context.getFill();
    }

    public void setFillRule(FillRule rule) {
        context.setFillRule(rule);
    }

    public FillRule getFillRule() {
        return context.getFillRule();
    }


    public void setFont(Font font) {
        context.setFont(font);
    }
    public Font getFont() {
        return context.getFont();
    }

    public void setFontSmoothingType(FontSmoothingType type) {
        context.setFontSmoothingType(type);
    }

    public FontSmoothingType getFontSmoothingType() {
        return context.getFontSmoothingType();
    }


    public void setGlobalBlendMode(BlendMode blendMode) {
        context.setGlobalBlendMode(blendMode);
    }
    public BlendMode getGlobalBlendMode() {
        return context.getGlobalBlendMode();
    }

    public void setGlobalAlpha(double alpha) {
        context.setGlobalAlpha(alpha);
    }
    public double getGlobalAlpha() {
        return context.getGlobalAlpha();
    }

    public void setLineCap(StrokeLineCap cap) {
        context.setLineCap(cap);
    }

    public StrokeLineCap getLineCap() {
        return context.getLineCap();
    }

    public void setLineDashes(double[] dashes) {
        context.setLineDashes(dashes);
    }

    public double[] getLineDashes() {
        return context.getLineDashes();
    }

    public void setLineDashOffset(double offset) {
        context.setLineDashOffset(offset);
    }

    public double getLineDashOffset() {
        return context.getLineDashOffset();
    }

    public void setLineJoin(StrokeLineJoin join) {
        context.setLineJoin(join);
    }

    public StrokeLineJoin getLineJoin() {
        return context.getLineJoin();
    }

    public void setLineWidth(double width) {
        context.setLineWidth(width);
    }

    public double getLineWidth() {
        return context.getLineWidth();
    }

    public void setMiterLimit(double miterLimit) {
        context.setMiterLimit(miterLimit);
    }

    public double getMiterLimit() {
        return context.getMiterLimit();
    }

    public Paint getStroke() {
        return context.getStroke();
    }

    public void setStroke(Paint paint) {
        context.setStroke(paint);
    }

    public void setTextAlign(TextAlignment textAlign) {
        context.setTextAlign(textAlign);
    }

    public TextAlignment getTextAlign() {
        return context.getTextAlign();
    }

    public void setTextBaseline(VPos baseline) {
        context.setTextBaseline(baseline);
    }

    public VPos getTextBaseline() {
        return context.getTextBaseline();
    }

    public void drawImage(Image img, double x, double y) {
        drawImage(img, x, y, img.getWidth(), img.getHeight());
    }

    public void drawImage(Image img, double x, double y, double w, double h) {
        context.drawImage(img, x, y, w, h);
        markDirty();
    }

    public void drawImage(Image img, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh) {
        context.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
        markDirty();
    }

    public void fillArc(
            double x, double y,
            double w, double h,
            double startAngle, double arcExtent,
            ArcType closure
    ) {
        context.fillArc(x, y, w, h, startAngle, arcExtent, closure);
        markDirty();
    }

    public void fillOval(
            double x, double y, double w, double h
    ) {
        context.fillOval(x, y, w, h);
        markDirty();
    }

    public void fillPolygon(
            double[]  xpts, double[] ypts, int npts
    ) {
        context.fillPolygon(xpts, ypts, npts);
        markDirty();
    }

    public void fillRect(double x, double y, double w, double h) {
        context.fillRect(x, y, w, h);
        markDirty();
    }

    public void fillRoundRect(
            double x, double y, double w, double h, double arcWidth, double arcHeight
    ) {
        context.fillRoundRect(x, y, w, h, arcWidth, arcHeight);
        markDirty();
    }

    public void fillText(String text, double x, double y) {
        context.fillText(text, x, y);
        markDirty();
    }

    public void strokeArc(double x, double y, double w, double h, double startAngle, double arcExtent, ArcType closure) {
        context.strokeArc(x, y, w, h, startAngle, arcExtent, closure);
        markDirty();
    }

    public void strokeLine(double x1, double y1, double x2, double y2) {
        context.strokeLine(x1, y1, x2, y2);
        markDirty();
    }

    public void strokeOval(double x, double y, double w, double h) {
        context.strokeOval(x, y, w, h);
        markDirty();
    }

    public void strokePolygon(double[] xpts, double[] ypts, int npts) {
        context.strokePolygon(xpts, ypts, npts);
        markDirty();
    }

    public void strokePolyline(double[] xpts, double[] ypts, int npts) {
        context.strokePolyline(xpts, ypts, npts);
        markDirty();
    }

    public void strokeRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight) {
        context.strokeRoundRect(x, y, w, h, arcWidth, arcHeight);
        markDirty();
    }

    public void strokeText(String text, double x, double y) {
        context.strokeText(text, x, y);
        markDirty();
    }

    public void strokeText(String text, double x, double y, double maxWidth) {
        context.strokeText(text, x, y, maxWidth);
        markDirty();
    }

    public void transform(Affine xform) {
        context.transform(xform);
    }

    public void transform(
            double mxx, double myx, double mxy, double myy, double mxt, double myt
    ) {
        context.transform(mxx, myx, mxy, myy, mxt, myt);
    }

    public void translate(double x, double y) {
        context.translate(x, y);
    }

    public void scale(double scale) {
        scale(scale, scale);
    }

    public void scale(double sx, double sy) {
        context.scale(sx, sy);
    }

    public void rotate(double deg) {
        context.rotate(deg);
    }

    public Affine getTransform() {
        return context.getTransform();
    }

    public Affine getTransform(Affine xform) {
        return context.getTransform(xform);
    }

    public void updateSurface() {
        context.getPixelWriter()
                .setPixels(
                        0, 0,
                        (int) Math.floor(image.getWidth()), (int) Math.floor(image.getHeight()),
                        image.getPixelReader(),
                        0, 0
                );
    }

    public void appendSVGPath(String svgpath) {
        context.appendSVGPath(svgpath);
    }

    public void applyEffect(Effect e) {
        context.applyEffect(e);
        markDirty();
    }

    public void arc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length) {
        context.arc(centerX, centerY, radiusX, radiusY, startAngle, length);
    }

    public void arcTo(double x1, double y1, double x2, double y2, double radius) {
        context.arcTo(x1, y1, x2, y2, radius);
    }

    public void bezierCurveTo(double xc1, double yc1, double xc2, double yc2, double x1, double y1) {
        context.bezierCurveTo(xc1, yc1, xc2, yc2, x1, y1);
    }

    public void save() {
        context.save();
    }

    public void restore() {
        context.restore();
    }

    public void setTransform(double mxx, double myx, double mxy, double myy, double mxt, double myt) {
        context.setTransform(mxx, myx, mxy, myy, mxt, myt);
    }

    public void setTransform(Affine xform) {
        context.setTransform(xform);
    }

    public void fillText(String text, double x, double y, double maxWidth) {
        context.fillText(text, x, y, maxWidth);
        markDirty();
    }

    public void beginPath() {
        context.beginPath();
    }

    public void moveTo(double x0, double y0) {
        context.moveTo(x0, y0);
    }

    public void lineTo(double x1, double y1) {
        context.lineTo(x1, y1);
    }

    public void quadraticCurveTo(double xc, double yc, double x1, double y1) {
        context.quadraticCurveTo(xc, yc, x1, y1);
    }

    public void rect(double x, double y, double w, double h) {
        context.rect(x, y, w, h);
    }

    public void closePath() {
        context.closePath();
    }

    public void fill() {
        context.fill();
        markDirty();
    }

    public void stroke() {
        context.stroke();
        markDirty();
    }

    public void clip() {
        context.clip();
    }

    public boolean isPointInPath(double x, double y) {
        return context.isPointInPath(x, y);
    }

    public void strokeRect(double x, double y, double w, double h) {
        context.strokeRect(x, y, w, h);
    }

    public PixelWriter getPixelWriter() {
        return context.getPixelWriter();
    }

    public void setEffect(Effect e) {
        context.setEffect(e);
    }

    public Effect getEffect(Effect e) {
        return context.getEffect(e);
    }

    public void clearRect(double x, double y, double w, double h) {
        context.clearRect(x, y, w, h);
        markDirty();
    }

    private final SnapshotParameters params = new SnapshotParameters();

    private void markDirty() {
        snapshot();
    }

    private void snapshot() {
        surface.snapshot(params, image);
    }
}
