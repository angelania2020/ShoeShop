/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Angelina
 */
public class Item implements Serializable {
    private String itemName;
    private String color;
    private int size;
    private Producer producers;
    private int itemCost; //в центах (с точностью у double большая проблема), либо Decimal
    private int quantity;
    private int count;
    
    public void Item() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        return "Item{" + "itemName=" + itemName + ", color=" + color + ", size=" + size + ", producers=" + producers + ", itemCost=" + itemCost + ", quantity=" + quantity + ", count=" + count + '}';
    }

    
    
    
}
