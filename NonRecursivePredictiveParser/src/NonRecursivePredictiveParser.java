//بسم الله الرحمن الرحيم
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;


public class NonRecursivePredictiveParser {

    private static String[][] parseTable;
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.txt");
        File output = new File("output.txt");
        if (!input.exists()) {
            System.out.println("File not found.");
            System.exit(0);
        }
        createParseTable(); // First construct the parse table.
        Scanner sc = new Scanner(input);
        PrintWriter write = new PrintWriter(output);
        String expr;
        while (sc.hasNextLine()) {// Loop through all the arthematic expressions in the file.
            stack.push("$");
            stack.push("E");//E because it's the the start symbol for the given grammar.
            expr = sc.nextLine();
            System.out.println("Left most derivation for arithmetic expression: " + expr);
            write.println("Left most derivation for arithmetic expression: " + expr);
            String[] symbols = expr.split(" ");
            //The algorithm starts here.
            int ip = 0;
            String X;
            boolean success = true;
            do {
                X = stack.peek();
                String a = symbols[ip];
                int row = getTerminalRowIndex(X);
                int column = getTerminalColumnIndex(a);
                if (isTerminal(X) || X.equals("$")) {
                    if (X.equals(a)) {
                        stack.pop();
                        ip++;
                    }
                    else {
                        error();
                        System.out.println("Continue next Expression...[If any]");
                        success = false;
                        break;
                    }
                }
                     //if non-terminal AND the row and column exists in table and there's a production in the cell.
                else if (!(isTerminal(X)) && row !=-1 && column !=-1 && !(parseTable[row][column].equals(""))){
                    StringBuilder outputString = new StringBuilder();
                    outputString.append(X).append(" -> ");
                    stack.pop();
                    String[] temp = parseTable[row][column].split(" ");
                    for (int i = temp.length-1; i >= 0 ; i--) {//Push production in reverse order.
                        if (!temp[i].equals("NULL")) {
                            stack.push(temp[i]);
                        }
                    }
                    outputString.append(parseTable[row][column]);
                    write.println(outputString);
                    System.out.println(outputString);
                } 
                else {
                    error();
                    System.out.println("Continue next Expression...[If any]");
                    success = false;
                    break;
                }
            } while (!X.equals("$"));
            if (success) {
                System.out.println("Parsing successfully halts.");
                write.println("Parsing successfully halts.");
                System.out.println("-------------------------------");
                write.println("-------------------------------");
                
            }
        }
        sc.close();
        write.flush();
        write.close();
    }
    
    
    
    public static void error(){
        System.out.println("Syntax Error");
    }
    public static boolean isTerminal(String token){
        return !Character.isUpperCase(token.charAt(0));
    }

    public static void createParseTable() {
        String[][] temp = {
           // 0      1   2    3      4   5
           // id     +   *    (      )   $
            {"T E'", "", "", "T E'", "", ""},//E "0"
            {"", "+ T E'", "", "", "NULL", "NULL"},//E' "1"
            {"F T'", "", "", "F T'", "", ""},//T "2"
            {"", "NULL", "* F T'", "", "NULL", "NULL"},//T' "3"
            {"id", "", "", "( E )", "", ""}//F "4"
        };
        parseTable = temp;
    }
    
    public static int getTerminalRowIndex(String terminal){
        if (terminal.equals("E")) {
            return 0;
        }
        else if(terminal.equals("E'")){
            return 1;
        }
        else if (terminal.equals("T")){
            return 2;
        }
        else if(terminal.equals("T'")){
            return 3;
        }
        else if (terminal.equals("F")){
            return 4;
        }
        else {
            return -1;
        }
    }
    
    public static int getTerminalColumnIndex(String terminal){
        if (terminal.equals("id")) {
            return 0;
        }
        else if(terminal.equals("+")){
            return 1;
        }
        else if (terminal.equals("*")){
            return 2;
        }
        else if(terminal.equals("(")){
            return 3;
        }
        else if (terminal.equals(")")){
            return 4;
        }
        else if (terminal.equals("$")){
            return 5;
        }
        else {
            return -1;
        }
    }
}
