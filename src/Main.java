public class Main {
    public static void main(String args[]) {
        parser p = new parser("input.txt");
        char[][] paper = p.getPaper();

        solution sol = new solution(paper, new int[]{7}, new int[]{5});
        sol.foldPaper();
    }
}