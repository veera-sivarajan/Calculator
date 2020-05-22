package calc;

public class Evaluate {
  private Stack<T> operator;
  private String output;

  public static void analyze(char input) { 
    if(Character.isDigit(input)) {
      output += input;
    }
    else {
      if(precedence(input) > precedence(operator.peek())) {
        operator.push(input);
      }
      else {
        while(precedence(input) <= precedence(operator.peek())) {
          stack.pop();
        }
        operator.push(input);
      }
    }
    }
  }



  public static void main(String[] args) {
    String input = args[0];
    for(int i = 0; i < input.length(); ++i) {
      Evaluate.analyze(input.charAt(i));
    }
  }
}
