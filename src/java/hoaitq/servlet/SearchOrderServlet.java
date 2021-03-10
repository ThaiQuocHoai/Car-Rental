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
import java.util.ArrayList;
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
@WebServlet(name = "SearchOrderServlet", urlPatterns = {"/SearchOrderServlet"})
public class SearchOrderServlet extends HttpServlet {

    private final String SUCCESS = "viewOrder.jsp";

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

        String url = SUCCESS;
        int index = 1;
        if (request.getParameter("sindex") != null) {
            index = Integer.parseInt(request.getParameter("sindex"));
        }
        String name = "";
        String now = "";

        String select = request.getParameter("rdo");
        if (select == null) {
            select = (String) session.getAttribute("SELECTORDERSEARCH");
        } else if (select.equals("1")) {
            name = request.getParameter("txtSearch");
        } else if (select.equals("2")) {
            now = request.getParameter("txtOrderDate");
        }

        try {
            OrderDAO dao = new OrderDAO();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            Map<ViewOrderDTO, ArrayList<OrderDetailDTO>> map = null;
            TreeMap<ViewOrderDTO, ArrayList<OrderDetailDTO>> sorted = null;
            int count = dao.getCountIdbySearchOrder(name, now, user.getUsername());
            ArrayList<Integer> listId = dao.getIdbySearchOrder(name, now, index - 1, user.getUsername());
            if (listId != null) {
                for (Integer id : listId) {
                    ArrayList<ViewOrderDTO> listOrder = dao.getOrderbySearchOrder(id);
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
                }
            }

            int endpage = count / 2;
            if (count % 2 != 0) {
                endpage++;
            }
            if (map == null) {
                map = new HashMap<>();
            }
            sorted = new TreeMap<>(map);
            if(now.isEmpty()){
                now = (String) session.getAttribute("NOWDATE");
            }
            session.setAttribute("MAPORDER", sorted);
            session.setAttribute("SINDEX", String.valueOf(index));
            session.setAttribute("SENDPAGE", String.valueOf(endpage));
            session.setAttribute("SELECTORDERSEARCH", select);
            session.setAttribute("SEARCHNAMEORDER", name);
            session.setAttribute("NOWDATE", now);
            session.setAttribute("SEARCHORDER", "asd");
            session.setAttribute("VIEWORDER", "");
        } catch (SQLException ex) {
            log("SearchOrderServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchOrderServlet_NamingException: " + ex.getMessage());
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
