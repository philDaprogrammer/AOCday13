public class Instruction {
    public char axis;
    public int  point;

    public Instruction(char axis, int point) {
        this.axis  = axis;
        this.point = point;
    }

    @Override
    public String toString() {
        return this.axis + "=" + this.point;
    }
}
