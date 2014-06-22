package utils;

import org.osbot.script.MethodProvider;
import org.osbot.script.Script;

public class MiscMethods {

    private Script sA;
    public MiscMethods(Script sA) {
        this.sA = sA;
    }

    public void sleepFor(int min, int max) throws InterruptedException {
    sA.sleep(MethodProvider.random(min, max));
    }


    public void stopScript() throws InterruptedException, NullPointerException {
        if(sA.client.getBank().isOpen()) {
            sA.client.getBank().close();
        }
        sleepFor(500,750);
        sA.stop();
    }

    public boolean isPoisoned() {
        return sA.client.getConfig(102) > 0;
    }

    public boolean curePoison() throws InterruptedException {
        if(isPoisoned()) {
            if (sA.client.getInventory().contains("Antipoison(1)")) {
                sA.client.getInventory().interactWithName("Antipoison(1)", "Drink");
                sleepFor(300,500);
                return true;
            } else if (sA.client.getInventory().contains("Antipoison(2)")) {
                sA.client.getInventory().interactWithName("Antipoison(2)", "Drink");
                sleepFor(300,500);
                return true;
            } else if (sA.client.getInventory().contains("Antipoison(3)")) {
                sA.client.getInventory().interactWithName("Antipoison(3)", "Drink");
                sleepFor(300,500);
                return true;
            } else if (sA.client.getInventory().contains("Antipoison(4)")) {
                sA.client.getInventory().interactWithName("Antipoison(4)", "Drink");
                sleepFor(300,500);
                return true;
            }
        }
        return false;
    }

    public static String formatTime(final long time) {
        final StringBuilder t = new StringBuilder();
        final long total_secs = time / 1000;
        final long total_mins = total_secs / 60;
        final long total_hrs = total_mins / 60;
        final int secs = (int) total_secs % 60;
        final int mins = (int) total_mins % 60;
        final int hrs = (int) total_hrs % 60;
        if (hrs < 10)
            t.append("0");
        t.append(hrs).append(":");
        if (mins < 10)
            t.append("0");
        t.append(mins).append(":");
        if (secs < 10)
            t.append("0");
        t.append(secs);
        return t.toString();
    }

    public boolean eatFood(String name) throws InterruptedException {
        if (sA.client.getInventory().contains(name)) {
            sA.client.getInventory().interactWithAll(sA.client.getInventory().getItemForName(name).getId(), "Eat", true,
                    MethodProvider.random(500, 1000));
            return true;
        }
        return false;
    }
}
