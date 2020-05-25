package calc;

import java.util.*;

public class InfixToPostfix {
  private Stack<Character> operator;
  private String output;
  
  public InfixToPostfix() {
    operator = new Stack<Character>();
    output = new String("");
  }

  private boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  private boolean isLeftAssoc(char ch) {
    return ch != '^';
  }

  private int precedence(char ch) {
    int result = 0;
    switch(ch) {
      case '+':
      case '-':
        result = 1;
        break;
      case '*':
      case '/':
        result = 2;
        break;
      case '^':
        result = 3;
        break;
    }
    return result;
  }

  public String evaluate(String input) throws Exception{ 
    for(int i = 0; i < input.length(); ++i) {
      char ch = input.charAt(i);
      if(Character.isDigit(ch)) {
        output += ch;
      }

      if(Character.compare(ch, '(') == 0) {
        //System.out.println("Pushing: " + ch);
        operator.push(ch);
      }

      if(Character.compare(ch, ')') == 0) {
        while(operator.getSize() > 0 && Character.compare(operator.peek(), '(') != 0) {
            output += operator.pop();
          //System.out.println("Output: " + output);
        }
        //System.out.println("Popping: " + operator.pop()); //popping '('
        operator.pop();
      }

      if(isOperator(ch)) {
        while(operator.getSize() > 0 && precedence(ch) <= precedence(operator.peek()) && isLeftAssoc(ch)) {
          output += operator.pop();
        }
        //System.out.println("Output: " + output);
        //System.out.println("Pushing: " + ch);
        operator.push(ch);
      }
      
      //System.out.println("Conversion: " + output);
    }
    while(operator.getSize() > 0) {
      output += operator.pop();
    }
    //System.out.println("Output: " + output);
    return output;
  }

  public void readInput(String input) {
    Scanner read = new Scanner(input);
    while(read.hasNext()) {
      char output;
      output += read.next();
      System.out.print(output + " ");
    }
  }
      
  public static void main(String[] args) throws Exception {
    InfixToPostfix eval = new InfixToPostfix();
    eval.readInput("1 + 10");
  }
}
