/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import entity.Customer;
import entity.History;
import entity.Item;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelina
 */
public class Keeper implements Keeping {

    @Override
    public void saveItems(List<Item> items) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream("items");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Item> loadItems() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Item> items = new ArrayList<>();
        try {
            fis = new FileInputStream("items");
            ois = new ObjectInputStream(fis);
            items = (List<Item>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл items еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения items", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return items;
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream("customers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Customer> loadCustomers() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Customer> customers = new ArrayList<>();
        try {
            fis = new FileInputStream("customers");
            ois = new ObjectInputStream(fis);
            customers = (List<Customer>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл customers еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения customers", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return customers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream("histories");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<History> loadHistories() {
                FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<History> histories = new ArrayList<>();
        try {
            fis = new FileInputStream("histories");
            ois = new ObjectInputStream(fis);
            histories = (List<History>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл histories еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения histories", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return histories;
    }
    
}
