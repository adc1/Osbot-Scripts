package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class StairsUp extends Task {

    private Constants c = new Constants();
    private TravelingMethods t;

    public StairsUp(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "climbing on the way to bank";
    }

    public boolean validate() throws InterruptedException{
        return (c.STAIR_BASEMENT.contains(sA.myPlayer())
                && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"))
                && t.stairsExist(c.STAIR_BASEMENT));
    }

    public boolean execute() throws InterruptedException {
        return t.useStairs(c.STAIR_BASEMENT);
    }
}