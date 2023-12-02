
package com.qlns.utils;
import java.awt.Component;
import javax.swing.JOptionPane;


public class MsgBox {
    public static void alert(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message,"Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
    //hiển thị thông báo người dùng xác nhận
    public static boolean confirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message,"Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    //hiển thị thông báo yêu cầu nhập dl
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
}
