package tasks;

import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.LootMethods;

import java.util.ArrayList;

public class IdHerbs extends Task {
    public static ArrayList<Integer> idList;
    private Constants c = new Constants();
    private LootMethods l;

    public IdHerbs(Script sA) {
        super(sA);
        l = new LootMethods(sA);
    }

    public String status() {
        return "Identifying herbs";
    }

    public boolean validate() throws InterruptedException {

        if(c.BANK_AREA.contains(sA.myPlayer()) && sA.client.getInventory().contains("Herb")) {
            for (int i : idList) {
               if (sA.client.getInventory().contains(i)) {
                   return true;
                }
            }
        }
        return false;
    }

    public boolean execute() throws InterruptedException {
       l.idHerbs(idList);
       return false;
    }
}

