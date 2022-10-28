package dashboard;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule implements Manageable {
    String name;
    ArrayList<Task> taskList = new ArrayList<>();

    @Override
    public void read(Scanner scan) {
        this.name = scan.next();
    }

    @Override
    public void print() {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Task t : taskList) {
            t.print();
        }
        System.out.println("-".repeat(80) + "\n");
    }

    @Override
    public void matches(String kwd) {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Task t : taskList) {
            t.matches(kwd);
        }
        System.out.println("-".repeat(80));
    }
}