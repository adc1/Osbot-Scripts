package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class DoorToBank extends Task {

    private Constants c = new Constants();
    private TravelingMethods t;

    public DoorToBank(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "Opening door on the way to bank";
    }

    public boolean validate() throws InterruptedException{
        return (c.STAIR_HOUSE.contains(sA.myPlayer())
               && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"))
               && t.doorExists(c.STAIR_HOUSE));
    }

    public boolean execute() throws InterruptedException {
        return t.openDoor(c.STAIR_HOUSE);
    }
}

