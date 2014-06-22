package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.LootMethods;

public class Loot extends Task {

    private Constants c = new Constants();
    private LootMethods l;
    public Loot(Script sA) {
        super(sA);
        l = new LootMethods(sA);
    }

    public String status() {
        return "Looting chest";
    }

    public boolean validate() throws InterruptedException{
        return (c.CHEST_ROOM.contains(sA.myPlayer())
                && (!sA.client.getInventory().isFull()
                && sA.client.getInventory().contains("Sinister key")
                && sA.client.getInventory().contains("Lockpick"))
                && l.chestExists(c.CHEST_ROOM));
    }

    public boolean execute() throws InterruptedException {
        return l.lootChest(c.CHEST_ROOM);
    }
}

