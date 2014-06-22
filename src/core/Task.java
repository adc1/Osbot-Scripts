package core;

import org.osbot.script.Script;

public abstract class Task {

    public Script sA;

    public Task(Script sA) {
        this.sA = sA;
    }

    public String status() {
        return "";
    }

    public abstract boolean validate() throws InterruptedException;

    public abstract boolean execute() throws InterruptedException;
}
