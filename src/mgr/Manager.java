package mgr;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public static Scanner fileIn;
    public ArrayList<Manageable> mList = new ArrayList<>();

    protected Scanner openFile(String filename) {

        Scanner filein = null;
        try {
            filein = new Scanner(new File(filename));
        } catch (Exception e) {
            System.out.printf("파일 오픈 실패: %s\n", filename);
            System.exit(0);
        }

        return filein;
    }

    public void readAll(String filename, Factory fac) {

        fileIn = openFile(filename);

        Manageable b = null;
        while (fileIn.hasNext()) {
            b = fac.create();
            b.read(fileIn);
            mList.add(b);
        }
        fileIn.close();
    }//ok

    public void printAll() {
        for (int i = 0; i < mList.size(); i++) {
            System.out.format("(%d) ", i + 1);
            mList.get(i).print();
        }
    }

    public void search(String kwd) {
        for (Manageable m : mList) {
            m.matches(kwd);
        }
    }

}
