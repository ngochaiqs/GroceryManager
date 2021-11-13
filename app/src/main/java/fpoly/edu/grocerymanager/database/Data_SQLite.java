package fpoly.edu.grocerymanager.database;

public class Data_SQLite {

    public static final String INSERT_NGUOI_DUNG =
            "INSERT INTO NguoiDung (maND, hoTen, matKhau, soDienThoai) values \n" +
                    "('NgocHai','Lương Ngọc Hải', '123', '0911602532'),\n" +
                    "('HongThi','Nguyễn Hồng Thi', '123', '0943225643'),\n" +
                    "('MyQuyen', 'Lương Mỹ Quyên', '123', '0911602532'),\n" +
                    "('HoangTrang', 'Trần Hoàng Trang', '123', '0911602532'),\n" +
                    "('QuangCuong', 'Lý Quang Cường', '123', '0911602532'),\n" +
                    "('VanTan', 'Nguyễn Văn Tấn', '123', '0911602532'),\n" +
                    "('ThaiLuat', 'Nguyễn Thái Luật', '123', '0911602532'),\n" +
                    "('ThanhTruc', 'Lê Thị Thanh Trúc', '123', '0911602532'),\n" +
                    "('NgocHuong', 'Trần Thị Ngọc Hương', '123', '0911602532')\n";
    public static final String INSERT_LOAI_HANG =
            "INSERT INTO LoaiHang (maLoai, tenLoai) values\n" +
                    "('GD01', 'Đồ Gia Dụng'),\n" +
                    "('TP01', 'Đồ Thực Phẩm')";
    public static final String INSERT_HANG =
            "INSERT INTO Hang(maHang, gia, tenHang) values \n" +
                    "('DAUAN01','65000','Dầu ăn Neptune')";
    public static final String INSERT_HOA_DON =
            "INSERT INTO HoaDon(maND,mahang,tongTien,ngayLap,trangThai) values\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0),\n" +
                    "('NgocHai','DAUAN01',' 130000','2021/10/10',0)";
    public static final String INSERT_HOA_DON_CHI_TIET =
            "INSERT INTO HoaDonChiTiet(mahang, soLuong,gia,ghiChu) values\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú'),\n" +
                    "('DAUAN01','2','130000','Ghi Chú')";
}