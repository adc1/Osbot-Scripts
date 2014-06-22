package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.TravelingMethods;

public class ToBank extends Task {
    private Constants c = new Constants();
    private TravelingMethods t;
    public ToBank(Script sA) {
        super(sA);
        t = new TravelingMethods(sA);
    }

    public String status() {
        return "Walking to bank";
    }

    public boolean validate() throws InterruptedException{
        return c.UPSTAIRS_AREA.contains(sA.myPlayer())
               && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"))
               && !c.BANK_AREA.contains(sA.myPlayer())
               && !t.doorExists(c.STAIR_HOUSE);
    }

    public boolean execute() throws InterruptedException {
        return sA.walk(c.BANK_AREA);
    }
}

