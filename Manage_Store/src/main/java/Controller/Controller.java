/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Product;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author VAN TUOI
 */

public class Controller {
   public static void writeToFile(Product[] products, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), StandardCharsets.UTF_8))) {
            for (Product product : products) {
                if (product != null) {
                    StringBuilder line = new StringBuilder();
                    line.append(product.getTenSanPham()).append("|")
                            .append(product.getMaSanPham()).append("|")
                            .append(product.getSoLuong()).append("|")
                            .append(product.getDonGia()).append("|")
                            .append(product.getDonViTinh()).append("|")
                            .append(product.getNgaySanXuat()).append("|")
                            .append(product.getHanSuDung());

                    writer.write(line.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> readFromFile(String fileName) {
        String filePath = "output1.txt";

        // Tạo danh sách để lưu các sản phẩm
        ArrayList<Product> list_product = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Tách thông tin từng cột trong dòng
                String[] data = line.split("\\|");

                // Kiểm tra xem có đủ cột không
                if (data.length == 7) {
                    // Tạo đối tượng Product từ dữ liệu và thêm vào danh sách
                    Product product = new Product(
                            data[0],  // Tên sản phẩm
                            data[1],  // Mã sản phẩm
                            Integer.parseInt(data[2]),  // Số lượng
                            Integer.parseInt(data[3]),  // Đơn giá
                            data[4],
                            data[5],   // Ngày sản xuất
                            data[6]    // Hạn sử dụng
                    );
                    list_product.add(product);
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return list_product;
    }
    public static void main(String[] args) {
        // Đối tượng mảng 2D để làm ví dụ
        Product[] list_products = new Product[1000];
        list_products[0] = new Product("Bàn", "B01", 10, 10,"cái", "29/11/2023","30/11/2023");
        list_products[1] = new Product("Ghế", "B01", 10, 10,"cái", "29/11/2023","30/11/2023");
        list_products[2] = new Product("Tủ", "B01", 100, 10,"cái", "29/11/2023","06/12/2023");

        // Gọi hàm để ghi vào tập tin
        writeToFile(list_products, "output1.txt");
        readFromFile("output.txt");
        list_products = new Product[1000];
//        list_products = readFromFile("output1.txt");
        System.out.println("Danh sách sản phẩm");
         for (Product product : list_products) {
             if (product != null)
                System.out.println(product.getTenSanPham());
        }
    }
}
