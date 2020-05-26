package calc;

public class Node<T> {
  private T data;
  private Node<T> link;

  public Node(T data) {
    this.data = data;
    link = null;
  }

  public void setData(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setLink(Node<T> link) {
    this.link = link;
  }

  public Node<T> getLink() {
    return link;
  }
}

