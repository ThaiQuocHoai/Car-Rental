/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.user;

import hoaitq.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author QH
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String username, String password) throws NamingException, SQLException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select UserName, Password, Fullname, Phone, Address, CreateDate, Role, Status, VerifyCode "
                        + "from [User] "
                        + "where UserName = ? and Password = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString(1);
                    String pwd = rs.getString(2);
                    String fullname = rs.getString(3);
                    String phone = rs.getString(4);
                    String address = rs.getString(5);
                    String createDate = rs.getString(6);
                    int Role = rs.getInt(7);
                    String status = rs.getString(8);
                    String verifyCode = rs.getString(9);
                    dto = new UserDTO(email, pwd, fullname, phone, address, createDate, Role, status, verifyCode);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    public int createUser(String username, String password, String fullname, String address, String phone, String now, String VerifyCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into [User](UserName, Password, Fullname, Address, Phone, CreateDate, Role, Status, VerifyCode) "
                        + "values(?,?,?,?,?,?,0,'New',?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, fullname);
                pst.setString(4, address);
                pst.setString(5, phone);
                pst.setString(6, now);
                pst.setString(7, VerifyCode);
                rs = pst.executeUpdate();
                return rs;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
        return rs;
    }

    public UserDTO checkOTP(String username, String VerifyCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select UserName, Password, Fullname, Address, Phone, CreateDate, Role, Status, VerifyCode "
                        + "from [User] "
                        + "where UserName = ? and VerifyCode = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, VerifyCode);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString(1);
                    String pwd = rs.getString(2);
                    String fullname = rs.getString(3);
                    String address = rs.getString(4);
                    String phone = rs.getString(5);
                    String createDate = rs.getString(6);
                    int Role = rs.getInt(7);
                    String status = rs.getString(8);
                    String verifyCode = rs.getString(9);
                    dto = new UserDTO(email, pwd, fullname, phone, address, createDate, Role, status, verifyCode);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
        return dto;
    }
    
    public int updateStatus(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "update [User] "
                        + "set Status = 'Active' "
                        + "where UserName = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeUpdate();
                return rs;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
        return rs;
    }

}
