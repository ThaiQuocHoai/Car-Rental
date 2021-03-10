/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.user;

import java.io.Serializable;

/**
 *
 * @author QH
 */
public class UserDTO implements Serializable{
    private String username ;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private String createdate;
    private int role;
    private String status;
    private String validateCode;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullname, String phone, String address, String createdate, int role, String status, String validateCode) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.createdate = createdate;
        this.role = role;
        this.status = status;
        this.validateCode = validateCode;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the createdate
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the validateCode
     */
    public String getValidateCode() {
        return validateCode;
    }

    /**
     * @param validateCode the validateCode to set
     */
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    
    
}
