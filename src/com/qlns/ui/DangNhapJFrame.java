package com.qlns.ui;


import com.qlns.dao.NhanVienDAO;
import com.qlns.entity.BNS;
import com.qlns.entity.nv01;
import com.qlns.entity.NhanVien;
import com.qlns.utils.Auth;
import javax.swing.JOptionPane;
import com.qlns.utils.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
   if(checkNull()){
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

        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("User");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel2.setText("Mật khẩu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass)
                    .addComponent(txtUser)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 291, Short.MAX_VALUE)
                        .addComponent(btnXacNhan))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnXacNhan)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        logIn();
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
