import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

 class MyList extends OperationWithProduct {
    Scanner sc;
    public MyList() {
        head = tail = null;
        sc = new Scanner(System.in);
    }

    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        sc = new Scanner(System.in);
    }
    boolean isEmpty() {
        return (head == null);
    }

     /**
      * Chuyển về số nhị phân
      * @param decimal_number
      * @return
      */
    int convertToBinary(int decimal_number) {
        if (decimal_number == 0)
            return 0;
        else
            return (decimal_number % 2 + 10 *
                    convertToBinary(decimal_number / 2));
    }

     /**
      * Ghi file
      * @throws IOException
      */
    public String writeToFile() throws IOException {
        // đọc file và xóa hết nội dung của file
        String result = "";
        BufferedWriter deleteData = Files.newBufferedWriter(Paths.get("information.txt"));
        deleteData.write("");
        deleteData.flush();
        deleteData.close();
        File fileName = new File("information.txt");
        FileWriter writer = new FileWriter(fileName, true);

        Node first  = head;
        while (head != null) {
            try {
                // tạo chuỗi ngăn cách với nhau bằng |
                String str = head.info.id + "  |  " + head.info.name + "  |  "
                        + head.info.quantity + "  |  " + head.info.price + "  |  ";
                // ghi chuỗi vào file
                writer.write(str);
                writer.write("\n");
            } catch (IOException e) {
                result = "Ghi file thất bại";
            }
            head = head.next;
        }
        head = first;
        writer.close();
        result = "Ghi file thanh cong!";
        return result;
    }

     /**
      * Tìm kiếm theo ID
      */
    String searchID() {
        String str = "";
        Node newNode = head;
        boolean isFound = false;
        System.out.print("Xin moi ban nhap ID: ");

        String id = sc.next();
        while (head != null) {
            // nếu id nhập vào trùng với id trong list thì hiển thị thông tin và thoát vòng
            if (id.equalsIgnoreCase(head.info.id)) {
                //System.out.println("Thong tin san pham nhu sau: ");
                //System.out.println(head.toString());
                str += head.toString();
                isFound = true;
                break;
            }
            head = head.next;
        }
        head = newNode;
        if(!isFound) {
            str += "-1";
        }
        return str;
    }

     /**
      * Xóa phần tử đầu tiên
      * @return
      */

    Node removeAtHead() {
        if(head != null) {
            return  head.next;
        }
        return head;
    }

     /**
      * Xóa phần tử theo index
      * @param index
      * @return
      */
    Node removeAtIndex(int index) {
        if(head == null || index < 0) {
            return null;
        }
        if(index == 0) {
            return removeAtHead();
        }
        Node curNode = head;
        Node preNode = null;
        int count = 0;
        boolean isFound = false;
        while (curNode != null) {
            if(count == index) {
                isFound = true;
                break;
            }
            preNode = curNode;
            curNode = curNode.next;
            count++;
        }
        if(isFound) {
            if(preNode == null) {
                return null;
            }else {
                if (curNode != null) {
                    preNode.next = curNode.next;
                }
            }
        }
        return head;
    }

     /**
      * Xóa phần tử theo Id
      */
    String removeById() {
        int index = 0;
        boolean findItem = false;
        String str= "";
        Node newNode = head;
        System.out.print("Xin moi nhap Id can xoa: ");
        String id = sc.next();
        // tìm index của Id
        while (head != null) {
            if (id.equalsIgnoreCase(head.info.id)) {
                System.out.println(head.toString());
                findItem = true;
                break;
            }
            head = head.next;
            index++;
        }
        head = newNode;
        if(index == 0 && findItem) {
            Node t = removeAtHead();
            head = t;
        }
        if (index > 0  && findItem) {
            Node t = removeAtIndex(index);
            head = t;
            str += "\nDa xoa xong!\nDanh sach sau khi xoa :\n\n" + traverse();
        }

        if(!findItem) {
            str = "\nKhong co Id nay trong danh sach.\n";
        }
        return str;
    }

     /**
      * Tìm pivot
      * @param start
      * @param end
      * @return
      */
     Node paritionLast(Node start, Node end)
     {
         if(start == end || start == null || end == null)
             return start;

         Node pivot_prev = start;
         Node curr = start;
         ProductInfo pivot = end.info;

         // không cần lặp đến Node cuối cùng
         // chỉ cần lặp đến Node trước Node cuối cùng
         // bởi vì Node cuối cùng là pivot

         while(start != end )
         {
             if(start.info.id.compareToIgnoreCase(pivot.id) < 0)
             {
                 pivot_prev = curr;
                 ProductInfo temp = curr.info;
                 curr.info = start.info;
                 start.info = temp;
                 curr = curr.next;
             }

             start = start.next;
         }

         ProductInfo temp = curr.info;
         curr.info = pivot;
         end.info = temp;

         // trả về nút previous của nút curent
         //bởi vì curent đang trỏ đến pivot
         return pivot_prev;
     }

     /**
      * Sắp xếp list bằng Quick Sort
      * @param start
      * @param end
      */
     void sortUsingRecursion(Node start, Node end)
     {
         if(start == null || start == end|| start == end.next )
             return;
         Node pivot_prev = paritionLast(start, end);
         sortUsingRecursion(start, pivot_prev);

         // nếu pivot được chọn và chuyển đến đầu,
         // nghĩa là start và pivot giống nhau
         // vì vậy hãy chọn từ phần tiếp theo của pivot

         if( pivot_prev != null &&
                 pivot_prev == start )
             sortUsingRecursion(pivot_prev.next, end);

            // nếu pivot ở giữa list thì bắt đầu từ phần tiếp theo của pivot
            // bởi vì ta đã có pivot_prev, nên ta sẽ di chuyển 2 node
         else if(pivot_prev != null &&
                 pivot_prev.next != null)
             sortUsingRecursion(pivot_prev.next.next, end);
     }
}