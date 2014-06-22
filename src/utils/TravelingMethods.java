package utils;

import org.osbot.script.Script;
import org.osbot.script.rs2.model.RS2Object;
import org.osbot.script.rs2.utility.Area;

import java.util.Arrays;

public class TravelingMethods {
    private Script sA;
    private MiscMethods m;

    public TravelingMethods(Script sA) {
        this.sA = sA;
        m = new MiscMethods(sA);
    }

    public boolean doorExists(Area area) throws InterruptedException {
        RS2Object door = sA.closestObjectForName(area, "Door");
        return (Arrays.asList(door.getDefinition().getActions()).contains("Open"));
    }

    public boolean specialDoor(Area area) throws InterruptedException {
        RS2Object door = sA.closestObjectForName(area, "Door");
        return (Arrays.asList(door.getDefinition().getActions()).contains("Pick-lock"));
    }

    public boolean stairsExist(Area area) throws InterruptedException {
        RS2Object stairs = sA.closestObjectForName(area, "Staircase");
        return (stairs != null);
    }

    public boolean barsExist (Area area) throws InterruptedException {
        RS2Object bars = sA.closestObjectForName(area, "Monkeybars");
        return (bars != null);
    }

    public boolean pipeExists (Area area) throws InterruptedException {
        RS2Object pipe = sA.closestObjectForName(area, "Obstacle pipe");
        return (pipe != null);
    }

    public boolean swingBars(Area area) throws InterruptedException {
        RS2Object bars = sA.closestObjectForName(area, "Monkeybars");
        if(barsExist(area)) {
            if(!sA.myPlayer().isAnimating()) {
                bars.interact("Swing across");
                m.sleepFor(300, 500);
                return true;
            }
        }
        return false;
    }

    public boolean useStairs(Area area) throws InterruptedException {
        RS2Object stairs = sA.closestObjectForName(area, "Staircase");
        if(stairsExist(area)) {
            if (Arrays.asList(stairs.getDefinition().getActions()).contains("Climb-down")) {
                stairs.interact("Climb-down");
                m.sleepFor(1500,2000);
                return true;
            } else if (Arrays.asList(stairs.getDefinition().getActions()).contains("Climb-up")) {
                stairs.interact("Climb-up");
                m.sleepFor(1500,2000);
                return true;
            }
        }
        return false;
    }
    public boolean usePipe(Area area) throws InterruptedException {
        RS2Object pipe = sA.closestObjectForName(area, "Obstacle pipe");
        if(pipeExists(area)) {
            pipe.interact("Squeeze-through");
            m.sleepFor(300,500);
            return true;
        }
        return false;
    }
    public boolean openDoor(Area area) throws InterruptedException {
        RS2Object door = sA.closestObjectForName(area, "Door");
        if(doorExists(area)) {
                if (specialDoor(area)) {
                    if (!sA.myPlayer().isAnimating()) {
                        door.interact("Pick-lock");
                        m.sleepFor(300,500);
                        return true;
                    }
                } else {
                    door.interact("Open");
                    m.sleepFor(300,500);
                    return true;
                }
        }
        return false;
    }
}