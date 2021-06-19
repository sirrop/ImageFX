package jp.gr.java_conf.alpius.imagefx;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.transform.Affine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class GraphicsTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox parent = new VBox();
        WritableImage dst = new WritableImage(500, 500);
        Graphics g = new Graphics(dst);
        ImageView view = new ImageView(dst);

        Button clear = new Button("Clear");
        Button shape = new Button("Shape");
        Button text = new Button("Text");
        Button output = new Button("Output");

        clear.setOnAction(e -> clear(g, dst.getWidth(), dst.getHeight()));
        shape.setOnAction(e -> shape(g));
        text.setOnAction(e -> text(g));
        output.setOnAction(e -> {
            try {
                output(dst);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        parent.getChildren().addAll(view, new HBox(
                clear, shape, text, output
        ));

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void clear(Graphics g, double w, double h) {
        g.clearRect(0, 0, w, h);
    }

    private static void shape(Graphics g) {
        g.setFill(Color.BLUE);
        g.fillRect(10, 10, 200, 300);
        g.setFill(Color.GREEN);
        g.fillOval(100, 150, 200, 300);
        g.setStroke(Color.CRIMSON);
        double width = g.getLineWidth();
        g.setLineWidth(20);
        g.strokeRoundRect(200, 100, 150, 250, 15, 15);
        g.setLineWidth(width);
    }

    private static void text(Graphics g) {
        g.setFont(Font.font("Serif", 50));
        g.setFill(Color.BLUE);
        g.fillText("ImageFX", 10, 60);
        g.setStroke(Color.RED);
        g.strokeText("ImageFX", 10, 240);
        g.setFill(Color.LIGHTBLUE);
        g.setFont(Font.font("Serif", FontPosture.ITALIC, 50));
        Affine affine = g.getTransform();
        g.scale(1, 1.2);
        g.fillText("ImageFX", 10, 340);
        g.setTransform(affine);
    }

    private static void output(WritableImage image) throws Exception {
        FileChooser fc = new FileChooser();
        File file = fc.showSaveDialog(null);
        if (file != null) {
            if (!file.exists()) file.createNewFile();
            BufferedImage result = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(result, "png", file);
        }
    }
}
