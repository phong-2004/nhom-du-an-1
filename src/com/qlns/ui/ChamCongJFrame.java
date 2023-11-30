package com.qlns.ui;

import com.qlns.entity.NhanVien;
import com.qlns.entity.nv01;
import com.qlns.utils.Auth;
//import com.qlns.utils.JDBC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
//import java.sql.PreparedStatement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JLabel;
import com.qlns.entity.nv01;

//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;

//import com.qlns.utils.JDBC;
public class ChamCongJFrame extends javax.swing.JFrame {

    private SimpleDateFormat dateFormat;
    private String timeIn;
    private String timeOut;
    // NhanVien LoggedInUser;
    private String HoTen;
    private String MaNV;

    public ChamCongJFrame() {

        initComponents();
        setLocationRelativeTo(null);

        updateDateTimeLabels();
        loadTimeInInfo();
        loadTimeOut();
       // hienThi();

        nv01 nv = Auth.user;

        HoTen = nv.getHoVaTen();
        txtname.setText(HoTen);

        MaNV = nv.getMaNV();
        txtMaNV.setText(MaNV);

    }

// public void hienThiTenNhanVien(String tenNhanVien) {
//     name.setText(tenNhanVien); // Hiển thị tên nhân viên lên label name
// }
    private void updateDateTimeLabels() {

        Date now = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dateFormat.format dinh dang doi tuong date thanh 1 chuoi theo dinh dang cua doi tuong SimpleDateFormat.
        txtNgayThang.setText(dateFormat.format(now));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        txtGio.setText(timeFormat.format(now));
    }

    private void loadTimeInInfo() {
        if (timeIn != null && !timeIn.isEmpty()) {
            txtTimeIn.setText(timeIn);
            txtNgayThang.setText(timeIn.substring(timeIn.indexOf("-") + 2));
            txtGio.setText(timeIn.substring(0, timeIn.indexOf("-")));
//            docFile();
        } else {
            txtTimeIn.setText("Chưa time in");
        }
    }

    private void loadTimeOut() {
        if (timeOut != null && !timeOut.isEmpty()) {
            txtTimeOut.setText(timeOut);
            txtNgayThang.setText(timeOut.substring(timeOut.indexOf("-") + 2));
            txtGio.setText(timeOut.substring(0, timeOut.indexOf("-")));
        } else {
            txtTimeOut.setText("Chưa time out");
        }
    }

    boolean isTimeIn = false;

