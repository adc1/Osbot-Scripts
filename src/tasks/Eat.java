package tasks;

import core.Task;
import org.osbot.script.Script;
import utils.MiscMethods;

public class Eat extends Task {

    private MiscMethods m;

    public Eat(Script sA) {
        super(sA);
        m = new MiscMethods(sA);
    }

    public String status() {
        return "Eating";
    }

    public boolean validate() throws InterruptedException{
        return sA.client.getInventory().contains("Lobster");
    }

    public boolean execute() throws InterruptedException {
        m.eatFood("Lobster");
        return true;
    }
}

