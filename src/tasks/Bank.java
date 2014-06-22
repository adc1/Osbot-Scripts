package tasks;

        import core.Task;
import data.Constants;
import org.osbot.script.Script;
import utils.BankingMethods;

import java.util.ArrayList;

public class Bank extends Task {
    public static ArrayList<Integer> idList;
    private Constants c = new Constants();
    private BankingMethods b;

    public Bank(Script sA) {
        super(sA);
        b = new BankingMethods(sA);
    }

    public String status() {
        return "Banking";
    }

    public boolean validate() throws InterruptedException {
        if (sA.client.getInventory().contains("Herb")) {
            if (idList != null) {
                int[] arr = new int[idList.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = idList.get(i);
                    if (sA.client.getInventory().contains(arr[i])) {
                        return false;
                    }
                }
            }

        }
        return c.BANK_AREA.contains(sA.myPlayer())
                && (sA.client.getInventory().isFull()
                || !sA.client.getInventory().contains("Sinister key")
                || !sA.client.getInventory().contains("Lockpick"));
    }
    public boolean execute() throws InterruptedException {
        if(!sA.client.getBank().isOpen()) {
        b.openBank();
        } else {
            if (b.depositInv()) {
                if (b.withdrawItem("Sinister key", 3)) {
                    if (b.withdrawItem("Lockpick", 1)) {
                        if(sA.myPlayer().getHealth() < 75) {
                            if (b.withdrawItem("Lobster", 4)) {
                                return b.closeBank();
                            }
                        } else {
                            return b.closeBank();
                        }
                    }
                }
            }
        }
        return false;
    }
}

