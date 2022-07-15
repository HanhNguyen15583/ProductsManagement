import java.util.ArrayList;

abstract class OperationWithProduct {
    Node head;
    Node tail;

    /**
     * Thêm 1 phần tử vào cuối
     * @param x
     */
    String addOne(ProductInfo x) {
        Node newNode = new Node(x);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        return "\nThem vao List thanh cong!";
    }

    /**
     * Thêm nhiều phần tử
     * @param x
     */
    void addMany(ArrayList<ProductInfo> x) {
        for(ProductInfo item : x) {
            Node newNode = new Node(item);
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.setNext(newNode);
                this.tail = newNode;
            }
        }
    }

    /**
     * Hiển thị thông tin
     */
    String traverse() {
        String data = "";
        Node first  = head;
        while (head != null) {
            //System.out.println(head.info.toString());
            data += head.info.toString() + "\n";
            head = head.next;
        }
        head  = first;
        System.out.println();
        return data;
    }

    /**
     * Đọc file và lưu dữ liệu vào trong list
     */

    void readAndSave() {
        // Lấy dữ liệu từ file
        ArrayList<ProductInfo> data = ReadAndWriteFile.readFile();
        // thêm dữ liệu vào trong list
        addMany(data);
    }

}
