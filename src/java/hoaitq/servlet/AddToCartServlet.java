/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.cart.CartDTO;
import hoaitq.cart.CartObject;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String CAR = "car.jsp";
    private final String RESET = "SearchServlet";

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
        HttpSession session = request.getSession();
        String url = CAR;
        String id = request.getParameter("hdId");
        String name = request.getParameter("hdName");
        String quantity = request.getParameter("hdQuantity");
        String quantityRental = request.getParameter("txtQuantity1");
        String image = request.getParameter("hdImage");
        String color = request.getParameter("hdColor");
        String year = request.getParameter("hdYear");
        String price = request.getParameter("hdPrice");
        String rate = request.getParameter("hdRate");
        
        String cate = (String) session.getAttribute("CATECHOOSER");
        String returnDate = (String) session.getAttribute("RETURNDATE");
        String rentalDate = (String) session.getAttribute("RENTALDATE");

        try {
            CartObject cartObj = (CartObject) session.getAttribute("CART");
            LocalDate rental = LocalDate.parse(rentalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate back = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Duration d = Duration.between(rental.atStartOfDay(), back.atStartOfDay());
            long diff = d.toDays();
            if (cartObj == null) {
                cartObj = new CartObject();
            }

            cartObj.addToCart(Integer.parseInt(id), name, image, color, Integer.parseInt(year), Float.parseFloat(price) * diff, Integer.parseInt(quantityRental), Float.parseFloat(rate), rentalDate, returnDate);
            List<CartDTO> listCart = cartObj.getCart();
            session.setAttribute("CART", cartObj);
            session.setAttribute("LISTCART", listCart);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(RESET);
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
