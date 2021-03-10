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
public class OrderDetailDTO implements Serializable{
    private String rental;
    private String back;
    private int idCar;
    private int idInvoice;
    private String carName;
    private String color;
    private int year;
    private float price;
    private int quantity;
    private int status;
    private float rate;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String rental, String back, int idCar, int idInvoice, String carName, String color, int year, float price, int quantity, int status, float rate) {
        this.rental = rental;
        this.back = back;
        this.idCar = idCar;
        this.idInvoice = idInvoice;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.rate = rate;
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
     * @return the idCar
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * @param idCar the idCar to set
     */
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    /**
     * @return the idInvoice
     */
    public int getIdInvoice() {
        return idInvoice;
    }

    /**
     * @param idInvoice the idInvoice to set
     */
    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
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

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the rate
     */
    public float getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(float rate) {
        this.rate = rate;
    }
    
    
}
