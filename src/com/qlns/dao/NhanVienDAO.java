
package com.qlns.dao;

import com.qlns.entity.NhanVien;
import com.qlns.utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




public class NhanVienDAO {
   String SELECT_BY_ID_SQL = "select * from nhanvien where MaNV = ?";
     
 public NhanVien selectById(String manv) {
       List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, manv);    
       if(list.isEmpty()){   // isEmpty là trống, rỗng, không có giá trị
         
           return null;
       }
       return list.get(0);
 }  
   protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoVaTen(rs.getString("HoVaTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setQueQuan(rs.getString("QueQuan"));
                entity.setSDT(rs.getFloat("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setChucVu(rs.getString("ChucVu"));
                entity.setBacLuong(rs.getString("BacLuong"));
                entity.setMaPB(rs.getString("MaPB"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }


}
