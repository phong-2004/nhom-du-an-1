
package com.qlns.entity;

public class BNS {
        private String MaNV;
       
        private String MatKhau;
      
        public BNS(String MaNV,String MatKhau) {
            this.MaNV = MaNV;
           
            this.MatKhau = MatKhau;
           
        }

    public BNS() {
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
    
    }



