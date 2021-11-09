/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Customer;
import entity.History;
import entity.Item;
import java.util.List;

/**
 *
 * @author Angelina
 */
public interface Keeping {
    public void saveItems(List<Item> items);
    public List<Item> loadItems();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();

}
