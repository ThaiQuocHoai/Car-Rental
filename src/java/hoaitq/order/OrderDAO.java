/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.order;

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
public class OrderDAO implements Serializable {

    public ArrayList<OrderDTO> getOrder(String cate, String name) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select id_car, car_name, rental_date, return_date, o.quantity "
                        + "from order_detail o join car ca on o.id_car = ca.id join category cate on cate.id = ca.IdCate "
                        + "where car_name like ? and cate.CateName like ? and status = 1 ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, "%" + cate + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String carName = rs.getString(2);
                    String rental = rs.getString(3);
                    String back = rs.getString(4);
                    int quantity = rs.getInt(5);

                    OrderDTO dto = new OrderDTO(id, name, rental, back, quantity);
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

    public void insertOrder(String fullname, String phone, String address, String username, int id, String code) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into [order](Fullname, Phone, Address, UserName, idDiscount, discountCode) "
                        + "values(?,?,?,?,?,?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, fullname);
                pst.setString(2, phone);
                pst.setString(3, address);
                pst.setString(4, username);
                pst.setInt(5, id);
                pst.setString(6, code);
                rs = pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void insertOrderNoDiscount(String fullname, String phone, String address, String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into [order](Fullname, Phone, Address, UserName) "
                        + "values(?,?,?,?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, fullname);
                pst.setString(2, phone);
                pst.setString(3, address);
                pst.setString(4, username);
                rs = pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int getIdMax() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select max(Id) "
                        + "from [order] ";
                pst = con.prepareStatement(sql);
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
        return 0;
    }

    public void insertOrderDetail(String rental, String back, int idCar, int idInvoice, String carName, String color, int year, float price, int quantity, int status, int rate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into order_detail(rental_date, return_date, id_car, id_invoice, car_name, color, year, price, quantity, status, rate) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, rental);
                pst.setString(2, back);
                pst.setInt(3, idCar);
                pst.setInt(4, idInvoice);
                pst.setString(5, carName);
                pst.setString(6, color);
                pst.setInt(7, year);
                pst.setFloat(8, price);
                pst.setInt(9, quantity);
                pst.setInt(10, status);
                pst.setInt(11, rate);
                rs = pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<OrderDetailDTO> getOrderDetail(int idIn) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDetailDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select rental_date, return_date, id_car, id_invoice, car_name, color, year, price, quantity, status, rate "
                        + "from order_detail "
                        + "where id_invoice = ? and status = 1 ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, idIn);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String rental = rs.getString(1);
                    String back = rs.getString(2);
                    int idCar = rs.getInt(3);
                    int idInvoice = rs.getInt(4);
                    String carName = rs.getString(5);
                    String color = rs.getString(6);
                    int year = rs.getInt(7);
                    float price = rs.getFloat(8);
                    int quantity = rs.getInt(9);
                    int status = rs.getInt(10);
                    float rate = rs.getFloat(11);

                    OrderDetailDTO dto = new OrderDetailDTO(rental, back, idCar, idInvoice, carName, color, year, price, quantity, status, rate);
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

    public ArrayList<ViewOrderDTO> getOrderAll(int page, String Uname) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ViewOrderDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select Id, Fullname, Phone, Address, DateOrder, UserName, idDiscount, discountCode \n"
                        + "from [order] "
                        + "where UserName like ? "
                        + "order by Id desc "
                        + "offset ?*2 rows "
                        + "fetch next 2 rows only ";
                pst = con.prepareStatement(sql);
                pst.setString(1, Uname);
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String fullname = rs.getString(2);
                    String phone = rs.getString(3);
                    String address = rs.getString(4);
                    String dateOrder = rs.getString(5);
                    String username = rs.getString(6);
                    int idDiscount = rs.getInt(7);
                    String discountCode = rs.getString(8);

                    ViewOrderDTO dto = new ViewOrderDTO(id, fullname, phone, address, dateOrder, username, idDiscount, discountCode);
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

    public int getOrderAllCount(String Uname) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select count(Id) "
                        + "from [order] "
                        + "where UserName like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, Uname);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public int cancelOrderDetail(String rental, String back, int id, int idCar) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "update order_detail "
                        + "set status = 0 "
                        + "where id_car = ? and id_invoice = ? and rental_date = ? and return_date = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, idCar);
                pst.setInt(2, id);
                pst.setString(3, rental);
                pst.setString(4, back);
                rs = pst.executeUpdate();

            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rs;
    }

    public int feedbackOrderDetail(String rental, String back, int id, int idCar, float rate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "update order_detail "
                        + "set rate = ? "
                        + "where id_car = ? and id_invoice = ? and rental_date = ? and return_date = ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, rate);
                pst.setInt(2, idCar);
                pst.setInt(3, id);
                pst.setString(4, rental);
                pst.setString(5, back);
                rs = pst.executeUpdate();

            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rs;
    }

    public float avgRate(int idCar) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        float rate = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select avg(rate) "
                        + "from order_detail "
                        + "where id_car = ? and rate > 0 ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, idCar);
                rs = pst.executeQuery();
                if (rs.next()) {
                    rate = rs.getFloat(1);
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
        return rate;
    }

    public void updateRate(int idCar, float rate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "update car "
                        + "set Rate = ? "
                        + "where id = ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, rate);
                pst.setInt(2, idCar);
                pst.executeUpdate();

            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<Integer> getIdbySearchOrder(String name, String date, int page, String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Integer> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select id_invoice "
                        + "from [order_detail] o join [order] r on o.id_invoice = r.Id "
                        + "where o.car_name like ? and r.DateOrder like ? and r.UserName like ? and o.status = 1 "
                        + "order by o.id_invoice desc \n"
                        + "offset ?*2 rows \n"
                        + "fetch next 2 rows only ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, "%" + date + "%");
                pst.setString(3, username);
                pst.setInt(4, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(id);
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

    public int getCountIdbySearchOrder(String name, String date, String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Integer> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select count(id_invoice) "
                        + "from [order_detail] o join [order] r on o.id_invoice = r.Id "
                        + "where o.car_name like ? and r.DateOrder like ? and r.UserName like ? and o.status = 1 ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, "%" + date + "%");
                pst.setString(3, username);
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
        return 0;
    }

    public ArrayList<ViewOrderDTO> getOrderbySearchOrder(int id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ViewOrderDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select Id, Fullname, Phone, Address, DateOrder, UserName, idDiscount, discountCode "
                        + "from [order] "
                        + "where Id = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt(1);
                    String fullname = rs.getString(2);
                    String phone = rs.getString(3);
                    String address = rs.getString(4);
                    String dateOrder = rs.getString(5);
                    String username = rs.getString(6);
                    int idDiscount = rs.getInt(7);
                    String discountCode = rs.getString(8);

                    ViewOrderDTO dto = new ViewOrderDTO(Id, fullname, phone, address, dateOrder, username, idDiscount, discountCode);
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

}
