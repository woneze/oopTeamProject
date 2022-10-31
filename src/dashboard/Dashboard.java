package dashboard;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Dashboard {
    public static String today;
    Scanner fileIn;
    Scanner scan;
    String name;
    static Manager taskMgr = new Manager();
    Manager scheduleMgr = new Manager();


    public Dashboard() {
        this.name = "default";
        getToday();
    }

    void getToday() {
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
        // 포맷 적용
        Dashboard.today = now.format(formatter);
    }

    void run() {
        taskMgr.readAll("task.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Task();
            }
        });
        scheduleMgr.readAll("schedule.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Schedule();
            }
        });

        scheduleMgr.printAll();
        menu();
    }

    void menu() {
        scan = new Scanner(System.in);
        int menuNum;
        while(true) {
            System.out.format("1. 전체 출력\n");
            System.out.format("2. 검색\n");
            System.out.format("3. 수정\n");
            menuNum = scan.nextInt();
            if(menuNum == 0)
                break;
            switch (menuNum) {
                case 1 -> scheduleMgr.printAll();
                case 2 -> search();
                case 3 -> modify();
            }
        }
    }

    void search() {
        String kwd = scan.next();
        System.out.println(kwd);
        scheduleMgr.search(kwd);
    }

    void modify() {
        taskMgr.printAll();
        System.out.print("수정할 Task 번호 선택: ");
        int tskNum = scan.nextInt();
        taskMgr.mList.get(tskNum-1).print();

        System.out.format("1. 이름 재설정\n");
        System.out.format("2. 날짜 변경\n");
        System.out.format("3. 태그 변경\n");
        System.out.format("4. 내용 변경\n");
        int menuNum = scan.nextInt();
        ((Task)(taskMgr.mList.get(tskNum-1))).modify(menuNum, scan);
    }

    public static void main(String[] args) {

        Dashboard d = new Dashboard();
        System.out.println(Dashboard.today);
        d.run();
    }

}