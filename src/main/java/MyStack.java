
import java.util.ArrayList;

class MyStack {
    public Node head;
    public MyStack() { head = null; }
    public MyStack(Node head) { this.head = head; }
    public boolean isEmpty() { return(head==null);}

    /**
     * Thêm phần tử vào cuối
     * @param x
     */
    public void addOne(ProductInfo x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Thêm nhiều phần tử
     * @param x
     */
    public void addMany(ArrayList<ProductInfo> x) {
        for(ProductInfo item : x) {
            addOne(item);
        }
    }

    /**
     * Hiển thị thông tin sản phẩm
     */
    String traverse() {
        String data = "";
        Node first  = head;
        while (head != null) {
            data += head.info.toString() + "\n";
            head = head.next;
        }
        head  = first;
        System.out.println();
        return data;
    }

}