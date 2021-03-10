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
public class UserErrorDTO implements Serializable{
    private String usernameError ;
    private String passwordError;
    private String fullnameError;
    private String phoneError;
    private String addressError;
    private String createdateError;
    private String validateCodeError;
    private String confirmPasswordError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String usernameError, String passwordError, String fullnameError, String phoneError, String addressError, String createdateError, String validateCodeError, String confirmPasswordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.fullnameError = fullnameError;
        this.phoneError = phoneError;
        this.addressError = addressError;
        this.createdateError = createdateError;
        this.validateCodeError = validateCodeError;
        this.confirmPasswordError = confirmPasswordError;
    }

    /**
     * @return the usernameError
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * @param usernameError the usernameError to set
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * @return the passwordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * @param passwordError the passwordError to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * @return the fullnameError
     */
    public String getFullnameError() {
        return fullnameError;
    }

    /**
     * @param fullnameError the fullnameError to set
     */
    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the addressError
     */
    public String getAddressError() {
        return addressError;
    }

    /**
     * @param addressError the addressError to set
     */
    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    /**
     * @return the createdateError
     */
    public String getCreatedateError() {
        return createdateError;
    }

    /**
     * @param createdateError the createdateError to set
     */
    public void setCreatedateError(String createdateError) {
        this.createdateError = createdateError;
    }

    /**
     * @return the validateCodeError
     */
    public String getValidateCodeError() {
        return validateCodeError;
    }

    /**
     * @param validateCodeError the validateCodeError to set
     */
    public void setValidateCodeError(String validateCodeError) {
        this.validateCodeError = validateCodeError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }
    
}
