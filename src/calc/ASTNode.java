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
  
  public void setData(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public ASTNode<T> getRight() {
    return right;
  }

  public ASTNode<T> getLeft() {
    return left;
  }

  public void setLeft(ASTNode<T> left) {
    this.left = left;
  }

  public void setRight(ASTNode<T> right) {
    this.right = right;
  }
}
