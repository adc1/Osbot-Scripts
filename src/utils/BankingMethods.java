package utils;

import org.osbot.script.Script;
import org.osbot.script.rs2.model.NPC;

public class BankingMethods {

    private Script sA;
    private MiscMethods m;
    public BankingMethods(Script sA) {
        this.sA = sA;
        m = new MiscMethods(sA);
    }

    public NPC closestBank() throws InterruptedException {
       return (sA.closestNPCForName("Banker"));
    }

    public boolean openBank() throws InterruptedException {
        NPC banker = closestBank();
            if (!sA.client.getBank().isOpen()) {
                if (banker != null) {
                    if (banker.isVisible()) {
                        banker.interact("Bank");
                       m.sleepFor(1500,2000);
                        return true;
                    }
                }
            }
        m.sleepFor(1500,2000);
        return false;
    }

    public boolean depositInv() throws InterruptedException {
        if(sA.client.getBank().isOpen()) {
                if(!sA.client.getInventory().isEmpty()) {
                    sA.client.getBank().depositAll();
                   m.sleepFor(500,750);
                    return true;
                } else {
                    return true;
                }
        }
        return false;
    }

    public boolean withdrawItem(String name, int num) throws InterruptedException {
        if(sA.client.getBank().isOpen()) {
            if (sA.client.getBank().contains(name)) {
                if (sA.client.getBank().getItemForName(name).getAmount() > num) {
                    sA.client.getBank().withdraw(sA.client.getBank().getItemForName(name).getId(), num);
                    return true;
                } else if (sA.client.getBank().getItemForName(name).getAmount() > 1) {
                    sA.client.getBank().withdraw(sA.client.getBank().getItemForName(name).getId(), sA.client.getBank().getItemForName(name).getAmount() - 1);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean closeBank() throws InterruptedException {
        if(sA.client.getBank().isOpen()) {
            sA.client.getBank().close();
            m.sleepFor(300,500);
            return true;
        }
        return false;
    }
}
