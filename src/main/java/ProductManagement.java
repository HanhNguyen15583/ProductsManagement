import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {
    static Scanner sc = new Scanner(System.in);
    public ProductInfo productInfo = null;
    public MyList myList = null;
    public MyStack myStack = null;
    public MyQuere myQuere = null;
    public ProductManagement() {
        myList = new MyList();
        productInfo = new ProductInfo();
        myStack = new MyStack();
        myQuere = new MyQuere();
    }
    public ProductInfo getProductInfo() { return this.productInfo; }
    public MyList getMyList() { return this.myList; }
    public MyStack getMyStack() {
        return this.myStack;
    }
    public MyQuere getMyQuere() {
        return this.myQuere;
    }

    /**
     * Hiển thị các chức năng cho người dùng lựa chọn
     * @return
     */
    public int chooseOption() {
        System.out.println("\nProduct list:");
        System.out.println("1. Load data from file and display");
        System.out.println("2. Input & add to the end.");
        System.out.println("3. Display data");
        System.out.println("4. Save product list to file.");
        System.out.println("5. Search by ID");
        System.out.println("6. Delete by ID");
        System.out.println("7. Sort by ID.");
        System.out.println("8. Convert to Binary");
        System.out.println("9. Load to stack and display");
        System.out.println("10. Load to queue and display.");
        System.out.println("0. Exit");
        System.out.print("Choose one of these options: ");
        int option = sc.nextInt();
        return option;
    }
    public static void main(String[] args) throws IOException {
        ProductManagement productManagement = new ProductManagement();
        MyList ml = productManagement.getMyList();
        MyStack ms = productManagement.getMyStack();
        MyQuere mq = productManagement.getMyQuere();
        while (true) {
            int option = productManagement.chooseOption();
            if(option == 0) {
                String input0 = "Tam biet. Hen gap lai!";
                System.out.println(input0);
                ReadAndWriteFile.appendArrayToFile(input0,"console_output.txt");
                break;
            }
            switch(option) {
                case 1:
                    ml.readAndSave();
                    String input1 = ml.traverse();
                    System.out.println(input1);
                    ReadAndWriteFile.testFile("console_output.txt");
                    ReadAndWriteFile.appendArrayToFile(input1,"console_output.txt");
                    break;
                case 2:
                    System.out.print("\nInput new ID: ");
                    String id = sc.next();
                    System.out.print("Input Product's Name: ");
                    String name = sc.next();
                    System.out.print("Input Product's quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Input Product's Price: ");
                    float price = sc.nextFloat();
                    ProductInfo newPro = new ProductInfo(id,name,quantity,price);
                    String input2 = ml.addOne(newPro);
                    System.out.println(input2);
                    ReadAndWriteFile.appendArrayToFile(input2 + "\n","console_output.txt");
                    break;
                case 3:
                    String input3 = ml.traverse();
                    System.out.println(input3);
                    ReadAndWriteFile.appendArrayToFile(input3,"console_output.txt");
                    break;
                case 4:
                    String input4 = ml.writeToFile();
                    System.out.println(input4);
                    ReadAndWriteFile.appendArrayToFile(input4 + "\n","console_output.txt");
                    break;
                case 5:
                    String input5 = ml.searchID();
                    System.out.println(input5);
                    ReadAndWriteFile.appendArrayToFile(input5 + "\n","console_output.txt");
                    break;
                case 6:
                    String input6 = ml.removeById();
                    System.out.println(input6);
                    ReadAndWriteFile.appendArrayToFile(input6,"console_output.txt");
                    break;
                case 7:
                    Node n = ml.head;
                    while(n.next != null) {
                        n = n.next;
                    }
                    ml.sortUsingRecursion(ml.head, n);
                    String input7 = ml.traverse();
                    System.out.println(input7);
                    ReadAndWriteFile.appendArrayToFile(input7,"console_output.txt");
                    break;
                case 8:
                    int result = ml.convertToBinary(ml.head.info.quantity);
                    System.out.println("Binary: " + result);
                    ReadAndWriteFile.appendArrayToFile(result + "\n","console_output.txt");

                    break;
                case 9:
                    ArrayList<ProductInfo> data = ReadAndWriteFile.readFile();
                    ms.addMany(data);
                    String input9 = ms.traverse();
                    System.out.println(input9);
                    ReadAndWriteFile.appendArrayToFile(input9,"console_output.txt");
                    break;
                case 10:
                    mq.readAndSave();
                    String input10 = mq.traverse();
                    System.out.println(input10);
                    ReadAndWriteFile.appendArrayToFile(input10,"console_output.txt");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }

        }
    }
}
