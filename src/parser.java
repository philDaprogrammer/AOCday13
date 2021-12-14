import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class parser {
    private final String fileName;

    public parser(String fileName) { this.fileName = fileName; }

    public char[][] getPaper() {
        char [][] paper = new char[15][11];

        for (int i=0; i < 15; i++) {
            Arrays.fill(paper[i], '.');
        }

        try {
            FileInputStream stream = new FileInputStream(this.fileName);
            Scanner sc = new Scanner(stream);

            while (sc.hasNext()) {
                String line = sc.next();
                String[] coordinates = line.split(",");
                if (coordinates.length == 2) {
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    paper[y][x] = '#';
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paper;
    }
}