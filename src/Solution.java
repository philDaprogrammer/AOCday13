import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Solution {
    private ArrayList<Instruction> instructions;
    private char[][] paper;

    public Solution(String filename) { parse(filename); }

    private void parse(String filename) {
        this.paper        = new char[1500][1500];
        this.instructions = new ArrayList<>();

        for (int i=0; i < 1500; ++i) { Arrays.fill(this.paper[i], '.');}

        try {
            File f            = new File(filename);
            FileReader fr     = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String xReg = "fold along x=[0-9]+";
            String yReg = "fold along y=[0-9]+";

            while ((line = br.readLine()) != null) {
                if (line.matches(xReg)) {
                    this.instructions.add(new Instruction('x', Integer.parseInt(line.split("=")[1])));
                } else if (line.matches(yReg)) {
                    this.instructions.add(new Instruction('y', Integer.parseInt(line.split("=")[1])));
                } else if (!line.isBlank()){
                    String[] coordinates = line.split(",");
                    this.paper[Integer.parseInt(coordinates[1])][Integer.parseInt(coordinates[0])] = '#';
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dumpPaper(char[][] paper) {
        for (char[] chars : paper) {
            System.out.println(Arrays.toString(chars));
        }
    }

    private char[][] foldX(Instruction ins, char[][] paper) {
        char[][] rightHalf = new char[paper.length][ins.point];
        char[][] leftHalf  = new char[paper.length][ins.point];

        for (int i=0; i < paper.length; ++i) {
            System.arraycopy(paper[i], 0, rightHalf[i], 0, ins.point);
            System.arraycopy(paper[i], ins.point + 1, leftHalf[i], 0, ins.point);
        }

        for (int i=0; i < leftHalf.length; ++i) {
            for (int j=leftHalf[i].length - 1; j >= 0; --j) {
                if (leftHalf[i][j] == '#') {
                    rightHalf[i][rightHalf[i].length - 1 - j] = leftHalf[i][j];
                }
            }
        }

        return rightHalf;
    }

    private char[][] foldY(Instruction ins, char[][] paper) {
        char[][] upperHalf  = new char[ins.point][paper[0].length];
        char[][] bottomHalf = new char[ins.point][paper[0].length];

        for (int i=0; i < ins.point; ++i) {
            upperHalf[i]  = paper[i];
            bottomHalf[i] = paper[i + 1 + ins.point];
        }

        for (int i=bottomHalf.length - 1; i >= 0; --i) {
            for (int j=0; j < bottomHalf[i].length; ++j) {
                if (bottomHalf[i][j] == '#') {
                    upperHalf[upperHalf.length - 1 - i][j] = bottomHalf[i][j];
                }
            }
        }

        return upperHalf;
    }

    public int instructionNum() { return instructions.size(); }

    public void solve(int folds) {
        char[][] paper = new char[1500][1500];

        for (int i=0; i < 1500; ++i) { System.arraycopy(this.paper[i], 0, paper[i],0, 1500 ); }

        for (int fold=0; fold < folds; ++fold) {
            Instruction ins = this.instructions.get(fold);

            if (ins.axis == 'x') {
                paper = foldX(ins, paper);
            } else {
                paper = foldY(ins, paper);
            }
        }

        dumpPaper(paper);
    }
}