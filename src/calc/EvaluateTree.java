package calc;

public class EvaluateTree {
  
  private Integer calculate(String operator, Integer leftData, Integer rightData) {
    switch(operator) {
      case "+":
        return leftData + rightData; 
      case "-":
        return leftData - rightData;
      case "*":
        return leftData * rightData;
      case "/":
        return leftData / rightData;
    }
    return null;
  }

  public Integer process(ASTNode<String> node) {
    if(node.getLeft() == null) {
      return Integer.valueOf(node.getData());
    }
    else {  
      Integer leftData = process(node.getLeft());
      Integer rightData = process(node.getRight());
      String operator = node.getData();
      return calculate(operator, leftData, rightData);
    }
  }

  public static void main(String[] args) throws Exception {
    InfixToPostfix convert = new InfixToPostfix();
    String postfix = convert.evaluate("1 + 2 * 3 - 4");
    Tree make = new Tree();
    make.buildTree(postfix);
    ASTNode<String> root = make.getRoot();
    EvaluateTree calc = new EvaluateTree();
    System.out.println("Output: " + calc.process(root));
  }
}
    
