/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Angelina
 */
@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String color;
    private int itemSize;
    @OneToOne(cascade = CascadeType.ALL)
    private Producer producers;
    private int itemCost; //в центах (с точностью у double большая проблема), либо Decimal
    private int quantity;
    private int count;

    public void Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public Producer getProducers() {
        return producers;
    }

    public void setProducers(Producer producers) {
        this.producers = producers;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Item{"
                + "itemName=" + itemName
                + ", color=" + color
                + ", itemSize=" + itemSize
                + ", producers=" + producers
                + ", itemCost=" + itemCost
                + ", quantity=" + quantity
                + ", count=" + count
                + '}';
    }

}
