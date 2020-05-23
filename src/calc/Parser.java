package calc;

public class Parser {
  private ASTNode<Character> root;
  private Stack<ASTNode<Character>> parentStack;
  private ASTNode<Character> currNode;

  public Parser() {
    root = new ASTNode<Character>(' ');
    parentStack = new Stack<ASTNode<Character>>();
    currNode = root;
  }

  public boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  public void read(String input) throws Exception {
    for(int i = 0; i < input.length(); ++i) {
      char token = input.charAt(i);
      if(token == '(') {
        parentStack.push(currNode);
        currNode.setLeft(new ASTNode<Character>(' '));
        currNode = currNode.getLeft();
        continue;
      }

      if(Character.isDigit(token)) { //throws stack underflow when no paranthesis
        currNode.setData(token);
        currNode = parentStack.pop();
        continue;
      }

      if(isOperator(token)) {
        currNode.setData(token);
        parentStack.push(currNode);
        currNode.setRight(new ASTNode<Character>(' '));
        currNode = currNode.getRight();
        continue;
      }

      if(token == ')') {
        currNode = parentStack.pop();
        continue;
      }
    }
  }

  public ASTNode<Character> getRoot() { 
    return root;
  }
  
  public static void main(String[] args) throws Exception {
    Parser parse = new Parser();
    parse.read(args[0]);
    System.out.println("Root data: " + parse.getRoot().getData());
  }


}
