/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.user.UserDAO;
import hoaitq.user.UserErrorDTO;
import hoaitq.validate.Invalidate;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QH
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private final String FAIL = "signup.jsp";
    private final String SUCCESS = "otp.jsp";

    private final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHAUPPER = ALPHA.toUpperCase();
    private final String DIGIT = "0123456789";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String address = request.getParameter("txtAddress");
        String phone = request.getParameter("txtPhone");

        try {
            UserErrorDTO error = new UserErrorDTO();
            int flag = 0;
            if (username.trim().isEmpty()) {
                flag = 1;
                error.setUsernameError("Username cannot be empty");
            } else {
                if (!Invalidate.checkEmail(username)) {
                    error.setUsernameError("Username must be an Email");
                    flag = 1;
                }
            }
            if (password.trim().isEmpty()) {
                flag = 1;
                error.setPasswordError("Password cannot be empty");
            } else {
                if (confirm.trim().isEmpty()) {
                    flag = 1;
                    error.setConfirmPasswordError("Confirm cannot be empty");
                } else {
                    if (!Invalidate.checkConfirmNotMatch(confirm, password)) {
                        flag = 1;
                        error.setConfirmPasswordError("Confirm password not matched");
                    }
                }
            }
            if (fullname.trim().isEmpty()) {
                flag = 1;
                error.setFullnameError("Fullname cannot be empty");
            } else {
                if (!Invalidate.checkFullname(fullname)) {
                    error.setFullnameError("Fullname must allow A - Z");
                    flag = 1;
                }
            }
            if (address.trim().isEmpty()) {
                flag = 1;
                error.setAddressError("Address cannot be empty");
            }
            if (phone.trim().isEmpty()) {
                flag = 1;
                error.setPhoneError("Phone cannot be empty");
            } else {
                if (!Invalidate.checkPhoneNum(phone)) {
                    flag = 1;
                    error.setPhoneError("Phone length is 10 and not contain characters");
                }
            }
            if (flag == 1) {
                request.setAttribute("REGISTER_FAIL", error);
            } else {
                Random random = new Random();
                String code = "";
                String Alpha_ALL = ALPHA + ALPHAUPPER + DIGIT;
                for (int i = 0; i < 6; i++) {
                    int number = random.nextInt(Alpha_ALL.length() - 1);
                    code += Alpha_ALL.charAt(number);
                }
                UserDAO dao = new UserDAO();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String now = dateFormat.format(new Date());
                int rs = dao.createUser(username, password, fullname, address, phone, now, code);
                if (rs > 0) {
                    url = SUCCESS;
                    Properties mailServerProperties;
                    Session getMailSession;
                    MimeMessage mailMessage;
                    HttpSession session = request.getSession();
                    session.setAttribute("EMAIL", username);

                    //set up mail server
                    mailServerProperties = System.getProperties();
                    mailServerProperties.put("mail.smtp.port", "587");
                    mailServerProperties.put("mail.smtp.auth", "true");
                    mailServerProperties.put("mail.smtp.starttls.enable", "true");

                    //get mail session
                    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
                    mailMessage = new MimeMessage(getMailSession);

                    mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

                    mailMessage.setSubject("[Verify your accout from Rental car]");
                    mailMessage.setText("Hello guys,"
                            + "Welcome to Rental car"
                            + "This is your verify code: " + code);
                    //send mail
                    Transport transport = getMailSession.getTransport("smtp");
                    transport.connect("smtp.gmail.com", "hoaithaiq2912@gmail.com", "Hoaiquoc1");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                }
            }

        } catch (MessagingException ex) {
            log("SignupServlet_MessagingException: " + ex.getMessage());
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate")) {
                UserErrorDTO error = new UserErrorDTO();
                error.setUsernameError("Username has existed, please try another name");
                request.setAttribute("REGISTER_FAIL", error);
            } else {
                log("SignUpServlet_SQLException: " + ex.getMessage());
            }
        } catch (NamingException ex) {
            log("SignUpServlet_NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
