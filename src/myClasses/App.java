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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Angelina
 */
public class App {

    private final Scanner scanner = new Scanner(System.in);
    //---------------Данные магазина обуви-------------
    private List<Item> items = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private List<Producer> producers = new ArrayList<>();
    //---------------Сохранение--------------------
//    private final Keeping keeping = new Keeper();
    private final Keeping keeping = new KeeperToBase();

    public App() {
        items = keeping.loadItems();
        producers = keeping.loadProducers();
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
            System.out.println("4: Список покупателей");
            System.out.println("5: Добавить денег покупателю");
            System.out.println("6: Добавить производителя");
            System.out.println("7: Список производителей");
            System.out.println("8: Покупка обуви");
            System.out.println("9: Доход магазина за все время работы");
            System.out.println("10: Доход магазина за указанный месяц");
            System.out.println("11: Редактировать товар");
            System.out.println("12: Редактировать покупателя");

            int task = scanner.nextInt();
            scanner.nextLine();

            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    addItem();
                    break;
                case 2:
                    printListItems();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    printListCustomers();
                    break;
                case 5:
                    changeBalance();
                    break;
                case 6:
                    addProducer();
                    break;
                case 7:
                    printListProducers();
                    break;
                case 8:
                    addHistory();
                    break;
                case 9:
                    printTotalIncome();
                    break;
                case 10:
                    printMonthlyIncome();
                    break;
                case 11:
                    changeItem();
                    break;
                case 12:
                    changeCustomer();
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");
            }

        } while ("r".equals(repeat));

    }

    private void addItem() {

        System.out.println("--- Добавление модели ---");

        if (isQuit()) {
            return;
        }

        Item item = new Item();

        Set<Integer> setNumbersProducers = printListProducers();
        if (setNumbersProducers.isEmpty()) {
            return;
        }
        System.out.println("Если есть фирма-производитель обуви в списке, выберите 1, если нет, выберите 2:");
        if (getNumber() != 1) {
            System.out.println("Добавьте фирму-производителя!");
            return;
        }

        System.out.println("Введите номер фирмы-производителя ");
        int numberProducer = insertNumber(setNumbersProducers);
        item.setProducers(producers.get(numberProducer - 1));

        System.out.print("Введите тип обуви: ");
        item.setItemName(scanner.nextLine());

        System.out.print("Введите цвет обуви: ");
        item.setColor(scanner.nextLine());

        System.out.print("Введите размер обуви: ");
        item.setItemSize(getNumber());

        System.out.print("Введите цену обуви в центах: ");
        item.setItemCost(getNumber());

        System.out.print("Введите количество экземпляров обуви: ");
        item.setQuantity(getNumber());
        item.setCount(item.getQuantity());

        System.out.println("-----------------");
        items.add(item);
        keeping.saveItems(items);

    }

    private void addCustomer() {
        if (isQuit()) {
            return;
        }

        System.out.println("--- Добавление покупателя ---");

        Customer customer = new Customer();

        System.out.print("Введите имя покупателя: ");
        customer.setFirstName(scanner.nextLine());

        System.out.print("Введите фамилию покупателя: ");
        customer.setLastName(scanner.nextLine());

        System.out.print("Введите телефон покупателя: ");
        customer.setTelephone(scanner.nextLine());

        System.out.print("Введите баланс покупателя (в центах): ");
        customer.setBalance(getNumber());

        System.out.println("-------------------");
        customers.add(customer);
        keeping.saveCustomers(customers);
    }

    private void addHistory() {
        System.out.println("--- Покупка покупателем обуви ---");
        if (isQuit()) {
            return;
        }
        System.out.println("Список моделей:");
        History history = new History();

        System.out.println("Выберите номер модели обуви: ");
        Set<Integer> setNumbersItems = printListItems();
        if (setNumbersItems.isEmpty()) {
            return;
        }
        int numberItem = insertNumber(setNumbersItems);

        Set<Integer> setNumbersCustomers = printListCustomers();
        if (setNumbersCustomers.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        if (customers.get(numberCustomer - 1).getBalance() >= items.get(numberItem - 1).getItemCost()) {
            history.setItem(items.get(numberItem - 1));
            history.setCustomer(customers.get(numberCustomer - 1));
            Calendar c = new GregorianCalendar();
            history.setSoldDate(c.getTime());

            history.getItem().setCount(history.getItem().getCount() - 1);
            history.getCustomer().setBalance(history.getCustomer().getBalance() - history.getItem().getItemCost());
            keeping.saveItems(items);
            keeping.saveCustomers(customers);
            histories.add(history);
            keeping.saveHistories(histories);
            System.out.println("Модель " + history.getItem().getItemName()
                    + " заказана " + history.getCustomer().getFirstName()
                    + " " + history.getCustomer().getLastName()
                    + " " + history.getSoldDate()
            );
        } else {
            System.out.println("У данного покупателя недостаточно денег на счёте!");
        }
        System.out.println("-----------------------");
    }

    private boolean isQuit() {
        System.out.println("Чтобы закончить задачу нажми \"q\"");
        String q = scanner.nextLine();
        return ("q".equals(q));
    }

    private int getNumber() {
        int number;
        do {
            String strNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(strNumber);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Попробуй еще раз: ");
            }
        } while (true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number;
        do {
            number = getNumber();
            if (setNumbers.contains(number)) {
                return number;
            }
            System.out.println("Попробуй еще: ");
        } while (true);
    }

    private Set<Integer> printListProducers() {
        Set<Integer> numbersProducers = new HashSet<>();
        if (producers.isEmpty()) {
            System.out.println("Список фирм-производителей пуст, добавьте их.");
            return new HashSet<>();
        } else {
            System.out.println("Список фирм-производителей:");
        }
        for (int i = 0; i < producers.size(); i++) {
            System.out.printf("%d. %s - %s%n",
                    i + 1,
                    producers.get(i).getProducerName(),
                    producers.get(i).getProducerCountry()
            );
            numbersProducers.add(i + 1);
        }
        return numbersProducers;
    }

    private Set<Integer> printListItems() {
        System.out.println("--- Список моделей ---");
        Set<Integer> setNumbersItems = new HashSet<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getCount() > 0) {
                System.out.printf("%d. %s. Цвет: %s. Размер: %d. Производитель: %s. Цена: %d. В наличии экземпляров: %d%n",
                        i + 1,
                        items.get(i).getItemName(),
                        items.get(i).getColor(),
                        items.get(i).getItemSize(),
                        items.get(i).getProducers(),
                        items.get(i).getItemCost(),
                        items.get(i).getCount()
                );
                setNumbersItems.add(i + 1);
            } else if (items.get(i) != null && items.get(i).getQuantity() > 0) {
                System.out.printf("%d. (Распродано) %s. Цвет: %s. Размер: %d. Производитель: %s. Цена: %d. В наличии экземпляров: %d%n",
                        i + 1,
                        items.get(i).getItemName(),
                        items.get(i).getColor(),
                        items.get(i).getItemSize(),
                        items.get(i).getProducers(),
                        items.get(i).getItemCost(),
                        items.get(i).getCount()
                );
            }
        }
        if (setNumbersItems.isEmpty()) {
            System.out.println("Нет обуви в магазине!");
            System.out.println("------------------------");
        }
        return setNumbersItems;
    }

    private Set<Integer> printListAllItems() {
        System.out.println("--- Список моделей ---");
        Set<Integer> setNumbersItems = new HashSet<>();
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s. Цвет: %s. Размер: %d. Цена: %d. Всего пар: %d. В наличии пар: %d. Производитель: %s.%n",
                    i + 1,
                    items.get(i).getItemName(),
                    items.get(i).getColor(),
                    items.get(i).getItemSize(),
                    items.get(i).getItemCost(),
                    items.get(i).getQuantity(),
                    items.get(i).getCount(),
                    items.get(i).getProducers()
            );
            setNumbersItems.add(i + 1);
        }
        if (setNumbersItems.isEmpty()) {
            System.out.println("Нет обуви в магазине!");
            System.out.println("------------------------");
        }
        return setNumbersItems;
    }

    private Set<Integer> printListCustomers() {
        System.out.println("--- Список зарегистрированных покупателей ---");
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i) != null) {
                System.out.printf("%d. %s %s. Телефон: %s. Баланс: %d%n",
                        i + 1,
                        customers.get(i).getFirstName(),
                        customers.get(i).getLastName(),
                        customers.get(i).getTelephone(),
                        customers.get(i).getBalance()
                );
                setNumbersCustomers.add(i + 1);
            }
        }
        if (setNumbersCustomers.isEmpty()) {
            System.out.println("Нет покупателей!");
            System.out.println("------------------------");
        }
        return setNumbersCustomers;
    }

    private void printTotalIncome() {
        System.out.println("--- Доход магазина за все время работы ---");
        int sum = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null) {
                sum += histories.get(i).getItem().getItemCost();
            }
        }

        System.out.println("Доход магазина за все время работы: " + sum + " центов.");
        System.out.println("------------------");
    }

    private void changeBalance() {
        System.out.println("--- Добавить денег покупателю ---");

        if (isQuit()) {
            return;
        }

        Set<Integer> setNumbersCustomers = printListCustomers();
        if (setNumbersCustomers.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);

        System.out.print("Введите сумму (в центах), которую необходмо добавить: ");
        int addedSum = getNumber();

        customers.get(numberCustomer - 1).setBalance(customers.get(numberCustomer - 1).getBalance() + addedSum);
        keeping.saveCustomers(customers);

        System.out.println("------------------");
    }

    private void addProducer() {
        System.out.println("---- Добавить фирму-производителя -----");
        if (isQuit()) {
            return;
        }
        Producer producer = new Producer();
        System.out.println("Название фирмы-производителя: ");
        producer.setProducerName(scanner.nextLine());
        System.out.println("Страна фирмы-производителя: ");
        producer.setProducerCountry(scanner.nextLine());
        producers.add(producer);
        keeping.saveProducers(producers);
    }

    private void changeCustomer() {
        System.out.println("----- Изменение данных покупателя ------");
        if (isQuit()) {
            return;
        }
        /**
         * 1. Выводим список покупателей 2. Выбрать номер покупателя 3. Показать
         * содержимое поля покупателя 4. Спросить у пользователя, нужно ли
         * менять поле. 5. Ввести новое значение поля. ----- Повторить для
         * других полей покупателя. Сохранить список существующих покупателей
         */
        Set<Integer> changeNumbers = new HashSet<>();
        changeNumbers.add(1);
        changeNumbers.add(2);

        Set<Integer> setNumbersCustomers = printListCustomers();
        if (setNumbersCustomers.isEmpty()) {
            return;
        }

        System.out.println("Введите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);

        System.out.println("Имя покупателя: " + customers.get(numberCustomer - 1).getFirstName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        int change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новое имя покупателя: ");
            customers.get(numberCustomer - 1).setFirstName(scanner.nextLine());
        }

        System.out.println("Фамилия покупателя: " + customers.get(numberCustomer - 1).getLastName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новую фамилию покупателя: ");
            customers.get(numberCustomer - 1).setLastName(scanner.nextLine());
        }

        System.out.println("Телефон покупателя: " + customers.get(numberCustomer - 1).getTelephone());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новый телефон покупателя: ");
            customers.get(numberCustomer - 1).setTelephone(scanner.nextLine());
        }

        System.out.println("Баланс покупателя: " + customers.get(numberCustomer - 1).getBalance());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новый баланс покупателя: ");
            customers.get(numberCustomer - 1).setBalance(getNumber());
        }

        keeping.saveCustomers(customers);
    }

    private void changeItem() {
        System.out.println("----- Изменение данных модели обуви  ------");
        if (isQuit()) {
            return;
        }
        /**
         * 1. Выводим список моделей обуви 2. Выбрать номер модели 3. Показать
         * содержимое поля модели 4. Спросить у пользователя, нужно ли менять
         * поле. 5. Ввести новое значение поля. ----- Повторить для других полей
         * модели. Сохранить список существующих моделей
         */
        Set<Integer> changeNumbers = new HashSet<>();
        changeNumbers.add(1);
        changeNumbers.add(2);

        Set<Integer> setNumbersItems = printListAllItems();
        if (setNumbersItems.isEmpty()) {
            return;
        }

        System.out.println("Введите номер модели: ");
        int numberItem = insertNumber(setNumbersItems);

        System.out.println("Название модели: " + items.get(numberItem - 1).getItemName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        int change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новое название модели: ");
            items.get(numberItem - 1).setItemName(scanner.nextLine());
        }

        System.out.println("Цвет модели: " + items.get(numberItem - 1).getColor());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новый цвет: ");
            items.get(numberItem - 1).setColor(scanner.nextLine());
        }

        System.out.println("Размер модели: " + items.get(numberItem - 1).getItemSize());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новый размер модели: ");
            items.get(numberItem - 1).setItemSize(getNumber());
        }

        // Изменение фирмы:
        System.out.println("Название фирмы: " + items.get(numberItem - 1).getProducers().getProducerName()
                + items.get(numberItem - 1).getProducers().getProducerCountry());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новую фирму-производителя: ");
            items.get(numberItem - 1).getProducers().setProducerName(scanner.nextLine());
        }

        System.out.println("Страна производства: " + items.get(numberItem - 1).getProducers().getProducerCountry());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новую страну производства: ");
            items.get(numberItem - 1).getProducers().setProducerCountry(scanner.nextLine());
        }

        System.out.println("Цена модели (в центах): " + items.get(numberItem - 1).getItemCost());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новую цену модели (в центах): ");
            items.get(numberItem - 1).setItemCost(getNumber());
        }

        System.out.println("Количество пар обуви: " + items.get(numberItem - 1).getQuantity());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новое количество пар обуви: ");
            int oldQuantity = items.get(numberItem - 1).getQuantity();
            int oldCount = items.get(numberItem - 1).getCount();
            int newQuantity;
            do {
                newQuantity = getNumber();
                if (newQuantity >= oldQuantity - oldCount) {
                    break;
                }
                System.out.println("Пар должно быть больше. Введите новое количество пар обуви: ");
            } while (true);
            int newCount = oldCount + (newQuantity - oldQuantity);

            items.get(numberItem - 1).setQuantity(newQuantity);
            items.get(numberItem - 1).setCount(newCount);
        }

        keeping.saveItems(items);
    }

    private void printMonthlyIncome() {
        System.out.println("--- Доход магазина за месяц ---");

        if (isQuit()) {
            return;
        }

        System.out.println("--- Введите месяц (от 1 до 12) ---");
        Set<Integer> setChosenMonth = new HashSet<>();
        for (int i = 1; i < 13; i++) {
            setChosenMonth.add(i);
        }

        int chosenMonth = insertNumber(setChosenMonth);

        System.out.println("--- Введите год (например, 2021) ---");
        int chosenYear = getNumber();

        int sum = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null) {
                if (histories.get(i).getSoldDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == chosenMonth) {
                    if (histories.get(i).getSoldDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == chosenYear) {
                        sum += histories.get(i).getItem().getItemCost();
                    }
                }
            }
        }
        System.out.println("Доход магазина за " + chosenMonth + "." + chosenYear + ": " + sum + " центов.");
        System.out.println("------------------");
    }
}
