/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Angelina
 */
@Entity
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Item item;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date soldDate;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "History{"
                + "customer=" + customer
                + ", item=" + item
                + ", soldDate=" + soldDate
                + '}';
    }

}
