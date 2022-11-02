package dashboard;

import mgr.Manageable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Schedule implements Manageable {
    String name;
    HashSet<Task> taskList = new HashSet<>();
    int schCateNum;//1, 2, 3, 4 중에서만 받아야 함. 1:wtList, 2:to do, 3:inpro, 4:done

    @Override
    public void read(Scanner scan) {
        this.name = scan.nextLine();
        this.schCateNum = scan.nextInt();
        scan.nextLine();
        classify();
    }

    void classify(){
        for (Task t : Dashboard.taskMgr.mList) {
            if (schCateNum == t.progressLvl())
                taskList.add(t);
            else
                taskList.remove(t);
        }
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
        System.out.println();
    }
}