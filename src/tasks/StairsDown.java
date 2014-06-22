package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class StairsDown extends Task {

    private Constants c = new Constants();
    private TravelingMethods t;

    public StairsDown(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "Climbing stairs on the way to chest";
    }

    public boolean validate() throws InterruptedException{
        return (c.UPSTAIRS_AREA.contains(sA.myPlayer())
                && (!sA.client.getInventory().isFull()
                && sA.client.getInventory().contains("Sinister key")
                && sA.client.getInventory().contains("Lockpick"))
                );
    }

    public boolean execute() throws InterruptedException {
        return t.useStairs(c.STAIR_HOUSE);
    }
}

