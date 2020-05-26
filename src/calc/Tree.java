package calc;

import java.util.*;

public class Tree {
  private InfixToPostfix convert;
  private ASTNode<String> root;
  private Stack<ASTNode<String>> expression;

  public Tree() {
     convert = new InfixToPostfix();
     expression = new Stack<ASTNode<String>>();
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

  private void buildTreeHelper(String input) throws Exception {
    Scanner reader = new Scanner(input);
    while(reader.hasNext()) {
      String ch = reader.next();
      if(isNumber(ch)) {
        ASTNode<String> node = new ASTNode<String>(ch);
        expression.push(node);
      }
      else {
        ASTNode<String> node1 = expression.pop();
        ASTNode<String> node2 = expression.pop();
        ASTNode<String> pushNode = new ASTNode<String>(ch, node2, node1);
        expression.push(pushNode);
      }
    }
    root = expression.pop();
    reader.close();
  }

  public void buildTree(String infixInput) throws Exception {
    buildTreeHelper(convert.evaluate(infixInput));
  }

  public ASTNode<String> getRoot() {
    return root;
  }

  /*private void postOrder(ASTNode<String> node) {
    if(node != null) {
      postOrder(node.getLeft());
      postOrder(node.getRight());
      System.out.print(node.getData() + " ");
    }
  }

  private void inOrder(ASTNode<String> node) {
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
    String postFix = convert.evaluate("1 + 2 * 3 - 4");
    System.out.println("Postfix: " + postFix);
    make.buildTree(postFix);
    make.printTree();
  }*/
}
    
