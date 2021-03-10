/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.discount;

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
public class DiscountDAO implements Serializable {

    public DiscountDTO checkDiscount(String code, String now) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DiscountDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select IdDiscount, Precent, ExperisionDate "
                        + "from discount "
                        + "where CodeDiscount like ? and status = 1 and ExperisionDate >= ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                pst.setString(2, now);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    int percent = rs.getInt(2);
                    String date = rs.getString(3);
                    dto = new DiscountDTO(id, code, percent, date, 1);
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
}