    private void valiDateTimeIn() {
        if (!isTimeIn) {

            timeIn = new SimpleDateFormat("HH:mm:ss - yyyy-MM-dd").format(new Date());
            loadTimeInInfo();
            isTimeIn = true;
            JOptionPane.showMessageDialog(this, "Bạn đã time in thành công");

        } else {
           
            JOptionPane.showMessageDialog(this, "Bạn đã time in", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void valiDateTimeOut() {
        if (!isTimeIn) {
            
            JOptionPane.showMessageDialog(this, "Bạn cần time in trước", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            timeOut = new SimpleDateFormat("HH:mm:ss - yyyy-MM-dd").format(new Date());
            loadTimeOut();
            isTimeIn = false;
            JOptionPane.showMessageDialog(this, "Bạn đã time out thành công");
        }
    }

//    private void upDateTimeIn() {
//        try {
//            String sql = "update chamcong set NgayGioVaoCong = ? where MaNV = ?";
//            PreparedStatement ps = JDBC.getStmt(sql);
//
//            ps.setString(1, txtTimeIn.getText());
//            ps.setString(2, txtMaNV.getText());
//            ps.executeUpdate();
////            luuFile();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
//            System.out.println(ex);
//        }
//    }

    private void upDateTimeOut() {
//        try {
//            String sql = "update chamcong set NgayGioRaCong = ? where MaNV = ?";
//            PreparedStatement ps = JDBC.getStmt(sql);

//            ps.setString(1, txtTimeOut.getText());
//            ps.setString(2, txtMaNV.getText());
//            ps.executeUpdate();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
//            System.out.println(ex);
//        }
    }

//    private void luuFile() {
//        try {
//            // luu time in vào 1 file 
//            //đăng xuất
//            //đăng nhập lại 
//            //đọc file từ file đã lưu time in
//            String time_in = txtTimeIn.getText();
//
//            //tạo đối tượng File
//            File file = new File("NgayGioVaoCa.txt");
//            // tạo đối tượng FileWrite
//            FileWriter writer = new FileWriter(file);
//            //ghi dữ liệu vào file            
//            writer.write(time_in);
//            //đóng file
//            writer.close();
//
//        } catch (Exception e) {
//            System.out.println("e");
//        }
//    }
//    boolean dangxuat = false;

//    private void docFile() {
//        try {
////            if (!dangxuat) {
////                
////            } else {
////                JOptionPane.showMessageDialog(this, "Cụp cái pha xuống");
////            }
//            File file = new File("NgayGioVaoCa.txt");
//            FileReader reader = new FileReader(file);
//            String line;
//            while ((line = reader.readLine()) != null) {
//                txtTimeIn.setText(line);
//            }
//            reader.close();
//        } catch (Exception e) {
//            System.out.println("e");
////            dangxuat = true;
//        }
//    }
    
//    private void hienThi(){
//        String sql = "select * from chamcong";
//        ResultSet rs = JDBC.query(sql);
//        
//        try {
//            while(rs.next()){
//                String ngaygiovaoca = rs.getString("NgayGioVaoCong");
//                String ngaygioraca = rs.getString("NgayGioRaCong");
//                
//                txtTimeIn.setText(ngaygiovaoca);
//                txtTimeOut.setText(ngaygioraca);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }   

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtname = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayThang = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtGio = new javax.swing.JLabel();
        btntimein = new javax.swing.JButton();
        btntimeout = new javax.swing.JButton();
        txtTimeIn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTimeOut = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Họ tên");

        txtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Năm Tháng Ngày ");

        txtNgayThang.setText("jLabel4");
        txtNgayThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Giờ ");

        txtGio.setText("jLabel6");
        txtGio.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btntimein.setText("Vào ca");
        btntimein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimeinActionPerformed(evt);
            }
        });

        btntimeout.setText("Ra ca");
        btntimeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimeoutActionPerformed(evt);
            }
        });

        txtTimeIn.setText("jLabel2");
        txtTimeIn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTimeIn.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtTimeInAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel4.setText("Bạn đã time in lúc :");

        jLabel6.setText("Bạn đã time out lúc:");

        txtTimeOut.setText("jLabel7");
        txtTimeOut.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Mã NV:");

        txtMaNV.setText("jLabel7");
        txtMaNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTimeIn, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(txtTimeOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btntimein)
                .addGap(72, 72, 72)
                .addComponent(btntimeout)
                .addContainerGap(116, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtGio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayThang)
                    .addComponent(txtGio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimeIn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimeOut)
                            .addComponent(jLabel6))
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntimein)
                            .addComponent(btntimeout))
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntimeinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimeinActionPerformed
        valiDateTimeIn();
//        upDateTimeIn();

    }//GEN-LAST:event_btntimeinActionPerformed

    private void btntimeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimeoutActionPerformed
        valiDateTimeOut();
        upDateTimeOut();

    }//GEN-LAST:event_btntimeoutActionPerformed

    private void txtTimeInAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtTimeInAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimeInAncestorAdded

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChamCongJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChamCongJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChamCongJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChamCongJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChamCongJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntimein;
    private javax.swing.JButton btntimeout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel txtGio;
    private javax.swing.JLabel txtMaNV;
    private javax.swing.JLabel txtNgayThang;
    private javax.swing.JLabel txtTimeIn;
    private javax.swing.JLabel txtTimeOut;
    private javax.swing.JLabel txtname;
    // End of variables declaration//GEN-END:variables
}
