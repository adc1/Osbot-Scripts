package tasks;

import core.Task;
import org.osbot.script.Script;
import utils.BankingMethods;
import utils.MiscMethods;

public class StopScript extends Task {
    private MiscMethods m;
    private BankingMethods b;

    public StopScript(Script sA) {
        super(sA);
        m = new MiscMethods(sA);
        b = new BankingMethods(sA);
    }

    public String status() {
        return "Stopping script";
    }

    public boolean validate() throws InterruptedException {
        if (sA.client.getBank().isOpen()) {
            if (sA.client.getBank().contains("Sinister key")){
                if (sA.client.getBank().getAmount(sA.client.getBank().getItemForName("Sinister key").getId()) <= 1) {
                    return true;
                }
            } else {
                return true;
            }
            if (sA.client.getBank().contains("Lockpick")) {
                if( sA.client.getBank().getAmount(sA.client.getBank().getItemForName("Lockpick").getId()) <= 1) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean execute() throws InterruptedException {
        if(m.isPoisoned()) {
            if (b.openBank() || sA.client.getBank().isOpen()) {
                if(!b.withdrawItem("Antipoison(1)", 1)) {
                    if(!b.withdrawItem("Antipoison(2)", 1)) {
                        if(!b.withdrawItem("Antipoison(3)", 1)) {
                            b.withdrawItem("Antipoison(4)", 1);
                        }
                    }
                }
            }
        }
        b.closeBank();
        m.curePoison();
        m.stopScript();
        return false;
    }
}
