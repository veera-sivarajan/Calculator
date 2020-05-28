package calc;

import java.util.*;

public class InfixToPostfix {
  private Stack<String> operator;
  private String output;
  
  public InfixToPostfix() {
    operator = new Stack<String>();
    output = new String("");
  }

  private boolean isOperator(String ch) {
    return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^");
  }

  private boolean isLeftAssoc(String ch) {
    return !(ch.equals("^"));
  }

  private int precedence(String ch) {
    int result = 0;
    switch(ch) {
      case "+":
      case "-":
        result = 1;
        break;
      case "*":
      case "/":
        result = 2;
        break;
      case "^":
        result = 3;
        break;
    }
    return result;
  }

  @SuppressWarnings("unused")
  private boolean isNumber(String input) {
    if(input == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(input);
    }
    catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public String evaluate(String input) throws Exception{ 
    String[] tokens = input.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
    for(String ele : tokens) {
      if(isNumber(ele)) {
        output += ele + " ";
      }

      if(ele.equals("(")) {
        //System.out.println("Pushing: " + ch);
        operator.push(ele);
      }

      if(ele.equals(")")) {
        while(operator.getSize() > 0 && !(operator.peek().equals("("))) {
            output += operator.pop() + " ";
          //System.out.println("Output: " + output);
        }
        //System.out.println("Popping: " + operator.pop()); //popping '('
        operator.pop();
      }

      if(isOperator(ele)) {
        while(operator.getSize() > 0 && precedence(ele) <= precedence(operator.peek()) && isLeftAssoc(ele)) {
          output += operator.pop() + " ";
        }
        //System.out.println("Output: " + output);
        //System.out.println("Pushing: " + ch);
        operator.push(ele);
      }
        
        //System.out.println("Conversion: " + output);
    }
    while(operator.getSize() > 0) {
      output += operator.pop() + " ";
    }
      //System.out.println("Output: " + output);
    return output;
  }
         
  /*SHIT CODE
    public String readInput(String input) throws Exception {
    Scanner read = new Scanner(input);
    String output = new String("");
    while(read.hasNext()) {
      String token = read.next();
      System.out.println("Token: " + token);
      int tokenSize = token.length();
      if(tokenSize > 1) {
        String modify = token;
        if(token.charAt(0) == '(' || token.charAt(tokenSize - 1) == ')') {
          System.out.println("Inside");
          token = Character.toString(modify.charAt(0));
          int i = 1;
          while(Character.isDigit(token.charAt(i))) {
            token += " ";
            token += Character.toString(modify.charAt(i));
            i += 1;
          }
        }
      }
      output += token + " ";
      //System.out.println("Output: " + output);
    }
    return (output);
  }*/
      
  public static void main(String[] args) throws Exception {
    InfixToPostfix eval = new InfixToPostfix();
    System.out.println("Output: " + eval.evaluate("2 ^ 3"));
  }
}
