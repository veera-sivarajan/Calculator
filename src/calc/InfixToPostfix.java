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
      Scanner reader = new Scanner(input);
      while(reader.hasNext()) {
        String ch = reader.next();
        if(isNumber(ch)) {
          output += ch + " ";
        }

        if(ch.equals("(")) {
          //System.out.println("Pushing: " + ch);
          operator.push(ch);
        }

        if(ch.equals(")")) {
          while(operator.getSize() > 0 && !(operator.peek().equals("("))) {
              output += operator.pop() + " ";
            //System.out.println("Output: " + output);
          }
          //System.out.println("Popping: " + operator.pop()); //popping '('
          operator.pop();
        }

        if(isOperator(ch)) {
          while(operator.getSize() > 0 && precedence(ch) <= precedence(operator.peek()) && isLeftAssoc(ch)) {
            output += operator.pop() + " ";
          }
          //System.out.println("Output: " + output);
          //System.out.println("Pushing: " + ch);
          operator.push(ch);
        }
        
        //System.out.println("Conversion: " + output);
      }
      while(operator.getSize() > 0) {
        output += operator.pop() + " ";
      }
      //System.out.println("Output: " + output);
      reader.close();
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
