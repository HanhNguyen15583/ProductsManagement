public class Node {
    ProductInfo info;
    Node next;

    Node(ProductInfo x, Node p) {
        info = x;
        next = p;
    }
    Node(ProductInfo x) {
        this(x, null);
    }

    public void setNext(Node nextNode) {
        this.next = nextNode;
    }
    public void setInfo(ProductInfo x) {
        this.info = x;
    }
    public Node getNext() {
        return this.next;
    }
    public ProductInfo getInfo() {
        return this.info;
    }

    /**
     * Hiển thị thông tin sau khi người dùng nhập sản phẩm vào
     * @return
     */
    public String toString() {
        return ("ID: " + getInfo().id + "\nName: " + getInfo().name + "\nQuantity: " + getInfo().quantity + "\nPrice: " + getInfo().price);
    }
}