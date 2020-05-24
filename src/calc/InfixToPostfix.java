package calc;

public class InfixToPostfix {
  private Stack<Character> operator;
  private String output;
  
  public InfixToPostfix() {
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
      case '^':
        result = 3;
    }
    return result;
  }

  public String convert(String input) throws Exception{ 
    for(int i = 0; i < input.length(); ++i) {
      int flag = 0;
      char ch = input.charAt(i);
      if(Character.isDigit(ch)) {
        flag += 1;
        output += ch;
      }

      if(Character.compare(ch, '(') == 0) {
        //System.out.println("Pushing: " + ch);
        operator.push(ch);
        flag += 1;
      }

      if(Character.compare(ch, ')') == 0) {
        while(operator.getSize() > 0 && Character.compare(operator.peek(), '(') != 0) {
            output += operator.pop();
          //System.out.println("Output: " + output);
        }
        //System.out.println("Popping: " + operator.pop()); //popping '('
        operator.pop();
        flag += 1;
      }

      if(flag == 0) {
        while(operator.getSize() > 0 && precedence(ch) <= precedence(operator.peek())) {
          output += operator.pop();
        }
        //System.out.println("Output: " + output);
        //System.out.println("Pushing: " + ch);
        operator.push(ch);
      }
    }
    while(operator.getSize() > 0) {
      output += operator.pop();
    }
    return output;
  }

  public static void main(String[] args) throws Exception {
    InfixToPostfix eval = new InfixToPostfix();
    System.out.println("Input: " + args[0]);
    System.out.println("Output: " + eval.convert(args[0]));
  }
}
