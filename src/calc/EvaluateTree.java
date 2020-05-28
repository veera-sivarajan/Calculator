package calc;

public class EvaluateTree {
  private Tree make;

  public EvaluateTree() {
    make = new Tree();
  }

  private Double calculate(String operator, Double leftData, Double rightData) {
    switch(operator) {
      case "+":
        return leftData + rightData; 
      case "-":
        return leftData - rightData;
      case "*":
        return leftData * rightData;
      case "/":
        return leftData / rightData;
      case "^":
        //System.out.println("Inside ^");
        Double result = 1.0;
        Double absRight= Math.abs(rightData);
        for(int i = 0; i < absRight; ++i) {
          result *= leftData;
        }
        if(rightData < 0) {
          result = 1 / result;
        }
        return result;
      }
      return null;
  }

  private Double processHelper(ASTNode<String> node) {
    if(node.getLeft() == null && node.getRight() == null) {
      //System.out.println("Inside if condition");
      return Double.valueOf(node.getData());
    }
    else {  
      Double leftData = processHelper(node.getLeft());
      Double rightData = processHelper(node.getRight());
      String operator = node.getData();
      //System.out.println("Operator: " + operator);
      return calculate(operator, leftData, rightData);
    }
  }

  public Double process(String infixInput) throws Exception {
    make.buildTree(infixInput);
    return processHelper(make.getRoot());
  }

  public static void main(String[] args) throws Exception {
    EvaluateTree calc = new EvaluateTree();
    System.out.println("Output 1: " + calc.process("(100-20)*20"));
    System.out.println("Output 2: " + calc.process("100-2*20"));
    System.out.println("Output 3: " + calc.process("2^(1-2)*0.5"));
    System.out.println("Output 4: " + calc.process("1/1024"));
  }
}
   
