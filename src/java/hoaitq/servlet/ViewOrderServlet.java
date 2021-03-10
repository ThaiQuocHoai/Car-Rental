/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.order.OrderDAO;
import hoaitq.order.OrderDetailDTO;
import hoaitq.order.ViewOrderDTO;
import hoaitq.user.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
@WebServlet(name = "ViewOrderServlet", urlPatterns = {"/ViewOrderServlet"})
public class ViewOrderServlet extends HttpServlet {

    private final String VIEWORDER = "viewOrder.jsp";

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
        String page = request.getParameter("cindex");
        String url = VIEWORDER;
        int index = 1;
        if (page != null && !page.isEmpty()) {
            index = Integer.parseInt(page);
        }
        url = VIEWORDER;

        try {
            Map<ViewOrderDTO, ArrayList<OrderDetailDTO>> map = null;
            TreeMap<ViewOrderDTO, ArrayList<OrderDetailDTO>> sorted = null;
            OrderDAO dao = new OrderDAO();
            UserDTO userDTO = (UserDTO) session.getAttribute("USER");
            ArrayList<ViewOrderDTO> listOrder = dao.getOrderAll(index - 1, userDTO.getUsername());
            int count = dao.getOrderAllCount(userDTO.getUsername());
            if (listOrder != null) {
                for (ViewOrderDTO order : listOrder) {
                    ArrayList<OrderDetailDTO> listOrderDetail = dao.getOrderDetail(order.getId());
                    if (listOrderDetail != null) {
                        if (map == null) {
                            map = new HashMap<>();
                        }
                        map.put(order, listOrderDetail);
                    }

                }
            }
            if (map == null) {
                map = new HashMap<>();
            }
            sorted = new TreeMap<>(map);
            int endPage = count / 2;
            if (count % 2 != 0) {
                endPage++;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String now = dateFormat.format(new Date());

            session.setAttribute("MAPORDER", sorted);
            session.setAttribute("CENDPAGE", String.valueOf(endPage));
            session.setAttribute("CINDEX", String.valueOf(index));
            session.setAttribute("SELECTORDERSEARCH", "1");
            session.setAttribute("NOWDATE", now);
            session.setAttribute("VIEWORDER", "asd");
            session.setAttribute("SEARCHORDER", "");
        } catch (SQLException ex) {
            log("ViewOrderServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ViewOrderServlet_NamingException: " + ex.getMessage());
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
