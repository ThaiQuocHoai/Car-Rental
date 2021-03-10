/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.cart.CartDTO;
import hoaitq.cart.CartObject;
import hoaitq.discount.DiscountDTO;
import hoaitq.order.OrderDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    private final String FAIL = "viewCart.jsp";
    private final String SUCCESS = "car.jsp";

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

        String url = FAIL;
        String fullname = request.getParameter("cName");
        String username = request.getParameter("cMail");
        String number = request.getParameter("cNum");
        String address = request.getParameter("cAdd");
        String total = request.getParameter("cTotal");
        String rental = (String) session.getAttribute("RENTALDATE");
        String back = (String) session.getAttribute("RETURNDATE");

        try {
            DiscountDTO disDTO = (DiscountDTO) session.getAttribute("DISCOUNT");
            CartObject cartObj = (CartObject) session.getAttribute("CART");
            List<CartDTO> listCart = (List<CartDTO>) session.getAttribute("LISTCART");
            OrderDAO orderDAO = new OrderDAO();
            if (disDTO != null) {
                orderDAO.insertOrder(fullname, number, address, username, disDTO.getId(), String.valueOf(disDTO.getPercent()));
                int id = orderDAO.getIdMax();
                for (CartDTO cartDTO : listCart) {
                    orderDAO.insertOrderDetail(rental, back, cartDTO.getId(), id, cartDTO.getCarName(), cartDTO.getColor(), cartDTO.getYear(), cartDTO.getPrice() * cartDTO.getQuantity(), cartDTO.getQuantity(), 1, 0);
                }
                url = SUCCESS;
                request.setAttribute("SUCCESS", "Bought success!");
            } else {
                orderDAO.insertOrderNoDiscount(fullname, number, address, username);
                int id = orderDAO.getIdMax();
                for (CartDTO cartDTO : listCart) {
                    orderDAO.insertOrderDetail(rental, back, cartDTO.getId(), id, cartDTO.getCarName(), cartDTO.getColor(), cartDTO.getYear(), cartDTO.getPrice() * cartDTO.getQuantity(), cartDTO.getQuantity(), 1, 0);
                }
                url = SUCCESS;
                request.setAttribute("SUCCESS", "Bought success!");
            }
            cartObj = null;
            disDTO = null;
            listCart = null;
            session.setAttribute("DISCOUNT", disDTO);
            session.setAttribute("CART", cartObj);
            session.setAttribute("LISTCART", listCart);
            session.setAttribute("TOTAL", String.valueOf(total));
        } catch (SQLException ex) {
            log("CheckoutServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutServlet_NamingException: " + ex.getMessage());
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
