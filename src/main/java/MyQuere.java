import java.util.Scanner;

public class MyQuere extends OperationWithProduct{
    Scanner sc;
    public MyQuere() {
        head = tail = null;
        sc = new Scanner(System.in);
    }

    public MyQuere(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        sc = new Scanner(System.in);
    }
}
