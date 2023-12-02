
package com.qlns.entity;

import java.util.Date;


public class NhanVien {
    private String MaNV;
    private String MatKhau;
    private String HoVaTen;
    private Boolean GioiTinh;
    private String QueQuan;
    private Float SDT;
    private String Email;
    private Date NgaySinh;
    private String ChucVu;
    private String BacLuong;
    private String MaPB;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String MatKhau, String HoVaTen, Boolean GioiTinh, String QueQuan, Float SDT, String Email, Date NgaySinh, String ChucVu, String BacLuong, String MaPB) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.HoVaTen = HoVaTen;
        this.GioiTinh = GioiTinh;
        this.QueQuan = QueQuan;
        this.SDT = SDT;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
        this.ChucVu = ChucVu;
        this.BacLuong = BacLuong;
        this.MaPB = MaPB;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public Boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public Float getSDT() {
        return SDT;
    }

    public void setSDT(Float SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getBacLuong() {
        return BacLuong;
    }

    public void setBacLuong(String BacLuong) {
        this.BacLuong = BacLuong;
    }

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String MaPB) {
        this.MaPB = MaPB;
    }

//    @Override
//    public String toString() {
//        return this.HoTen;
//    }
    
    
    
    
    
}
