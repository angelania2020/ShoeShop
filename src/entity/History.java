/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Angelina
 */
public class History implements Serializable {
    private Customer customer;
    private Item item;
    private Date soldDate;

    public History() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    @Override
    public String toString() {
        return "History{" + "customer=" + customer + ", item=" + item + ", soldDate=" + soldDate + '}';
    }
    
    
    
}
