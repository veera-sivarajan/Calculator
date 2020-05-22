package calc;

public class Parser {
  private Stack<Character> operators;
  private Stack<ASTNode<Character>> operands;

  public Parser() {
    operators = new Stack<Character>();
    operands = new Stack<ASTNode<Character>>();
  }
  
  private boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
  }

  public ASTNode<Character> eval(String input) {
    for(int i = 0; i < input.length(); ++i) {
      char ch = input.charAt(i);
      if(ch == '(') {
        operators.push(ch);
      }
      else if(Character.isDigit(ch)) {
        operands.push(new ASTNode<Character>(ch));
      }
      else if(isOperator(ch)) {
        while(precedence(operators.peek()) >= precedence(ch)) {
          char symbol = operators.pop();
          ASTNode<Character> op1 = operands.pop();
          ASTNode<Character> op2 = operands.pop();
          operands.push(new ASTNode<Character>(symbol, op1, op2));
        }
        operators.push(ch);
      }
      else if(ch == ')') {
        while(operators.peek() != '(') {
          char symbol = operators.pop();
          ASTNode<Character> op1 = operands.pop();
          ASTNode<Character> op2 = operands.pop();
          operands.push(new ASTNode<Character>(symbol, op1, op2));
        }
        operators.pop();
      }
    }
    return operands.pop();
  }
}
