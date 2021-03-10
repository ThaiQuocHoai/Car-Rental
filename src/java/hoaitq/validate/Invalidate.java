/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.validate;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 *
 * @author QH
 */
public class Invalidate implements Serializable {

    private static final String PATTERM = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    private static final String FULLNAME = "^[a-zA-z ]+$";

    public static boolean checkEmail(String email) {
        return Pattern.matches(PATTERM, email);
    }

    public static boolean checkFullname(String fullname) {
        if (Pattern.matches(FULLNAME, fullname)) {
            return true;
        }
        return false;
    }

    public static boolean checkConfirmNotMatch(String confirm, String password) {
        if (!confirm.equals(password)) {
            return false;
        }
        return true;
    }
    
    public static boolean checkPhoneNum(String phone){
        if(!phone.matches("^[\\d]{11}$"))
            return true;
        return false;
    }
}
