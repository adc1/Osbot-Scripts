package core;

import data.Constants;
import gui.Gui;
import gui.MyPaint;
import org.osbot.script.Script;
import org.osbot.script.ScriptManifest;
import tasks.*;
import utils.MiscMethods;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

@ScriptManifest(author = "adc", info = "Uses sinister keys to get high level herbs", name = "Sinister Stacks", version = 0.1)
public class SinisterStacks extends Script implements MouseListener {



    private Gui gui;
    private MyPaint p;
    private boolean hidden = false;
    private String status = "";
    private String previousStatus = "";
    private Constants c = new Constants();
    private MiscMethods m = new MiscMethods(this);
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private long startTime;



    public void onStart() {
        super.onStart();
        useDefaultPaint(false);
        try {
            p = new MyPaint(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gui = new Gui();
        gui.setSize(300, 400);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        Collections.addAll(tasks,
                new IdHerbs(this),
                new Eat(this),
                new BarsFromChest(this),
                new BarsToChest(this),
                new ChestToPipe(this),
                new DoorFromBank(this),
                new DoorFromChest(this),
                new DoorToBank(this),
                new DoorToChest(this),
                new PipeFromChest(this),
                new PipeToChest(this),
                new StairsDown(this),
                new StairsUp(this),
                new StopScript(this),
                new ToBank(this),
                new Loot(this),
                new Bank(this)
                );
        startTime = System.currentTimeMillis();

    }

    public void onExit() throws InterruptedException {
        //logoutTab.logOut();
    }

    @SuppressWarnings("static-access")
    public void onPaint(Graphics g) {
        if(p != null) {
            if(p.cursor!=null) {
                int mX = client.getMousePosition().x - p.cursor.getWidth(null) / 2;
                int mY = client.getMousePosition().y - p.cursor.getHeight(null) / 2;
                g.drawImage(p.cursor, mX, mY, null);
            }
            final long time = System.currentTimeMillis() - startTime;
            g.setColor(Color.black);
            g.setFont(p.gabriola);


            if (!hidden) {
                g.drawImage(p.paint, c.PAINT_X, c.PAINT_Y, null); // draw the paint
                g.drawString(m.formatTime(time), c.PAINT_X + 10, c.PAINT_Y + 415);
                g.drawImage(p.hideBox, p.paint.getWidth(null)-p.hideBox.getWidth(null)-23, c.PAINT_Y + 7, null);
            } else {
                g.drawImage(p.showBox, p.paint.getWidth(null)-p.showBox.getWidth(null)-23, c.PAINT_Y + 7, null);
            }
        }
    }

    public int onLoop() throws InterruptedException {
        if (gui.run) {
            IdHerbs.idList = gui.idList;
            Bank.idList = gui.idList;
           for(Task t : tasks) {
               if(t.validate()) {
                   previousStatus = status;
                   status = t.status();
                   if(!status.equals(previousStatus)) {
                       log("STATUS: " + status);
                   }
                   if(t.execute()) {
                       return random(200,400);
                   }
               }
           }
        }
        return random(200, 300);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point po;

        Rectangle close = new Rectangle(p.paint.getWidth(null)-p.hideBox.getWidth(null)-23, c.PAINT_Y + 7, 20, 25);

        Rectangle open = new Rectangle(p.paint.getWidth(null)-p.showBox.getWidth(null)-23, c.PAINT_Y + 7, 20, 25);

        po = e.getPoint();


        if (close.contains(po) && !hidden) {
            hidden = true;
        } else if (open.contains(po) && hidden) {
            hidden = false;
        }

    }
}


