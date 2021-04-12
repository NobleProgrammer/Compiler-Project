
/*
    AMMAR LANGUAGE TEAM
    TURKI S. ALZUBAIDI
    AMMAR T. JOHARJI
*/

/*
NOTE AND Apology: Hello all, hope you and your family are doing well.
    First, we apologize of bad output formmating.
    Also, about the text file formatting/type(ANSI, unicode, UTF-8),
    we found diffirent results for each diffirent test file type.
    for example, UTF-8 always starts with unrecognized lexeme.
    ANSI type change any unrecognized lexeme into question mark.
    Our programe is built with ANSI type. Thank you.
    Have a nice day :)
    Turki Alzubaidi
    Ammar Joharji
*/      

/*
    AMMAR LANGUAGE is dervied from the arabic word "ammar" which is an adjective of the verb "amr" which means command.
    and porgramming languages are about commanding the computer to do task. :)
*/

import java.io.*;
import java.util.*;

public class Tokenizer {

    static int loop_state_counter = 0;
    static HashSet <String> hash = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
       File file = new File("input.txt");
       File rwords = new File("rwords_input.txt");
       
       Scanner in = new Scanner (rwords);

       while(in.hasNext()) hash.add(in.next());

        Scanner sc = new Scanner(file);
        sc.useDelimiter("");
        String input = "";
        while (sc.hasNext()) {
            input += sc.next();
        }
        enTokenizer(input);
    }

 
    public static void enTokenizer(String input) {
        
        int state = 0;
        StringBuilder lexeme = new StringBuilder();
        if (input.length() < 1) {
            System.out.println("Error, Input file is empty. Exiting...");
            return;
        }
        
        System.out.println("Lexeme:\tToken:");
        int forLength = input.length();
        for (int i = 0; i < forLength ; i++) {
          // System.out.println("i is " + i + "is letter is " + input.charAt(i));
            switch (state) {
                case 0: {
                    if (input.charAt(i) == '\t' || input.charAt(i) == '\r' || input.charAt(i) == '\n' || input.charAt(i) == ' '){
                        state = 0;
                    }
                    else if(input.charAt(i) == '$' || input.charAt(i) == '_' ||  (65 <= input.charAt(i) && input.charAt(i) <= 122) ){
                        state = 86;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } 
                    else if (Character.isDigit(input.charAt(i))){
                        state = 22;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }

                    }
                    else if (input.charAt(i) == '>'){
                        state = 52;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '<'){
                        state = 61;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '+') {//Plus
                        state = 2;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } else if (input.charAt(i) == '-') {
                        state = 1;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } else if (input.charAt(i) == '*') {
                        state = 5;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } else if (input.charAt(i) == '/') {
                        state = 4;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } else if (input.charAt(i) == '%') {
                        state = 3;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == ']'){//Delimiters Start from Here.
                        state = 34;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '['){
                        state = 33;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '{'){//Complete
                        state = 31;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '}'){//Complete
                        state = 32;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '('){
                        state = 29;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == ')'){
                        state = 30;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == ';'){
                        state = 35;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == ':'){
                        state = 36;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '\"'){
                        String substring = input.substring(i+1);
                        int index = substring.indexOf('\"');
                        if(index != -1 ){
                            substring = substring.substring(0, index);
                            System.out.println("\""+substring+"\" \t String");
                            i = ++i + substring.length();
                            break;
                        } else {
                        state = 40;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    }
                    else if (input.charAt(i) == '\''){
                        //String substring = input.substring(++i);
                        if(i+2 < input.length() && input.charAt(i+2) == '\''){
                            System.out.println("\'"+input.substring(i+1,i+2)+"\' \t Character");
                            i = i+2;
                        } else {
                        state = 39;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    }
                    else if (input.charAt(i) == '?'){
                        state = 37;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == ','){
                        state = 38;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '.'){
                        state = 41;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if (input.charAt(i) == '\\'){
                        state = 42;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                      //Logical OP resume here
                      else if(input.charAt(i) == '&'){
                        state = 70;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if(input.charAt(i) == '|'){
                        state = 74;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if(input.charAt(i) == '^'){
                        state = 78;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if(input.charAt(i) == '!'){
                        state = 81;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if(input.charAt(i) == '~'){
                        state = 84;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else if(input.charAt(i) == '='){
                        state = 85;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    } else if(input.charAt(i) == '_'){
                        state = 90;
                        lexeme.append(input.charAt(i));
                        if (i == input.length()-1) {
                            forLength++;
                        }
                    }
                    else
                    System.out.println("ERROR! " + input.charAt(i) + " IS UNRECOGNIZED LEXEME");
                
                   
                    
                    break;
                }//End Case 0
                case 1: {//Previous input was '-'
                    if (i < input.length() && input.charAt(i) == '-') {
                        state = 7;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '=') {
                        state = 6;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && Character.isDigit(input.charAt(i))){
                        state = 22;
                     //   lexeme.append(input.charAt(i));
                        i--;
                    }
                    else {
                        state = 8;
                        i--;
                    }
                    break;
                }
                case 2: { // Previous input was '+'
                    if (i < input.length() && input.charAt(i) == '+') {
                        state = 10;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '=') {
                        state = 9;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 11;
                        i--;
                    }
                    break;
                }
                case 3: {//Previous input was '%'
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 12;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else {
                        state = 13;
                        i--;
                    }
                    break;
                }
                case 4: {//Previous input was '/'
                    if (i < input.length() && input.charAt(i) == '/') {
                        state = 15;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '*') {
                        state = 14;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '=') {
                        state = 16;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 17;
                        i--;
                    }
                    break;
                }
                case 5: {//Previous input was '*'
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 19;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '/') {
                        state = 18;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 20;
                        i--;
                    }
                    break;
                }
                case 6: {
                    System.out.println(lexeme + "\tMinus Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 7: {
                    System.out.println(lexeme + "\tDecrement Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 8: {
                    System.out.println(lexeme + "\tArithmetic Minus Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 9: {
                    System.out.println(lexeme + "\tPlus Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 10: {
                    System.out.println(lexeme + "\tIncrement Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 11: {
                    System.out.println(lexeme + "\tArithmetic Plus Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 12: {
                    System.out.println(lexeme + "\tMod. Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 13: {
                    System.out.println(lexeme + "\tArithmetic Modulo Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 14: {
                  //  System.out.println(lexeme + "\tOpening Comment Operator");
                    int j = i +1;

                    while(!(input.charAt(j) == '*' && input.charAt(j+1) == '/') )j++;
                    i = j+1;
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 15: {
                    // System.out.println(lexeme + "\tComment Operator");
                    while(input.charAt(i)!='\n')i++;
                    
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 16: {
                    System.out.println(lexeme + "\tDiv. Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 17: {
                    System.out.println(lexeme + "\tArithmetic Division Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 18: {
                   System.out.println(lexeme + "\tClosing Comment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 19: {
                    System.out.println(lexeme + "\tMultiplication Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 20: {
                    System.out.println(lexeme + "\tArithmetic Multiplication Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }

                //Delims starts here
                case 29: {
                    System.out.println(lexeme + "\tOpen Parenthesis Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 30: {
                    System.out.println(lexeme + "\tClose Parenthesis Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 31: {
                    System.out.println(lexeme + "\tOpen Curly Bracket Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 32: {
                    System.out.println(lexeme + "\tClose Curly Bracket Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 33: {
                    System.out.println(lexeme + "\tOpen Bracket Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 34: {
                    System.out.println(lexeme + "\tClose Bracket Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 35: {
                    System.out.println(lexeme + "\tSemicolon Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 36: {
                    System.out.println(lexeme + "\tColon Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 37: {
                    System.out.println(lexeme + "\tQuestion Mark Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 38: {
                    System.out.println(lexeme + "\tComma Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 39: {
                    System.out.println(lexeme + "\tSingle Quotes Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 40: {
                    System.out.println(lexeme + "\tDouble Quotes Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 41: {
                    System.out.println(lexeme + "\tDot Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 42: {//Means we encountered a "\"
                    if (i < input.length() && input.charAt(i) == 'r') {
                        state = 44;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == 't'){
                        state = 45;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == 'b'){
                        state = 46;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == 'n'){
                        state = 47;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == '\"'){
                        state = 48;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == '\''){
                        state = 49;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == 'f'){
                        state = 50;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else if (i < input.length() && input.charAt(i) == '\\'){
                        state = 51;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else {
                        state = 43;
                        i--;
                    }
                    break;
                }
                case 43:{
                    System.out.println(lexeme + "\tBackward Slash Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 44:{
                    System.out.println(lexeme + "\tCarriage Return Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 45:{
                    System.out.println(lexeme + "\tTab Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 46:{
                    System.out.println(lexeme + "\tBack Space Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 47:{
                    System.out.println(lexeme + "\tNew Line Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 48:{
                    System.out.println(lexeme + "\tDouble Quotes Escape Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 49:{
                    System.out.println(lexeme + "\tSingle Quote Escape Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 50:{
                    System.out.println(lexeme + "\tForm Feed Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 51:{
                    System.out.println(lexeme + "\tBackward Slash Escape Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }

                case 52:{
                
                    if (i < input.length() && input.charAt(i) == '>') {
                        state = 55;
                        lexeme.append(input.charAt(i));
               
                        forLength++;
                    }
                    else if( i < input.length() && input.charAt(i) == '='){
                        state = 54;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else {
                        state = 53;
                        i--;
                    }

                    break;
                }
                case 53:{
                    System.out.println(lexeme + "\t Greater than");
                    lexeme.setLength(0);
                     if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 54:{
                    System.out.println(lexeme + "\t Greater than or equal");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 55:{
                    if(i < input.length() && input.charAt(i) == '>'){
                        state = 58;
                        lexeme.append(input.charAt(i));

                    }
                     else if(i < input.length() && input.charAt(i) == '='){
                        state = 57;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 56;
                        i--;
                    }
                    break;
                    
                }
                case 56:{
                    System.out.println(lexeme + "\t Signed right shift");
                    lexeme.setLength(0);
                    
                    state = 0;
                    forLength--;
                    i--;
                    break;
                }
                case 57:{
                    System.out.println(lexeme + "\t Signed right shift, assign");
                    lexeme.setLength(0);
                    state = 0;
              // dangerous     i--;
                    forLength--;
                    break;
                    
                }
                case 58:{
                    if(i < input.length() && input.charAt(i) == '='){
                    state = 60;
                    lexeme.append(input.charAt(i));

                    } else {
                        state = 59;
                        i--;
                    }

                    break;
                    
                }
                case 59:{
                    System.out.println(lexeme + "\t unsigned right shift ");
                    lexeme.setLength(0);
                    state = 0;
                    i--;
                    forLength--;
                    break;
                }
                case 60:{
                    System.out.println(lexeme + "\t unsigned right shift, assignment");
                    lexeme.setLength(0);
                    state = 0;
                    i--;
                    forLength--;
                    break;
                }
                case 61:{
                
                    if (i < input.length() && input.charAt(i) == '<') {
                        state = 64;
                        lexeme.append(input.charAt(i));
               
                        forLength++;
                    }
                    else if( i < input.length() && input.charAt(i) == '='){
                        state = 63;
                        lexeme.append(input.charAt(i));
                        i--;
                    }
                    else {
                        state = 62;
                        i--;
                    }

                    break;
                }
                case 62:{
                    System.out.println(lexeme + "\t Less than");
                    lexeme.setLength(0);
                     if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 63:{
                    System.out.println(lexeme + "\t Less than or equal");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 64:{
                    if(i < input.length() && input.charAt(i) == '<'){
                        state = 67;
                        lexeme.append(input.charAt(i));

                    }
                     else if(i < input.length() && input.charAt(i) == '='){
                        state = 66;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 65;
                        i--;
                    }
                    break;
                    
                }
                case 65:{
                    System.out.println(lexeme + "\t Signed left shift");
                    lexeme.setLength(0);
                    state = 0;
                    forLength--;
                    i--;
                    break;
                }
                case 66:{
                    System.out.println(lexeme + "\t Left right shift, assign");
                    lexeme.setLength(0);
                    state = 0;
                    forLength--;
                //dangerous    i--;
                    break;
                    
                }
                case 67:{
                    if(i < input.length() && input.charAt(i) == '='){
                    state = 69;
                    lexeme.append(input.charAt(i));

                    } else {
                        state = 68;
                        i--;
                    }

                    break;
                    
                }
                case 68:{
                    System.out.println(lexeme + "\t unsigned Left shift ");
                    lexeme.setLength(0);
                    state = 0;
                    i--;
                    forLength--;
                    break;
                }
                case 69:{
                    System.out.println(lexeme + "\t unsigned left shift, assignment");
                    lexeme.setLength(0);
                    state = 0;
                    i--;
                    forLength--;
                    break;
                    
                }
                case 70:{
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 72;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '&') {
                        state = 73;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 71;
                        i--;
                    }
                    break;
                }
                case 71:{
                    System.out.println(lexeme + "\tAND Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 72: {
                    System.out.println(lexeme + "\tAND Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 73: {
                    System.out.println(lexeme + "\tLogical AND Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 74: {
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 76;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else if (i < input.length() && input.charAt(i) == '|') {
                        state = 77;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else {
                        state = 75;
                        i--;
                    }
                    break;
                }
                case 75: {
                    System.out.println(lexeme + "\tOR Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 76: {
                    System.out.println(lexeme + "\tOR Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 77: {
                    System.out.println(lexeme + "\tLogical OR Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 78: {
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 80;
                        lexeme.append(input.charAt(i));
                        i--;
                    }else {
                        state = 79;
                        i--;
                    }
                    break;
                }
                case 79: {
                    System.out.println(lexeme + "\tXOR Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 80: {
                    System.out.println(lexeme + "\tXOR Assignment Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 81: {
                    if (i < input.length() && input.charAt(i) == '=') {
                        state = 83;
                        lexeme.append(input.charAt(i));
                        i--;
                    }else {
                        state = 82;
                        i--;
                    }
                    break;
                }
                case 82: {
                    System.out.println(lexeme + "\tNot (Negation) Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 83: {
                    System.out.println(lexeme + "\tNot Equal Logical Operator");
                    lexeme.setLength(0);
                    state = 0;
                    break;
                }
                case 84:{
                    System.out.println(lexeme + "\tOne's Complement Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 85:{
                    if(i < input.length() && input.charAt(i)== '='){
                        state = 88;
                        lexeme.append(input.charAt(i));
                        i--;
                    } else{
                        state = 89;
                        i--;
                    }
                    break;
                }
                case 86:{
                    if(i < input.length() && (input.charAt(i) == '$' || input.charAt(i) == '_' || Character.isLetter(input.charAt(i)) ||
                      (65 <= input.charAt(i) && input.charAt(i) <= 122))){
                        state = 86;
                        lexeme.append(input.charAt(i));
                        forLength++;
                        loop_state_counter++;
                    }
                    else {
                        state = 87;
                        i--;
                    }
                    break;
                }
                case 87:{
                    if(hash.contains(lexeme+""))
                          System.out.println(lexeme + "\t" + lexeme);
                    else
                            System.out.println(lexeme + "\t Identifier");

                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    forLength = forLength - loop_state_counter;
                    loop_state_counter = 0;
                    state = 0;
                    break; 
                }
                case 88:{
                    System.out.println(lexeme + "\t Logical equal");
                    lexeme.setLength(0);
                    state =0;
                    break;
                }
                case 89:{
                    System.out.println(lexeme + "\tAssignment Operator");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 90:{
                    System.out.println(lexeme + "\t Underscore");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    state = 0;
                    break;
                }
                case 22:{
                    if(i < input.length() && Character.isDigit(input.charAt(i))){
                    state =22;
                    lexeme.append(input.charAt(i));
                    forLength++;
                    loop_state_counter++;
                    } else if(i < input.length() && input.charAt(i) == '.'){
                        state = 27;
                        lexeme.append(input.charAt(i));
                        forLength++;
                        loop_state_counter++;
                    } else{
                        state = 26;
                        i--;
                    }
                

                    break;
                }

                case 26:{
                    System.out.println(lexeme + "\t Integer");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    forLength = forLength - loop_state_counter;
                    loop_state_counter = 0;
                    state = 0;
                    break; 
                }
                
                case 27:{
                    if(i < input.length() && Character.isDigit(input.charAt(i))){
                        state =27;
                        lexeme.append(input.charAt(i));
                        forLength++;
                        loop_state_counter++;
                        } else{
                            state = 28;
                            i--;
                        }

                        break;
                }

                case 28:{
                    System.out.println(lexeme + "\tDouble");
                    lexeme.setLength(0);
                    if (i < input.length()) {
                        i--;
                    }
                    forLength = forLength - loop_state_counter;
                    loop_state_counter = 0;
                    state = 0;
                    break; 
                }

            }
        }
    }
    
}
