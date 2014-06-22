package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class ChestToPipe extends Task {

    private Constants c = new Constants();
    private TravelingMethods t;

    public ChestToPipe(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "Crossing pipe out of chest room";
    }

    public boolean validate() throws InterruptedException{
        return (c.CHEST_ROOM.contains(sA.myPlayer())
                && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"))
                && t.pipeExists(c.CHEST_ROOM));
    }

    public boolean execute() throws InterruptedException {
        return t.usePipe(c.CHEST_ROOM);
    }
}


