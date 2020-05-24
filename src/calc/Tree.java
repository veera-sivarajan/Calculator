package calc;

public class Tree {
  Stack<ASTNode<Character>> expression;

  public Tree() {
     expression = new Stack<ASTNode<Character>>();
  }

  public ASTNode<Character> buildTree(String input) throws Exception {
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
    return expression.pop();
  }

  public static void main(String[] args) throws Exception {
    //InfixToPostfix convert = new InfixToPostfix();
    Tree make= new Tree();
    ASTNode<Character> root = make.buildTree("12+345+**");
    System.out.println("Root data: " + root.getData());
    System.out.println("Root left data: " + root.getLeft().getData());
    System.out.println("Root right data: " + root.getRight().getData());
  }
}
    
