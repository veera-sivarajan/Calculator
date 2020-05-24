package calc;

public class Tree {
  ASTNode<Character> root;
  Stack<ASTNode<Character>> expression;

  public Tree() {
     expression = new Stack<ASTNode<Character>>();
  }

  public void buildTree(String input) throws Exception {
    for(int i = 0; i < input.length(); ++i) {
      char ch = input.charAt(i);
      if(Character.isDigit(ch)) {
        ASTNode<Character> node = new ASTNode<Character>(ch);
        expression.push(node);
      }
      else {
        ASTNode<Character> node1 = expression.pop();
        ASTNode<Character> node2 = expression.pop();
        ASTNode<Character> pushNode = new ASTNode<Character>(ch, node2, node1);
        expression.push(pushNode);
      }
    }
    root = expression.pop();
  }

  private void postOrder(ASTNode<Character> node) {
    if(node != null) {
      postOrder(node.getLeft());
      postOrder(node.getRight());
      System.out.print(node.getData() + " ");
    }
  }

  private void inOrder(ASTNode<Character> node) {
    if(node != null) {
      inOrder(node.getLeft());
      System.out.print(node.getData() + " ");
      inOrder(node.getRight());
    }
  }
  
  public void printTree() {
    inOrder(root);
  }

  public static void main(String[] args) throws Exception {
    InfixToPostfix convert = new InfixToPostfix();
    Tree make= new Tree();
    make.buildTree(convert.evaluate("4+2*1+3*(5+1)"));
    make.printTree();
  }
}
    
