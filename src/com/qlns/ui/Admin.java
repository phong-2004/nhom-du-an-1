package com.qlns.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import com.qlns.dao.NhanVienDAO;
import com.qlns.entity.Account_Admins;
import com.qlns.entity.nv01;
import com.qlns.utils.Auth;
import com.qlns.utils.JDBC;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class Admin extends javax.swing.JFrame {

    java.util.List<nv01> a = new ArrayList<>();
    private DefaultTableModel tableModel;
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

    private String userLogin = "123";
    private String filePath;
    private Map<String, String> phongBanMap = new HashMap<>();
    DefaultTableModel model = new DefaultTableModel(new String[]{"Mã NV", "Họ Tên", "Số ĐT", "Email", "CCCD", "Ngày Sinh", "Giới Tính", "Mật Khẩu", "Địa Chỉ", "Bậc Lương", "Ảnh", "Mã PB"}, 0);
    int index = 0;
 //private Connection conn;
    public Admin() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataToTable();
        phongBanMap = new HashMap<>();
     
    }

    private void Savedata() {

        String manv = txtmanv.getText();
        String hoten = txthoten.getText();
        String email = txtemail.getText();
        String sdt = txtsodt.getText();
        String cccd = txtcccd.getText();
        java.util.Date selectedDateUtil = txtngaysinh.getDate();
        boolean gioitinh = rdonam.isSelected();
        String matkhaunv = String.valueOf(txtmatkhau.getPassword());
        String diachi = txtdiachi.getText();
        String bacluong = txtluong.getText();
        String imgnv = filePath;
        String tenPhongBan = (String) cbPhongBan.getSelectedItem();
        String maPhongBan = phongBanMap.get(tenPhongBan);

        try {
//            String url = "jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;";
//            String username = "SA";
//            String password = "phong123aa";
//
//            Connection conn = DriverManager.getConnection(url, username, password);

            String insertQuery;
            if (rdoquanly.isSelected()) {
                insertQuery = "INSERT INTO quanly (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, Anh, BacLuong,MaPB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                insertQuery = "INSERT INTO NhanVien (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, Anh, BacLuong,MaPB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            PreparedStatement pstmt = JDBC.getStmt(insertQuery);
            pstmt.setString(1, manv);
            pstmt.setString(2, hoten);
            pstmt.setString(3, sdt);
            pstmt.setString(4, email);
            pstmt.setString(5, cccd);
            pstmt.setDate(6, new java.sql.Date(selectedDateUtil.getTime()));
            pstmt.setBoolean(7, gioitinh);
            pstmt.setString(8, matkhaunv);
            pstmt.setString(9, diachi);
            pstmt.setString(10, bacluong);
            pstmt.setString(11, imgnv);
            pstmt.setString(12, maPhongBan);

            pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            clearInputs();
            loadDataToTable();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            //set hình ảnh
            try {
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;user=SA;password=phong123aa"); // kết nối database
                String sql = "UPDATE quanly set anh =? WHERE MaNV=?"; // lệnh SQL
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, filePath);
                st.setString(2, userLogin);
                st.execute();
                con.close();
                System.out.println("Add img databse");
                //  initDataUser();
                khoitaoUser(filePath);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    public void khoitaoUser(String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblanhnv.setIcon(new ImageIcon(scaledImage));
    }

private void loadDataToTable() {
        //String phongban = cbPhongBan.getItemAt(index);
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        tblthongtin.setModel(model);
        try {
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;user=SA;password=mtuan120304"); // kết nối database
            // Connection conn2 = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;user=SA;password=phong123aa"); // kết nối database
            String sql = "SELECT * FROM quanly";
            
            ResultSet rs = JDBC.query(sql);
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
            String sql2 = "SELECT * FROM NhanVien";
            ResultSet rs2 = JDBC.query(sql2);
//            Statement statement2 = conn.createStatement();
//            ResultSet rs2 = statement2.executeQuery(sql2);

            model.setRowCount(0);
            //   model.addRow(new Object[]{"t"});
            //  System.out.println("nnnnn");

            while (rs.next()) {
                String manv = rs.getString("MaQL");
                String hovaten = rs.getString("HoVaTen");
                String email = rs.getString("Email");
                String sodt = rs.getString("SDT");
                String cccd = rs.getString("cccd");
                Date ngaysinh = rs.getDate("NgaySinh");
                java.util.Date ngaysinhUtil = new java.util.Date(ngaysinh.getTime());

                boolean gioitinh = rs.getBoolean("GioiTinh");

                String matkhau = rs.getString("MatKhau");
                String diachi = rs.getString("DiaChi");
                //    String imgnv = rs.getString("imgnv");

                String gioitinhStr = gioitinh ? "Nam" : "Nữ";
                String luongasd = rs.getString("BacLuong");
                String anh = rs.getString("Anh");
                String MaPB = rs.getString("MaPB");
                model.addRow(new Object[]{manv, hovaten, sodt, email,
                    cccd, ngaysinh, gioitinhStr, matkhau, diachi, luongasd, anh, MaPB});

            }
            rs.close();
//            statement.close();

            while (rs2.next()) {
                String manv = rs2.getString("MaNV");
                String hovaten = rs2.getString("HoVaTen");
                String email = rs2.getString("Email");
                String sodt = rs2.getString("SDT");
                String cccd = rs2.getString("cccd");
                Date ngaysinh = rs2.getDate("NgaySinh");
                java.util.Date ngaysinhUtil = new java.util.Date(ngaysinh.getTime());

                boolean gioitinh = rs2.getBoolean("GioiTinh");

                String matkhau = rs2.getString("MatKhau");
                String diachi = rs2.getString("DiaChi");
                //    String imgnv = rs.getString("imgnv");

                String gioitinhStr = gioitinh ? "Nam" : "Nữ";
                String luongasd = rs2.getString("BacLuong");
                String anh = rs2.getString("Anh");
                String MaPB = rs2.getString("MaPB");
                model.addRow(new Object[]{manv, hovaten, sodt, email,
                    cccd, ngaysinh, gioitinhStr, matkhau, diachi, luongasd, anh, MaPB});

            }

            //     System.out.println(model.getValueAt(1, 1));
            rs2.close();
//            statement2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
 public String layTenPhongBan(String maPhongBan) {
        String tenPhongBan = null;

        try {
//            String url = "jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;";
//            String username = "SA";
//            String password = "phong123aa";
//
//            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT TenPB FROM PhongBan WHERE MaPB = ?";
            PreparedStatement ps = JDBC.getStmt(query);
            ps.setString(1, maPhongBan);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tenPhongBan = rs.getString("TenPB");
            }

            rs.close();
            ps.close();
//            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có
        }

        return tenPhongBan;
    }



    private void clickTable( ) {

        if (model.getRowCount() < 0) {
            index = 0;
            return;
        }
        txtmanv.setText(model.getValueAt(index, 0).toString());
        txthoten.setText(model.getValueAt(index, 1).toString());
        txtsodt.setText(model.getValueAt(index, 2).toString());
        txtemail.setText(model.getValueAt(index, 3).toString());
        txtcccd.setText(model.getValueAt(index, 4).toString());
        java.util.Date selectedDateUtil = (java.util.Date) model.getValueAt(index, 5);
        txtngaysinh.setDate(selectedDateUtil);
        rdonam.setSelected(model.getValueAt(index, 6).toString() == "Nam" ? true : false);
        rdonu.setSelected(model.getValueAt(index, 6).toString() == "Nam" ? false : true);
        txtdiachi.setText(model.getValueAt(index, 8).toString());
        txtmatkhau.setText(model.getValueAt(index, 7).toString());
        rdoquanly.setSelected(model.getValueAt(index, 0).toString().startsWith("QL00") ? true : false);
        rdonhanvien.setSelected(model.getValueAt(index, 0).toString().startsWith("NV00") ? true : false);
        ImageIcon icon = new ImageIcon(model.getValueAt(index, 10).toString());
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblanhnv.setIcon(new ImageIcon(scaledImage));

        txtluong.setText(model.getValueAt(index, 9).toString());
      
     String maPhongBan = model.getValueAt(index, 11).toString();

    // Gọi hàm để lấy tên phòng ban tương ứng
    String tenPhongBan = layTenPhongBan(maPhongBan);

    
    if (tenPhongBan != null) {
        cbPhongBan.setSelectedItem(tenPhongBan);
    }

    }

    private void clearInputs() {
        // nhân viên
        txtmanv.setText("");
        txthoten.setText("");
        txtsodt.setText("");
        txtemail.setText("");
        txtcccd.setText("");
        txtngaysinh.setDate(null);
        rdonam.setSelected(true);
        rdoquanly.setSelected(true);
        txtmatkhau.setText("");
        txtdiachi.setText("");
        lblanhnv.setIcon(null);
        txtluong.setText("");

    }

    private boolean isValidNumber(String input) {

        String regex = "^[0-9]{1,10}$";
        return Pattern.matches(regex, input);
    }

    private boolean isValidNumber2(String input) {

        String regex = "^[0-9]{12}$";
        return Pattern.matches(regex, input);
    }

    private boolean isValidPassword(String password) {
        // Kiểm tra mật khẩu không có khoảng trắng và chỉ chứa chữ không dấu, ít nhất 1 ký tự viết hoa và 1 chữ số
        return password.matches("^[a-zA-Z0-9]+$") && password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtsodt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcccd = new javax.swing.JTextField();
        txtngaysinh = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        rdoquanly = new javax.swing.JRadioButton();
        rdonhanvien = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdiachi = new javax.swing.JTextArea();
        txtmatkhau = new javax.swing.JPasswordField();
        cboxanhien = new javax.swing.JCheckBox();
        jLabel33 = new javax.swing.JLabel();
        lblanhnv = new javax.swing.JLabel();
        btnanhnv = new javax.swing.JButton();
        txtluong = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbPhongBan = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblthongtin = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnthemnv = new javax.swing.JButton();
        btnxoanv = new javax.swing.JButton();
        btnlammoinv = new javax.swing.JButton();
        btnsuanv = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BAN NHÂN SỰ <3");

        jTabbedPane1.setBackground(new java.awt.Color(70, 73, 75));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BAN NHÂN SỰ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        jTabbedPane1.setForeground(new java.awt.Color(255, 0, 0));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân Viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setText("Mã Nhân Viên : ");

        jLabel3.setText("Họ Và Tên");

        txtmanv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtmanvMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Nhân Viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel4.setText("Số Điện Thoại : ");

        jLabel5.setText("Email : ");

        jLabel6.setText("Số CCCD: ");

        txtngaysinh.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Ngày Sinh : ");

        jLabel8.setText("Giới Tính :");

        buttonGroup1.add(rdonam);
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        jLabel9.setText("Vai Trò :");

        buttonGroup2.add(rdoquanly);
        rdoquanly.setSelected(true);
        rdoquanly.setText("Quản Lý");

        buttonGroup2.add(rdonhanvien);
        rdonhanvien.setText("Nhân Viên");

        jLabel11.setText("Mật Khẩu:");

        jLabel12.setText("Địa Chỉ : ");

        txtdiachi.setColumns(20);
        txtdiachi.setRows(5);
        jScrollPane1.setViewportView(txtdiachi);

        cboxanhien.setBackground(new java.awt.Color(204, 204, 255));
        cboxanhien.setText("Ẩn / Hiện Mật Khẩu");
        cboxanhien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxanhienActionPerformed(evt);
            }
        });

        jLabel33.setText("Ảnh :");

        btnanhnv.setBackground(new java.awt.Color(255, 51, 51));
        btnanhnv.setText("Ảnh NV");
        btnanhnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanhnvActionPerformed(evt);
            }
        });

        jLabel36.setText("Lương :");

        jLabel1.setText("Phòng Ban : ");

        cbPhongBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng Kinh Doanh", "Phòng Marketing", "Phòng IT", "Phòng Tài Chính", "Phong Ban A" }));
        cbPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPhongBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcccd, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(txtngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdonam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdonu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtluong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblanhnv, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnanhnv)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(rdoquanly)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdonhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(cboxanhien, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(cbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmatkhau))
                        .addContainerGap(46, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoquanly)
                        .addComponent(rdonhanvien))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(9, 9, 9)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxanhien)
                            .addComponent(jLabel33))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(rdonam)
                                    .addComponent(rdonu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addComponent(lblanhnv, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnanhnv))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        tblthongtin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "Số ĐT", "Email", "CCCD", "Ngày Sinh ", "Giới Tính ", "Địa Chỉ ", "Mật Khẩu ", "Bậc Lương", "ẢNh", "Mã PB"
            }
        ));
        tblthongtin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblthongtinMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblthongtin);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        jLabel13.setText("Tìm Nhân Viên : ");

        btnsearch.setBackground(new java.awt.Color(255, 0, 51));
        btnsearch.setText("Tìm Kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        btnthemnv.setBackground(new java.awt.Color(255, 51, 51));
        btnthemnv.setText("Thêm");
        btnthemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemnvActionPerformed(evt);
            }
        });
        jPanel8.add(btnthemnv);

        btnxoanv.setBackground(new java.awt.Color(255, 51, 51));
        btnxoanv.setText("Xóa");
        btnxoanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoanvActionPerformed(evt);
            }
        });
        jPanel8.add(btnxoanv);

        btnlammoinv.setBackground(new java.awt.Color(255, 0, 0));
        btnlammoinv.setText("Làm Mới");
        btnlammoinv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoinvActionPerformed(evt);
            }
        });
        jPanel8.add(btnlammoinv);

        btnsuanv.setBackground(new java.awt.Color(255, 0, 51));
        btnsuanv.setText("Cập Nhật");
        jPanel8.add(btnsuanv);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel2);

        jButton1.setText("Quay lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlammoinvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoinvActionPerformed
        clearInputs();
    }//GEN-LAST:event_btnlammoinvActionPerformed

    private void btnxoanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnxoanvActionPerformed

    private void btnthemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemnvActionPerformed

        String manv = txtmanv.getText();
        String hoten = txthoten.getText();
        String email = txtemail.getText();
        String sdt = txtsodt.getText();
        String cccd = txtcccd.getText();
        java.util.Date selectedDateUtil = txtngaysinh.getDate();
        //     boolean gioitinh = rdonam.isSelected()
        String matkhaunv = String.valueOf(txtmatkhau.getPassword());
        String diachi = txtdiachi.getText();
        Icon anhnv = lblanhnv.getIcon();

        //        // Kiểm tra trùng 'manv' và 'taikhoan' trước khi lưu vào database
