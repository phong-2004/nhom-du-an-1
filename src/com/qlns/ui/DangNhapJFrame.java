package com.qlns.ui;

import com.qlns.dao.NhanVienDAO;
import com.qlns.entity.BNS;
import com.qlns.entity.nv01;
import com.qlns.entity.NhanVien;
import com.qlns.utils.Auth;
import javax.swing.JOptionPane;
import com.qlns.utils.JDBC;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class DangNhapJFrame extends javax.swing.JFrame {

    List<BNS> admin = new ArrayList<>();
    List<nv01> a = new ArrayList<>();
   
    //private NhanVien LoggedInUser;
    public DangNhapJFrame() {
        initComponents();
        setLocationRelativeTo(null);
       
    }
   
     
    boolean checkNull() {
        String user = txtUser.getText();
        String pass = String.valueOf(txtPass.getPassword());
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User không được bỏ trống ", "thông báo", JOptionPane.ERROR_MESSAGE);
            txtUser.requestFocus();
            return false;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password không được bỏ trống ", "thông báo", JOptionPane.ERROR_MESSAGE);
            txtPass.requestFocus();
            return false;
        }

        return true;
    }
    NhanVienDAO nvDAO = new NhanVienDAO();

    void logIn() {
        if (checkNull()) {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;user=SA;password=phong123aa";
            Connection con;
            try {
                admin.removeAll(admin);
                con = DriverManager.getConnection(connectionUrl);
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("Select * from BNS");
                while (rs.next()) {
                    BNS ad = new BNS(rs.getString("MaNV"), rs.getString("MatKhau"));

                    admin.add(ad);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (BNS a : admin) {
                if (a.getMaNV().equals(txtUser.getText().trim()) && (a.getMatKhau().equals(new String(txtPass.getPassword()).trim()))) {
                    JOptionPane.showMessageDialog(this, "Login Thành công ");
                    BanNhanSuJFrame adminFrame = new BanNhanSuJFrame(); // Tạo một biến mới cho frame
                    adminFrame.setVisible(true);
                    dispose();
                    return;
                }
            }

            try {
                //retainAll 
                a.retainAll(a);
                con = DriverManager.getConnection(connectionUrl);
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("Select * from NhanVien");
                while (rs.next()) {
                    nv01 ad = new nv01(rs.getString("MaNV"), rs.getString("MatKhau"), rs.getString("HoVaTen"));
                    a.add(ad);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (nv01 a : a) {
                if (a.getMaNV().equals(txtUser.getText().trim()) && (a.getMatKhau().equals(new String(txtPass.getPassword()).trim()))) {
                    JOptionPane.showMessageDialog(this, "Login Thành công ");
                    Auth.user = a;
                    ChamCongJFrame as = new ChamCongJFrame();
                    as.setVisible(true);
                    dispose();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Login Thất bại, sai Tài Khoản Hoặc Mật Khẩu");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        forgetpass = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        ckb1 = new javax.swing.JCheckBox();
        txtPass = new javax.swing.JPasswordField();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        forgetpass.setForeground(new java.awt.Color(255, 51, 102));
        forgetpass.setText("Bạn Quên Mật Khẩu ?");
        forgetpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetpassMouseClicked(evt);
            }
        });

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        ckb1.setText("Ẩn Hiên Mật Khẩu.");
        ckb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb1ActionPerformed(evt);
            }
        });

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        jLabel2.setText("Mật khẩu");

        jLabel1.setText("Mã Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser)
                            .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ckb1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(forgetpass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXacNhan)
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckb1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forgetpass)
                    .addComponent(btnXacNhan))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        logIn();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void forgetpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetpassMouseClicked
        new QuenmatkhauJFame().setVisible(true);
        dispose();
    }//GEN-LAST:event_forgetpassMouseClicked

    private void ckb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb1ActionPerformed
        if (ckb1.isSelected()) {
            txtPass.setEchoChar((char) 0);
        } else {
            txtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_ckb1ActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JCheckBox ckb1;
    private javax.swing.JLabel forgetpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    
}
