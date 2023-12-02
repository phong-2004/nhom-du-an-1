<<<<<<< HEAD:src/com/qlns/entity/QLChamCong.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlns.entity;

import java.time.DateTimeException;

/**
 *
 * @author tuann
 */
public class QLChamCong {
    private String MaCC;
    private String MaNV;
    private String NgayGioVaoCa;
    private String NgayGioRaCa;

    public QLChamCong() {
    }

    public QLChamCong(String MaCC, String MaNV, String NgayGioVaoCa, String NgayGioRaCa) {
        this.MaCC = MaCC;
        this.MaNV = MaNV;
        this.NgayGioVaoCa = NgayGioVaoCa;
        this.NgayGioRaCa = NgayGioRaCa;
    }

    public String getMaCC() {
        return MaCC;
    }

    public void setMaCC(String MaCC) {
        this.MaCC = MaCC;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayGioVaoCa() {
        return NgayGioVaoCa;
    }

    public void setNgayGioVaoCa(String NgayGioVaoCa) {
        this.NgayGioVaoCa = NgayGioVaoCa;
    }

    public String getNgayGioRaCa() {
        return NgayGioRaCa;
    }

    public void setNgayGioRaCa(String NgayGioRaCa) {
        this.NgayGioRaCa = NgayGioRaCa;
    }

    
    
    
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlns.entity;

import java.time.DateTimeException;

/**
 *
 * @author tuann
 */
public class ClassQLChamCong {
    
    private String MaNV;
    private String HoVaTen;
    private String NgayGioVaoCa;
    private String NgayGioRaCa;

    public ClassQLChamCong() {
    }

    public ClassQLChamCong(String MaNV, String HoVaTen,String NgayGioVaoCa, String NgayGioRaCa) {
        
        this.MaNV = MaNV;
        this.HoVaTen = HoVaTen;
        this.NgayGioVaoCa = NgayGioVaoCa;
        this.NgayGioRaCa = NgayGioRaCa;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public String getNgayGioVaoCa() {
        return NgayGioVaoCa;
    }

    public void setNgayGioVaoCa(String NgayGioVaoCa) {
        this.NgayGioVaoCa = NgayGioVaoCa;
    }

    public String getNgayGioRaCa() {
        return NgayGioRaCa;
    }

    public void setNgayGioRaCa(String NgayGioRaCa) {
        this.NgayGioRaCa = NgayGioRaCa;
    }

    
    
    
}
>>>>>>> 24c9ff1ebc2058c2168cb2f9b88a241f8bcea3db:src/com/qlns/entity/ClassQLChamCong.java
