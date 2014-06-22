package gui;

import org.osbot.script.Script;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class MyPaint {
    private Script sA;
    public Image cursor;
    public Image paint;
    public Image hideBox;
    public Image showBox;
    public Font gabriola;


    public MyPaint(Script sA) throws InterruptedException {
        this.sA = sA;
        readImages();
    }
    public void readImages() throws InterruptedException, NullPointerException {
        try {
            cursor = ImageIO.read(new File("Images\\Multi\\Cursor.png"));

        } catch (MalformedURLException e) {
            sA.log("Error in retrieving mouse cursor : malformedURL");
        } catch (IOException e) {
            sA.log("Error in retrieving mouse cursor : IOException");
        }
        try {
            paint = ImageIO.read(new File("Images\\SinisterStacks\\Paint.png"));
        } catch (MalformedURLException e) {
            sA.log("Error in retrieving paint : malformedURL");
        } catch (IOException e) {
            sA.log("Error in retrieving paint : IOException");
        }
        try {
            hideBox = ImageIO.read(new File("Images\\SinisterStacks\\HideButton.png"));
        } catch (MalformedURLException e) {
            sA.log("Error in retrieving hideBox : malformedURL");
        } catch (IOException e) {
            sA.log("Error in retrieving hideBox : IOException");
        }
        try {
            showBox = ImageIO.read(new File("Images\\SinisterStacks\\ShowButton.png"));
        } catch (MalformedURLException e) {
            sA.log("Error in retrieving showBox : malformedURL");
        } catch (IOException e) {
            sA.log("Error in retrieving showBox : IOException");
        }
        try {
            // create the font to use. Specify the size!
            gabriola = Font.createFont(Font.TRUETYPE_FONT,
                    new File("Images\\SinisterStacks\\Gabriola.ttf")).deriveFont(Font.BOLD, 19f);
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
                    "Images\\SinisterStacks\\Gabriola.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
