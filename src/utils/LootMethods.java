package utils;

import org.osbot.script.MethodProvider;
import org.osbot.script.Script;
import org.osbot.script.rs2.model.RS2Object;
import org.osbot.script.rs2.utility.Area;

import java.util.ArrayList;
import java.util.Arrays;

public class LootMethods {
    private Script sA;
    private MiscMethods m;
    public LootMethods(Script sA) {
        this.sA = sA;
        m = new MiscMethods(sA);
    }

    public boolean chestExists(Area area) throws InterruptedException {
        RS2Object door = sA.closestObjectForName(area, "Closed chest");
        return (door != null && Arrays.asList(door.getDefinition().getActions()).contains("Open"));
    }

    public boolean lootChest(Area area) throws InterruptedException {
        RS2Object chest = sA.closestObjectForName(area, "Closed chest");
        if(chestExists(area)) {
            sA.client.getInventory().interactWithName("Sinister key", "use");
            chest.interact("Use", "Sinister key -> Closed Chest", false, 0, true, false);
            m.sleepFor(2500,3000);
            return true;
        }
        return false;
    }

    public void idHerbs(ArrayList<Integer> idList) throws InterruptedException, NullPointerException {
        if(idList != null) {
            int[] arr = new int[idList.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = idList.get(i);
                if (sA.client.getInventory().contains(arr[i])) {
                    m.sleepFor(100,200);
                    sA.client.getInventory().interactWithAll(arr[i], "Identify", false, MethodProvider.random(500, 750));
                }

            }
        }
    }
}