/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.car;

import hoaitq.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author QH
 */
public class CarDAO implements Serializable {

    public ArrayList<String> getCate() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<String> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select CateName "
                        + "from [category] ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String cate = rs.getString("CateName");
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(cate);
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
        return list;
    }

    public ArrayList<CarDTO> getCar(String cate, String name, int page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<CarDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select c.id, CarName, Image, Color, [Year], Price, Quantity, Rate, ca.CateName from car c join category ca on c.IdCate = ca.id "
                        + "where ca.CateName like ? and c.CarName like ? "
                        + "order by CreateDate DESC "
                        + "offset ?*12 rows "
                        + "fetch next 12 rows only ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + cate + "%");
                pst.setString(2, "%" + name + "%");
                pst.setInt(3, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String carName = rs.getString(2);
                    String image = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    float rate = rs.getFloat(8);
                    String cateName = rs.getString(9);
                    CarDTO dto = new CarDTO(id, carName, image, color, year, price, quantity, rate, cateName);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }
    
    public int countCar(String cate, String name) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select COUNT(c.id) from car c join category ca on c.IdCate = ca.id "
                        + "where ca.CateName like ? and c.CarName like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + cate + "%");
                pst.setString(2, "%" + name + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
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
        return -1;
    }
}
