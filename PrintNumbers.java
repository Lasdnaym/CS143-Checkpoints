public class PrintNumbers {
    public static void printNumbers3(int valueA, int timesA, int valueC, int timesC) {
        if (valueA < 0) {
            throw new IllegalArgumentException();
        } else if (timesA < 0) {
            throw new IllegalArgumentException();
        } else if (valueC < 0) {
            throw new IllegalArgumentException();
        } else if (timesC < 0) {
            throw new IllegalArgumentException();
        }
        printNumHelp(timesA, timesC, "", valueA, valueC);
    }

    // Where as is the number of a's and cs is the number of c's
    private static void printNumHelp(int as, int cs, String s, int valueA, int valueC) {
        if (as == 0 && cs == 0) {
            System.out.println(s);
        } else if (as >= 0 && cs >= 0) {
            printNumHelp(as - 1, cs, s + "" + valueA, valueA, valueC);
            printNumHelp(as, cs - 1, s + "" + valueC, valueA, valueC);
        }
    }

    public static void main(String[] args) {
        printNumbers3(1, 2, 3, 4);
    }
}
