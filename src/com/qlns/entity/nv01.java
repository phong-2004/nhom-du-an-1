/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlns.entity;

/**
 *
 * @author ACER
 */
public class nv01 {
           private String MaNV;
       
        private String MatKhau;
        private String HoVaTen;
      
        public nv01(String MaNV,String MatKhau, String HoVaTen) {
            this.MaNV = MaNV;
           
            this.MatKhau = MatKhau;
            this.HoVaTen = HoVaTen;
           
        }

    public nv01() {
    }
        

    

        
        
        // Getter và setter cho MaNV
        public String getMaNV() {
            return MaNV;
        }
    
        public void setMaNV(String maNV) {
            this.MaNV = maNV;
        }
    
       
        public String getMatKhau() {
            return MatKhau;
        }
    
        public void setMatKhau(String matKhau) {
            this.MatKhau = matKhau;
        }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }
        
}
