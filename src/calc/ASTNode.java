package calc;

public class ASTNode<T> {
  private T data;
  private ASTNode<T> left;
  private ASTNode<T> right;

  public ASTNode(T data) {
    this.data = data;
    left = right = null;
  }

  public ASTNode(T data, ASTNode<T> left, ASTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
}
