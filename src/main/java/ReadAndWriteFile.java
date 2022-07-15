import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadAndWriteFile {
    /**
     * Tách chuỗi có chứa các dấu phân cách và trả về mảng các giá trị
     * @param subject
     * @param delimiters
     * @return
     */
    static String[] SplitUsingTokenizer(String subject, String delimiters) {
        StringTokenizer strTkn = new StringTokenizer(subject, delimiters);
        ArrayList<String> arrLis = new ArrayList<String>(subject.length());
        while(strTkn.hasMoreTokens())
            arrLis.add(strTkn.nextToken());
        return arrLis.toArray(new String[0]);
    }

    /**
     * Đọc file và xử lý chuỗi trong file
     * @return danh sách mảng dữ liệu
     */
    public static ArrayList<ProductInfo> readFile() {
        ArrayList<ProductInfo> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("information.txt"))) {
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                // Nếu dòng không có gì hoặc đầu dòng chứa ký tự ID hoặc "-" thì bỏ qua
                if (line!= null && line !="" && !line.startsWith("ID") && !line.startsWith("-")) {
                    //loại bỏ khoảng cách trong chuỗi
                    String newline = line.replace(" ","");
                    //tạo mảng lưu thông tin
                    String[] c = SplitUsingTokenizer(newline,"|");
                    String id = c[0];
                    String name = c[1];
                    Integer quatity = Integer.parseInt(c[2]);
                    float price = Float.parseFloat(c[3]);
                    ProductInfo newPro = new ProductInfo(id,name,quatity,price);
                    data.add(newPro);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * tạo phương thức kiểm tra xem file đã tồn tại hay chưa
     * nếu tồn tại rồi thì xóa nội dung của file đi
     * @param fileName
     */
    public static void testFile(String fileName) {
        final Path path = Paths.get(fileName);
        if(Files.exists(path)) {
            File myObj = new File(fileName);
            myObj.delete();
        }
    }

    /**
     * tạo phương thức thêm chuỗi vào file
     */

    public static void appendArrayToFile(String str, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(str);
            writer.write("\n");
            writer.close();
        }catch (IOException e) {
            System.err.println("Ghi file thất bại");
        }
    }
}
