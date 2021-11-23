/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import entity.Customer;
import entity.History;
import entity.Item;
import entity.Producer;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Angelina
 */
public class KeeperToBase implements Keeping {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoeShopPU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    @Override
    public void saveItems(List<Item> items) {
        tx.begin();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == null) {
                em.persist(items.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public void saveProducers(List<Producer> producers) {
        tx.begin();
        for (int i = 0; i < producers.size(); i++) {
            if (producers.get(i).getId() == null) {
                em.persist(producers.get(i));
            }
        }
        tx.commit();

    }

    @Override
    public List<Item> loadItems() {
        if (em.createQuery("SELECT i FROM Item i").getResultList().isEmpty()) {
            System.out.println("Таблица item пуста.");
        }
        try {
            return (List<Item>) em.createQuery("SELECT i FROM Item i").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица item пуста.");
        }
        return new ArrayList<>();  // чтобы не было NullPointerException
    }

    @Override
    public List<Producer> loadProducers() {
        if (em.createQuery("SELECT p FROM Producer p").getResultList().isEmpty()) {
            System.out.println("Таблица producer пуста.");
        }
        try {
            return (List<Producer>) em.createQuery("SELECT p FROM Producer p").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица producer пуста.");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        tx.begin();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == null) {
                em.persist(customers.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Customer> loadCustomers() {
        if (em.createQuery("SELECT c FROM Customer c").getResultList().isEmpty()) {
            System.out.println("Таблица customer пуста.");
        }
        try {
            return (List<Customer>) em.createQuery("SELECT c FROM Customer c").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица customer пуста.");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getId() == null) {
                em.persist(histories.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        if (em.createQuery("SELECT h FROM History h").getResultList().isEmpty()) {
            System.out.println("Таблица history пуста.");
        }
        try {
            return (List<History>) em.createQuery("SELECT h FROM History h").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица history пуста.");
        }
        return new ArrayList<>();
    }

}
