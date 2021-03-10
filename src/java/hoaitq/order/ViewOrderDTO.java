/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.order;

import java.io.Serializable;

/**
 *
 * @author QH
 */
public class ViewOrderDTO implements Serializable, Comparable<ViewOrderDTO> {

    private int id;
    private String fullname;
    private String phone;
    private String address;
    private String dateOrder;
    private String username;
    private int idDiscount;
    private String discountCode;

    public ViewOrderDTO() {
    }

    public ViewOrderDTO(int id, String fullname, String phone, String address, String dateOrder, String username, int idDiscount, String discountCode) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.dateOrder = dateOrder;
        this.username = username;
        this.idDiscount = idDiscount;
        this.discountCode = discountCode;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the dateOrder
     */
    public String getDateOrder() {
        return dateOrder;
    }

    /**
     * @param dateOrder the dateOrder to set
     */
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the idDiscount
     */
    public int getIdDiscount() {
        return idDiscount;
    }

    /**
     * @param idDiscount the idDiscount to set
     */
    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public int compareTo(ViewOrderDTO t) {
        if (t.getId() > this.id) {
            return 1;
        } else if (t.getId() == this.id) {
            return 0;
        }
        return -1;
    }

}
