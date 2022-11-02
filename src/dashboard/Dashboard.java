package dashboard;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Dashboard {
    public static String today;
    Scanner scan;
    static Manager<Task> taskMgr = new Manager<>();
    static Manager<Schedule> scheduleMgr = new Manager<>();

    String getToday() {
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
        // 포맷 적용
        return now.format(formatter);
    }

    void run() {
        System.out.format("<오늘 날짜: %s >\n\n", today = getToday());
        taskMgr.readAll("task.txt", new Factory<Task>() {
            @Override
            public Task create() {
                return new Task();
            }
        });
        scheduleMgr.readAll("schedule.txt", new Factory<Schedule>() {
            @Override
            public Schedule create() {
                return new Schedule();
            }
        });

        scheduleMgr.printAll();
        menu();
    }

    void menu() {
        scan = new Scanner(System.in);
        int menuNum;
        while (true) {
            System.out.format("\t0. 종료\n");
            System.out.format("\t1. 전체 출력\n");
            System.out.format("\t2. 검색\n");
            System.out.format("\t3. 수정\n");
            System.out.format("\t4. 일정 추가\n");
            System.out.format("\t5. 일정 삭제\n");
            menuNum = scan.nextInt();
            if (menuNum == 0)
                break;
            switch (menuNum) {
                case 1 -> scheduleMgr.printAll();
                case 2 -> search();
                case 3 -> modify();
                case 4 -> {
                    Task t = new Task();
                    t.read(scan);
                    taskMgr.mList.add(t);
                }
                case 5 -> {
                    taskMgr.printAll();
                    System.out.print("\n삭제할 Task 번호 선택: ");
                    int tskNum = scan.nextInt();
                    taskMgr.mList.get(tskNum - 1).print();
                    System.out.println("삭제되었습니다.");
                    taskMgr.mList.remove(tskNum - 1);
                }
            }
        }
    }

    void search() {
        System.out.format("검색 키워드 입력: ");
        String kwd = scan.next();
        System.out.println();
        scheduleMgr.search(kwd);
    }

    void modify() {
        taskMgr.printAll();
        System.out.print("\n수정할 Task 번호 선택: ");
        int tskNum = scan.nextInt();
        taskMgr.mList.get(tskNum - 1).print();

        System.out.format("\n\t1. 이름 재설정\n");
        System.out.format("\t2. 날짜 변경\n");
        System.out.format("\t3. 태그 변경\n");
        System.out.format("\t4. 내용 변경\n");

        taskMgr.mList.get(tskNum - 1).modify(scan.nextInt(), scan);
        for(Schedule s:Dashboard.scheduleMgr.mList){
            s.classify();
        }
    }

    public static void main(String[] args) {

        Dashboard d = new Dashboard();
        d.run();
    }
}