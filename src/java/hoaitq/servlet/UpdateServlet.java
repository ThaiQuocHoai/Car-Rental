/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.car.CarDTO;
import hoaitq.cart.CartDTO;
import java.io.IOException;
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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String CART = "viewCart.jsp";

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
        String url = CART;
        List<CarDTO> listCar = (List<CarDTO>) session.getAttribute("LISTCAR");
        List<CartDTO> listCart = (List<CartDTO>) session.getAttribute("LISTCART");

        String id = request.getParameter("uId");
        String quantity = request.getParameter("txtValue").trim();
        String returnDate = request.getParameter("uReturn");
        String rentalDate = request.getParameter("uRental");
        try {
            for (CarDTO carDTO : listCar) {
                if (carDTO.getId() == Integer.parseInt(id)) {
                    for (CartDTO cartDTO : listCart) {
                        if (cartDTO.getId() == Integer.parseInt(id) && cartDTO.getRentalDate().equals(rentalDate) && cartDTO.getReturnDate().equals(returnDate)) {
                            if (Integer.parseInt(quantity) > carDTO.getQuantity()) {
                                request.setAttribute("QUANERROR", "Quantity of " + cartDTO.getCarName() + " must less than " + carDTO.getQuantity());
                            } else {
                                cartDTO.setQuantity(Integer.parseInt(quantity));
                            }
                        }
                    }
                }
            }

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
