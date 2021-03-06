package calc;

public class Stack<T> {
  
  private Node<T> top;
  private int size; 
  public Stack() {
    this.top = null;
    this.size = 0;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public void push(T num) {
    Node<T> ele = new Node<T>(num);
    if(this.size == 0) {
      this.top = ele;
    }
    else {
      ele.setLink(top);
      top = ele;
    }
    size += 1;
    //System.out.println(num + " added");
  }

  public T pop() throws Exception {
    if(this.size == 0) {
      throw new Exception("Empty Stack");
    }
    T popData = top.getData();
    top = top.getLink();
    size -= 1;
    return popData;
  }

  public T peek() {
    return top.getData();
  }
  
  /*public static void main(String[] args) throws Exception {
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println("Peek: " + stack.peek());
    stack.pop();
    System.out.println("Peek after pop: " + stack.peek());
  }*/
}
