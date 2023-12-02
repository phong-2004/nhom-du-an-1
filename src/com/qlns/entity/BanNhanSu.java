
package com.qlns.entity;


public class BanNhanSu {
    private String MaBNS;
    private String MatKhau;
    private String HoTen;

    public BanNhanSu() {
    }

    public BanNhanSu(String MaBNS, String MatKhau, String HoTen) {
        this.MaBNS = MaBNS;
        this.MatKhau = MatKhau;
        this.HoTen = HoTen;
    }

    public String getMaBNS() {
        return MaBNS;
    }

    public void setMaBNS(String MaBNS) {
        this.MaBNS = MaBNS;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    
    
}
