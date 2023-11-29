package Model;
import java.time.LocalDate;
import java.util.Date;


public class Product {
    private String tenSanPham, maSanPham;
    private int soLuong, donGia;
    private String donViTinh,ngaySanXuat, hanSuDung;

    public Product() {
        this.tenSanPham = "";
        this.maSanPham = "";
        this.soLuong = 0;
        this.donGia = 0;
        this.donViTinh = "";
        this.ngaySanXuat = "";
        this.ngaySanXuat = "";
    }
    public Product(String tenSanPham, String maSanPham, int soLuong, int donGia,String donViTinh, String ngaySanXuat, String hanSuDung) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSuDung = hanSuDung;
    }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public String getMaSanPham() { return maSanPham; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public int getDonGia() { return donGia; }
    public void setDonGia(int donGia) { this.donGia = donGia; }

    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
    
    public String getNgaySanXuat() { return ngaySanXuat; }
    public void setNgaySanXuat(String ngaySanXuat) { this.ngaySanXuat = ngaySanXuat; }

    public String getHanSuDung() { return hanSuDung; }
    public void setHanSuDung(String hanSuDung) { this.hanSuDung = hanSuDung; }

    @Override
    public String toString() {
        return "Product{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", maSanPham='" + maSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", donViTinh=" + donViTinh +
                ", ngaySanXuat=" + ngaySanXuat +
                ", hanSuDung=" + hanSuDung +
                '}';
    }
}
