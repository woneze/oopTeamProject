package dashboard;

import mgr.Manageable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Task extends Daily {
    String start_Date;
    String end_Date;
    HashSet<String> tag = new HashSet<>();
    int tagCount = 0;

    @Override
    public void read(Scanner scan) {
        super.read(scan);//name, content input
        setDate(scan);
        scan.nextLine();
        while (setTag(scan)) {
        }
        scan.nextLine();
        scan.nextLine();
    }

    private void setDate(Scanner scan) {
        this.start_Date = scan.next();
        if (start_Date.equals("-"))
            this.end_Date = "-";
        else
            this.end_Date = scan.next();
    }

    private boolean setTag(Scanner scan) {// #tag는 태그 추가 -tag는 태그 삭제
        String hashtag = scan.next();

        return switch (hashtag.charAt(0)) {
            case '#' -> {
                if (tag.size() > 5) {
                    System.out.println("태그 수가 5개 이상입니다.");
                    yield false;
                }
                tag.add(hashtag);
                yield true;
            }
            case '-' -> {
                System.out.format("%s 삭제", "#" + hashtag.substring(1));
                tag.remove("#" + hashtag.substring(1));
                yield false;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + hashtag.charAt(0));
        };
    }

    @Override
    public void print() {
        System.out.format("|");
        printName();
        System.out.format("| ");
        printDate();
        System.out.format("| ");
        printTag();
        System.out.format("| ");
        printContent();
        System.out.println();
    }


    void printDate() {
        System.out.format("%s-%s ", start_Date, end_Date);
        // 출력 자릿수 맞추기
    }

    void printTag() {
        for (String hashtag : tag) {
            if (hashtag == null || hashtag.length() == 0)
                continue;
            System.out.format("%s ", hashtag);
        }
    }

    public int progressLvl() {
        if (start_Date.equals("-"))
            return 1;// waitingList
        if (start_Date.compareTo(Dashboard.today) > 0)
            return 2;//toDo
        else {
            if (end_Date.compareTo(Dashboard.today) >= 0)
                return 3;// inProgress
            else
                return 4;// done
        }
    }

    @Override
    public void modify(int menuChk, Scanner scan) {
        scan.nextLine();
        switch (menuChk) {
            case 1 -> {
                System.out.print("이름 입력: ");
                setName(scan);
            }
            case 2 -> {
                System.out.println("*날짜 입력 양식");
                System.out.format("*| 시작일: mm.dd 마감일: mm.dd|\n");
                System.out.format(" | 날짜 지정 해제 시 \"-\" 입력  |\n");
                System.out.print("날짜 입력: ");
                setDate(scan);
            }
            case 3 -> {
                System.out.println("*태그 입력 양식");
                System.out.format("*| #'태그' 입력 시 태그 생성    |\n");
                System.out.format(" | -'태그' 입력 시 해당 태그 삭제|\n");
                System.out.print("태그 입력: ");
                setTag(scan);
            }
            case 4 -> {
                System.out.println("\'다시쓰기\' 입력 시 삭제 후 입력");
                System.out.println("그 외 입력 시 덧붙이기");
                System.out.print("내용 입력: ");
                setContent(scan);
            }
        }
    }

    @Override
    public void matches(String kwd) {
        if (this.name.contains(kwd) || matchesTag(kwd) || this.content.toString().contains(kwd))
            print();
    }

    boolean matchesTag(String kwd) {
        for (String t : tag) {
            if (t.substring(1).equals(kwd))
                return true;
        }
        return false;
    }
}