package calc;

public class Evaluate {
  private Stack<Character> operator;
  private String output;
  
  public Evaluate() {
    operator = new Stack<Character>();
    output = new String("");
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
    }
    return result;
  }

  public String convert(String input) throws Exception{ 
    for(int i = 0; i < input.length(); ++i) {
      char ch = input.charAt(i);
      if(Character.isDigit(ch)) {
        output += ch;
      }

      else if(Character.compare(ch, '(') == 0) {
        operator.push(ch);
      }
      else if(Character.compare(ch, ')') == 0) {
        while(operator.getSize() > 0 && Character.compare(operator.peek(), '(') != 0) {
          output += operator.pop();
        }
        operator.pop(); //popping '('
      }
      else {
        while(operator.getSize() > 0 && precedence(ch) <= precedence(operator.peek())) {
          output += operator.pop();
        }
        operator.push(ch);
      }
    }
    while(operator.getSize() > 0) {
      output += operator.pop();
    }
    return output;
  }

  public static void main(String[] args) throws Exception {
    Evaluate eval = new Evaluate();
    System.out.println("Output: " + eval.convert(args[0]));
  }
}
