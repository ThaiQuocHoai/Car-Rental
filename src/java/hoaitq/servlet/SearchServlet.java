/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.servlet;

import hoaitq.car.CarDAO;
import hoaitq.car.CarDTO;
import hoaitq.cart.CartDTO;
import hoaitq.order.OrderDAO;
import hoaitq.order.OrderDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String CAR = "car.jsp";

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
        String url = CAR;
        HttpSession session = request.getSession();
        String select = request.getParameter("rdo1");
        String cate = "";

        String name = "";
        int quantity = 1;
        if (request.getParameter("txtQuantity") != null) {
            quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        }
        int index = 1;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        String rental = "";
        String back = "";
        if (request.getParameter("txtRentalDate") != null) {
            rental = request.getParameter("txtRentalDate");
        } else {
            rental = (String) session.getAttribute("RENTALDATE");
        }
        if (request.getParameter("txtReturnDate") != null) {
            back = request.getParameter("txtReturnDate");
        } else {
            back = (String) session.getAttribute("RETURNDATE");
        }

        LocalDate rentalDate = LocalDate.parse(rental, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate returnDate = LocalDate.parse(back, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String now = dateFormat.format(new Date());
            LocalDate timeNow = LocalDate.parse(now, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            CarDAO carDAO = new CarDAO();
            OrderDAO orderDAO = new OrderDAO();
            if (select == null) {
                select = (String) session.getAttribute("SELECT");
            }
            if (select.equals("1")) {
                if (request.getParameter("dropCate") != null) {
                    cate = request.getParameter("dropCate");
                } else if (session.getAttribute("CATECHOOSER") != null) {
                    cate = (String) session.getAttribute("CATECHOOSER");
                }
            } else {
                if (request.getParameter("txtName") != null) {
                    name = request.getParameter("txtName");
                } else if (session.getAttribute("SEARCHNAME") != null) {
                    name = (String) session.getAttribute("SEARCHNAME");
                }
            }
            if (rentalDate.compareTo(timeNow) < 0) {
                request.setAttribute("RENTALERROR", "Rental date must greater than now date");
            } else if (rentalDate.compareTo(returnDate) >= 0) {
                request.setAttribute("RENTALERROR", "Rental date must less than return date");
            } else {
                ArrayList<CarDTO> listCar = carDAO.getCar(cate, name, index - 1);
                int count = carDAO.countCar(cate, name);
                ArrayList<OrderDTO> listOrder = orderDAO.getOrder(cate, name);
                if (listOrder != null) {
                    for (OrderDTO orderDTO : listOrder) {
                        LocalDate dRentOrder = LocalDate.parse(orderDTO.getRental(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate dReturnOrder = LocalDate.parse(orderDTO.getBack(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        if (rentalDate.compareTo(dRentOrder) >= 0 && rentalDate.compareTo(dReturnOrder) <= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == orderDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - orderDTO.getQuantity());
                                }
                            }
                        } else if (returnDate.compareTo(dRentOrder) >= 0 && returnDate.compareTo(dReturnOrder) <= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == orderDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - orderDTO.getQuantity());
                                }
                            }
                        } else if (rentalDate.compareTo(dRentOrder) <= 0 && returnDate.compareTo(dReturnOrder) >= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == orderDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - orderDTO.getQuantity());
                                }
                            }
                        }
                    }
                }
                List<CartDTO> listCart = (List<CartDTO>) session.getAttribute("LISTCART");
                if (listCart != null) {
                    for (CartDTO cartDTO : listCart) {
                        LocalDate cRentOrder = LocalDate.parse(cartDTO.getRentalDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate cBackOrder = LocalDate.parse(cartDTO.getReturnDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        if (rentalDate.compareTo(cRentOrder) >= 0 && rentalDate.compareTo(cBackOrder) <= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == cartDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - cartDTO.getQuantity());
                                }
                            }
                        } else if (returnDate.compareTo(cRentOrder) >= 0 && returnDate.compareTo(cBackOrder) <= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == cartDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - cartDTO.getQuantity());
                                }
                            }
                        } else if (rentalDate.compareTo(cRentOrder) <= 0 && returnDate.compareTo(cBackOrder) >= 0) {
                            for (CarDTO carDTO : listCar) {
                                if (carDTO.getId() == cartDTO.getId()) {
                                    carDTO.setQuantity(carDTO.getQuantity() - cartDTO.getQuantity());
                                }
                            }
                        }
                    }
                }
                ArrayList<CarDTO> fakeList = new ArrayList<>();
                for (CarDTO carDTO : listCar) {
                    if (carDTO.getQuantity() >= quantity) {
                        fakeList.add(carDTO);
                    }
                }
                int endPage = count / 12;
                if (count % 12 != 0) {
                    endPage++;
                }
                session.setAttribute("INDEX", String.valueOf(index));
                session.setAttribute("ENDPAGE", String.valueOf(endPage));
                session.setAttribute("LISTCAR", fakeList);
                session.setAttribute("CATECHOOSER", cate);
                session.setAttribute("SEARCHNAME", name);
                session.setAttribute("SELECT", select);
                session.setAttribute("RENTALDATE", rental);
                session.setAttribute("RETURNDATE", back);
                session.setAttribute("FLAG", "asdasdasd");
                session.setAttribute("QUANTITY", String.valueOf(quantity));
            }
        } catch (SQLException ex) {
            log("SearchServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_NamingException: " + ex.getMessage());
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
