package dashboard;

import mgr.Manageable;

import java.util.ArrayList;
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
        this.name = scan.nextLine();
    }
    protected void setContent(Scanner scan) {
        String rewrite = scan.nextLine();
        if (rewrite.equals("다시쓰기")) {
            this.content.delete(0, content.length());
            this.content.append(scan.next());
        } else {
            this.content.append(rewrite);
        }
    }

    @Override
    public void print() {
        System.out.format("||");
        printName();
        printContent();
        System.out.println();
    }

    protected void printName() {
        if (name.length() > 10) {
            System.out.format("*[%s...] | ", name.substring(0, 8));
        } else
            System.out.format("*[%s] | ", name);
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
