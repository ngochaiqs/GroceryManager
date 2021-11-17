package fpoly.edu.grocerymanager.model;

public class Hang {
    private Integer maHang;
    private String tenHang;
    private int gia;
    private Integer maLoai;

    public Hang() {
    }

    public Hang(Integer maHang, String tenHang, int gia, Integer maLoai) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.gia = gia;
        this.maLoai = maLoai;
    }

    public Integer getMaHang() {
        return maHang;
    }

    public void setMaHang(Integer maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }
}
