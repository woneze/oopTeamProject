package dashboard;

import java.util.ArrayList;

public class Schedule {
    String name;
    ArrayList<Task> taskList = new ArrayList<>();

    void read (String name) {
        this.name = name;
    }

    void print() {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Task t : taskList) {
            t.print();
        }
        System.out.println("-".repeat(80)+"\n");
    }

    void matches(String kwd) {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Task t : taskList) {

            if (t.matches(kwd)) {
                t.print();
            }
        }
        System.out.println("-".repeat(80));
    }
}