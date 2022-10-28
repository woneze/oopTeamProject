package dashboard;

import mgr.Manageable;
import mgr.Manager;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule implements Manageable {
    String name;
    ArrayList<Manageable> taskList = new ArrayList<>();
    int schCateNum;//1, 2, 3, 4 중에서만 받아야 함. 1:wtList, 2:to do, 3:inpro, 4:done

    @Override
    public void read(Scanner scan) {
        this.name = scan.nextLine();
        this.schCateNum = scan.nextInt();
        scan.nextLine();
        for(Manageable t: Dashboard.taskMgr.mList){
            if(schCateNum == ((Task)t).progressLvl())
                taskList.add(t);
        }
    }

    @Override
    public void print() {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Manageable t : taskList) {
            t.print();
        }
        System.out.println("-".repeat(80) + "\n");
    }

    @Override
    public void matches(String kwd) {
        System.out.format("<%s>\n", name);
        System.out.println("-".repeat(80));
        for (Manageable t : taskList) {
            t.matches(kwd);
        }
        System.out.println("-".repeat(80));
    }


}