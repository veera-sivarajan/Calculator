package calc;

//Not working as intended
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
        currNode.setLeft(new ASTNode<Character>(' '));
        parentStack.push(currNode);
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
        currNode.setRight(new ASTNode<Character>(' '));
        parentStack.push(currNode);
        currNode = currNode.getRight();
        continue;
      }

      if(token == ')') {
        if(parentStack.getSize() > 0) { 
          currNode = parentStack.pop();
        }
        continue;
      }
    }
  }

  private void inOrderTraversal(ASTNode<Character> node) {
    if(node != null) {
      inOrderTraversal(node.getLeft());
      System.out.print(node.getData());
      inOrderTraversal(node.getRight());
    }
  }

  public void printTree() {
    inOrderTraversal(root);
  }

  public ASTNode<Character> getRoot() { 
    return root;
  }
  
  public static void main(String[] args) throws Exception {
    Parser parse = new Parser();
    parse.read("(5*3)+(4+2/2*8)");
    parse.printTree();
  }


}
