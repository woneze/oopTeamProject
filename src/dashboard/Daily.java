package dashboard;

import mgr.Manageable;

import java.util.Scanner;

class Daily implements Manageable {
    String name;
    StringBuilder content = new StringBuilder();

    @Override
    public void read(Scanner scan) {
        setName(scan);
        setContent(scan);
    }

    protected void setName(Scanner scan) {
        System.out.format("일정 이름을 입력하세요.");
        this.name = scan.nextLine();
        System.out.print("\b".repeat("일정 이름을 입력하세요.".length()));
    }

    protected void setContent(Scanner scan) {
        System.out.format("일정 내용을 입력하세요.");
        String rewrite = scan.nextLine();
        System.out.print("\b".repeat("일정 내용을 입력하세요.".length()));
        if (rewrite.equals("다시쓰기")) {
            this.content.delete(0, content.length());
            this.content.append(scan.next());
        } else {
            this.content.append(rewrite);
        }
    }

    @Override
    public void print() {
        System.out.format("|*");
        printName();
        System.out.format("| ");
        printContent();
        System.out.format("| ");
        System.out.println();
    }

    protected void printName() {
        System.out.format("[%s] ", name);
    }

    protected void printContent() {
        if (content.length() > 15) {
            System.out.format("%-12s... ", content.substring(0, 13));
        } else
            System.out.format("%-15s ", content);
    }

    protected void modify(int menuChk, Scanner scan) {
        scan.nextLine();
        switch (menuChk) {
            case 1 -> setName(scan);
            case 4 -> {
                System.out.println("\'다시쓰기\' 입력 시 삭제 후 입력");
                System.out.println("그 외 입력 시 덧붙이기");
                setContent(scan);
            }
        }
    }

    @Override
    public void matches(String kwd) {
        if (this.name.contains(kwd) || this.content.toString().contains(kwd))
            print();
    }
}
