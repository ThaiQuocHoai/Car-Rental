/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QH
 */
public class CartObject implements Serializable {

    List<CartDTO> cart = null;

    public List<CartDTO> getCart() {
        return cart;
    }

    public void addToCart(int id, String carName, String image, String color, int year, float price, int quantity, float rate, String rentalDate, String returnDate) {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        int flag = 1;
        if (cart.size() > 0) {
            for (CartDTO cartDTO : cart) {
                if (cartDTO.getId() == id && cartDTO.getRentalDate().equals(rentalDate) && cartDTO.getReturnDate().equals(returnDate)) {
                    cartDTO.setQuantity(cartDTO.getQuantity() + quantity);
                    flag = 0;
                    break;
                }
            }

        } else {
            CartDTO cartDTO = new CartDTO(id, carName, image, color, year, price, quantity, rate, rentalDate, returnDate);
            cart.add(cartDTO);
            flag =2;
        }
        if (flag == 1 && cart.size()>0) {
            CartDTO cartDTO = new CartDTO(id, carName, image, color, year, price, quantity, rate, rentalDate, returnDate);
            cart.add(cartDTO);
        }
    }

    public void removeFromCart(int id, String rentalDate, String returnDate) {
        if (cart == null) {
            return;
        }
        for (int i=0; i<cart.size();i++) {
            if (cart.get(i).getId() == id && cart.get(i).getRentalDate().equals(rentalDate) && cart.get(i).getReturnDate().equals(returnDate)) {
                cart.remove(cart.get(i));
            }
        }
    }

}