//                try {
//                        if (nv01.isManvDuplicate(manv)) {
//                                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại trong database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                                return;
//                            }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//        
//                try {
//                        if (EmployeeManager.isTaikhoanDuplicate(taikhoannv)) {
//                                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại trong database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                                return;
//                            }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//                    }
        if (manv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (hoten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ Tên Nhân Viên không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Căn Cước không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matkhaunv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật Khẩu không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được bỏ trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedDateUtil == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ép kiểu từ java.util.Date sang java.sql.Date
        java.sql.Date selectedDate = new java.sql.Date(selectedDateUtil.getTime());
        if (hoten.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Tên người dùng không được chứa số!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Định dạng Email không hợp lệ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidNumber(sdt)) {
            JOptionPane.showMessageDialog(this, "Số Điện thoại chỉ được nhập số và dưới > 11 số", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidNumber2(cccd)) {
            JOptionPane.showMessageDialog(this, "Số Căn cước công dân chỉ được nhập số và 12 số", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidPassword(matkhaunv)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự 1 chữ số 1 chữ in hoa và không chứa khoảng trắng.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rdoquanly.isSelected()) {
            if (!txtmanv.getText().startsWith("QL00")) {
                JOptionPane.showMessageDialog(this, "Tài Khoản quản lí phải bắt đầu là QL00");
                return;
            }
        }
        if (rdonhanvien.isSelected()) {
            if (!txtmanv.getText().startsWith("NV00")) {
                JOptionPane.showMessageDialog(this, "Tài Khoản nhân viên phải bắt đầu là NV00");
                return;
            }
        }
        if (anhnv == null) {
            JOptionPane.showMessageDialog(this, "Chưa tải ảnh nhân viên ");
            return;
        }

        Savedata();
    }//GEN-LAST:event_btnthemnvActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed

//        String maNV = txtsearch.getText().trim();
//        if (maNV.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm !", "Thông báo",
//                JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QLNS1;encrypt=false;user=SA;password=phong123aa");)
//        {
//            String sql = "SELECT * FROM Managers WHERE manv = ?";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, maNV);
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                //  clickTable();
//
//                // System.out.println(maNV);
//            }else {
//                JOptionPane.showMessageDialog(this, "Nhân viên không tồn tại ");
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_btnsearchActionPerformed

    private void tblthongtinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongtinMouseClicked
        // TODO add your handling code here:
        index = tblthongtin.getSelectedRow();
        //  System.out.println(index);

        clickTable();
    }//GEN-LAST:event_tblthongtinMouseClicked

    private void btnanhnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanhnvActionPerformed
        chooseFile();
    }//GEN-LAST:event_btnanhnvActionPerformed

    private void cboxanhienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxanhienActionPerformed
        if (cboxanhien.isSelected()) {
            txtmatkhau.setEchoChar((char) 0); // Show the password in plain text
        } else {
            txtmatkhau.setEchoChar('*'); // Mask the password
        }
    }//GEN-LAST:event_cboxanhienActionPerformed

    private void txtmanvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmanvMouseClicked
        //  clearInputs();
    }//GEN-LAST:event_txtmanvMouseClicked

    private void cbPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPhongBanActionPerformed
      

    }//GEN-LAST:event_cbPhongBanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new BanNhanSuJFrame().setVisible(true);
        dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanhnv;
    private javax.swing.JButton btnlammoinv;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsuanv;
    private javax.swing.JButton btnthemnv;
    private javax.swing.JButton btnxoanv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbPhongBan;
    private javax.swing.JCheckBox cboxanhien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblanhnv;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonhanvien;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JRadioButton rdoquanly;
    private javax.swing.JTable tblthongtin;
    private javax.swing.JTextField txtcccd;
    private javax.swing.JTextArea txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtluong;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JPasswordField txtmatkhau;
    private com.toedter.calendar.JDateChooser txtngaysinh;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsodt;
    // End of variables declaration//GEN-END:variables
}
