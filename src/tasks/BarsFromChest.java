package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class BarsFromChest extends Task {

    private Constants c = new Constants();
    private TravelingMethods t;

    public BarsFromChest(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "Crossing bars on the way to bank";
    }

    public boolean validate() throws InterruptedException{
        return (c.OUTSIDE_PIPE.contains(sA.myPlayer())
                && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"))
                && t.barsExist(c.OUTSIDE_PIPE));
    }

    public boolean execute() throws InterruptedException {
        return t.swingBars(c.OUTSIDE_PIPE);
    }
}


