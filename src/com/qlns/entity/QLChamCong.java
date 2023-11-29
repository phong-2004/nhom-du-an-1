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
