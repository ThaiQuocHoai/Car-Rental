/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoaitq.order;

import java.io.Serializable;

/**
 *
 * @author QH
 */
public class OrderDTO implements Serializable{
    private int id;
    private String name;
    private String rental;
    private String back;
    private int quantity;

    public OrderDTO() {
    }

    public OrderDTO(int id, String name, String rental, String back, int quantity) {
        this.id = id;
        this.name = name;
        this.rental = rental;
        this.back = back;
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rental
     */
    public String getRental() {
        return rental;
    }

    /**
     * @param rental the rental to set
     */
    public void setRental(String rental) {
        this.rental = rental;
    }

    /**
     * @return the back
     */
    public String getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(String back) {
        this.back = back;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
