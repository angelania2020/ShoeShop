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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angelina
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeping = new Keeper();
    
    public App() {
        items = keeping.loadItems();
        customers = keeping.loadCustomers();
        histories = keeping.loadHistories();
    }
    
    public void run() {
        String repeat = "r";
        do {
            System.out.println("Выберите номер задачи:");
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить модель");
            System.out.println("2: Список продаваемых моделей");
            System.out.println("3: Добавить покупателя");
            System.out.println("4: Список зарегистрированных покупателей");
            System.out.println("5: Покупка покупателем обуви");
            System.out.println("6: Доход магазина за все время работы");
            System.out.println("7: Добавить денег покупателю");
            
            int task = scanner.nextInt(); scanner.nextLine();
            
            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    System.out.println("--- Добавление модели ---");
                    items.add(addItem());
                    keeping.saveItems(items);
                    break;
                case 2:
                    System.out.println("--- Список продаваемых моделей ---");
                    for (int i=0; i < items.size(); i++){
                        if(items.get(i) != null) {
                            System.out.println(items.get(i).toString());
                        }
                    }      
                    System.out.println("------------------");
                    break;
                case 3:
                    System.out.println("--- Добавление покупателя ---");
                    customers.add(addCustomer());
                    keeping.saveCustomers(customers);
                    break;
                case 4:
                    System.out.println("--- Список зарегистрированных покупателей ---");
                    for (int i=0; i < customers.size(); i++){
                        if(customers.get(i) != null) {
                            System.out.println(customers.get(i).toString());
                        }
                    }      
                    System.out.println("------------------");
                    break; 
                case 5:
                    addHistory();
                    break;
                    
                case 6:
                    System.out.println("--- Доход магазина за все время работы ---");  
                    int sum=0;
                    int n=0;
                    for (int i=0; i < histories.size(); i++){
                        if(histories.get(i) != null) { 
                            sum += histories.get(i).getItem().getItemCost();
                            n++;
                        }
                    }

                    System.out.println("Доход магазина за все время работы: " + sum + " центов.");
                    System.out.println("------------------");
                    break; 
                    
                case 7:
                    System.out.println("--- Добавить денег покупателю ---");  
                    System.out.println("Список покупателей:");
                    for (int i = 0; i < customers.size(); i++) {
                        if(customers.get(i) != null){
                            System.out.println(i+1+". "+customers.get(i).toString());
                        }
                    }
        
                    System.out.print("Выберите номер покупателя: ");
                    int nrCustomer = scanner.nextInt(); scanner.nextLine();
                    
                    System.out.print("Введите сумму (в центах), которую необходмо добавить: ");
                    int addedSum = scanner.nextInt(); scanner.nextLine();
                    
                    customers.get(nrCustomer-1).setBalance(customers.get(nrCustomer-1).getBalance()+addedSum);
                    keeping.saveCustomers(customers);
                    
                    System.out.println("------------------");
                    break; 
                    
                    
                default:
                    System.out.println("Выберите цифру из списка!");
            }
            
        }while("r".equals(repeat));
    
    }
    
    private Item addItem(){
        Item item = new Item();
        
        System.out.print("Введите тип обуви: ");
        item.setItemName(scanner.nextLine());
        
        System.out.print("Введите цвет обуви: ");
        item.setColor(scanner.nextLine());
        
        System.out.print("Введите размер обуви: ");
        item.setSize(scanner.nextInt());scanner.nextLine();
        
        System.out.println("Введите название фирмы-производителя обуви: ");
        Producer producers = new Producer();
        
        //System.out.println("Введите название фирмы: ");
        producers.setProducerName(scanner.nextLine());
        System.out.println("Введите страну производителя: ");
        producers.setProducerCountry(scanner.nextLine());
        item.setProducers(producers);
        
        System.out.print("Введите цену обуви в центах: ");
        item.setItemCost(scanner.nextInt());scanner.nextLine();
        
        System.out.print("Введите количество экземпляров обуви: ");
        item.setQuantity(scanner.nextInt());scanner.nextLine();
        item.setCount(item.getQuantity());
        
        return item;
    }
    
    private Customer addCustomer(){
        Customer customer = new Customer();
        
        System.out.print("Введите имя покупателя: ");
        customer.setFirstName(scanner.nextLine());
        
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastName(scanner.nextLine());
        
        System.out.print("Введите телефон покупателя: ");
        customer.setTelephone(scanner.nextLine());
        
        System.out.print("Введите баланс покупателя (в центах): ");
        customer.setBalance(scanner.nextInt());scanner.nextLine();
        
        return customer;
    }
    
        private void addHistory() {
        System.out.println("--- Покупка покупателем обуви ---");
        System.out.println("Список моделей:");
        History history = new History();
        int n = 0;
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) != null && items.get(i).getCount()>0){
                
                System.out.println(i+1
                        +". "+items.get(i).getItemName()
                        +". "+items.get(i).getColor()
                        +". "+items.get(i).getSize()
                        +". "+items.get(i).getItemCost()
                        +". "+items.get(i).getProducers().getProducerName()
                        +". "+items.get(i).getProducers().getProducerName()
                        +". В наличии: " + items.get(i).getCount()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет обуви в магазине");
            return;
        }
        System.out.print("Выберите номер модели: ");
        int numberItem = scanner.nextInt(); scanner.nextLine();
        while (true) {
        System.out.println("");
        System.out.println("Список покупателей:");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.println(i+1+". "+customers.get(i).toString());
            }
        }
        
        System.out.print("Выберите номер покупателя: ");
        
        int numberCustomer = scanner.nextInt(); scanner.nextLine();
        if (customers.get(numberCustomer-1).getBalance() >= items.get(numberItem-1).getItemCost()) {
                
        history.setItem(items.get(numberItem-1));
        history.setCustomer(customers.get(numberCustomer-1));
        Calendar c = new GregorianCalendar();
        history.setSoldDate(c.getTime());
        history.getItem().setCount(history.getItem().getCount() - 1);
        history.getCustomer().setBalance(history.getCustomer().getBalance() - history.getItem().getItemCost());
        keeping.saveItems(items);
        keeping.saveCustomers(customers);
        
        
        histories.add(history);
        keeping.saveHistories(histories);
        System.out.println("Модель "+history.getItem().getItemName()
                            +" заказана "+history.getCustomer().getFirstName()
                            +" "+history.getCustomer().getLastName()
                            +" "+history.getSoldDate()
        
        );
        break;
        } else System.out.println("У данного покупателя недостаточно денег на счёте! Выберете другого:");
        }
        System.out.println("-------------------");
        
    }
    
}
