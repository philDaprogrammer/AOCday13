import java.util.Arrays;

public class solution {
    private final char[][] paper;
    private final int[]    xFolds;
    private final int[]    yFolds;

    public solution(char[][] paper, int[] yFolds, int xFolds[]) {
        this.paper  = paper;
        this.xFolds = xFolds;
        this.yFolds = yFolds;
    }

    private char[][] foldY(char[][] paper, int y) {
        char [][] lowerHalf = Arrays.copyOfRange(paper, 0, y);
        char [][] upperHalf = Arrays.copyOfRange(paper, y+1, paper.length);
        int k = 0;

        for (int i=upperHalf.length-1; i >= 0; i--) {
            for (int j = 0; j < upperHalf[i].length; j++) {
                if (upperHalf[i][j] == '#') {
                    lowerHalf[k][j] = upperHalf[i][j];
                }
            }
            k++;
        }

        return lowerHalf;
    }

    private char[][] foldX(char[][] paper, int x) {
        char [][] rightHalf = new char[paper.length][x];
        char [][] leftHalf  = new char[paper.length][x];
        int k = 0;

        for (int i=0; i < paper.length; i++) {
            leftHalf[i]  = Arrays.copyOfRange(paper[i], 0, x);
            rightHalf[i] = Arrays.copyOfRange(paper[i], x+1, paper[i].length);
        }

        for (int i=0 ; i < rightHalf.length; i++) {
            for (int j=rightHalf[i].length-1; j >= 0; j--) {
                if (rightHalf[i][j] == '#') {
                    leftHalf[i][k] = rightHalf[i][j];
                }
                k++;
            }
            k=0;
        }

        return leftHalf;
    }

    public void foldPaper() {
        char[][] folded = this.paper;

        for (int yFold : this.yFolds)
            folded = this.foldY(folded, yFold);

        for (int xFold : this.xFolds)
            folded = this.foldX(folded, xFold);

        this.printSol(folded);
    }

    private void printSol(char[][]paper) {
        for (char[] chars : paper) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }

            System.out.println();
        }
    }
}