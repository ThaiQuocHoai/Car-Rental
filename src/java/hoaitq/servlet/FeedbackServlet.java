/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.order.OrderDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author QH
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackServlet"})
public class FeedbackServlet extends HttpServlet {

    private final String SUCCESS = "ViewOrderServlet";
    private final String FAIL = "viewOrder.jsp";

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

        String idCar = request.getParameter("feedbackIdCar");
        String idInvoice = request.getParameter("feedbackId");
        String back = request.getParameter("feedbackReturn");
        String rental = request.getParameter("feedbackRental");
        String rate = request.getParameter("txtRate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());

        try {
            LocalDate returnDate = LocalDate.parse(back, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate nowDate = LocalDate.parse(now, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (nowDate.compareTo(returnDate) <= 0) {
                request.setAttribute("ERRORFEEDBACK", "You cannot feedback after "+back);
            } else {
                OrderDAO dao = new OrderDAO();
                int result = dao.feedbackOrderDetail(rental, back, Integer.parseInt(idInvoice), Integer.parseInt(idCar), Float.parseFloat(rate));
                float feedback = dao.avgRate(Integer.parseInt(idCar));
                dao.updateRate(Integer.parseInt(idCar), feedback);
                url = SUCCESS;
            }

        } catch (SQLException ex) {
            log("CancelServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CancelServlet_NamingException: " + ex.getMessage());
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
