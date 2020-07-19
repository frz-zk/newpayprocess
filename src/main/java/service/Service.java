package service;

import DTO.Inventory;
import DTO.Payment;
import DTO.Transaction;
import mythread.Mythread;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    public Service() {

    }

    public static List<Inventory> inventoryList = new ArrayList<>();
    public static List<Transaction> transactionList = new ArrayList<>();
    public static List<Payment> paymentList = new ArrayList<>();

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static String createDataPayment() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            Payment payment = new Payment();
            payment.setIsdebtor(false);
            payment.setAmount(BigDecimal.valueOf(amount));
            total = total.add(payment.getAmount());
            String deposit = "10.100.0." + i;
            payment.setInventoryNumber(deposit);
            result.append(payment.toString()).append("\n");
            paymentList.add(payment);
        }

        Payment debtorpayment = new Payment();
        debtorpayment.setIsdebtor(true);
        debtorpayment.setAmount(total);
        debtorpayment.setInventoryNumber("10.100.0.4");
        result.append(debtorpayment.toString()).append("\n");
        paymentList.add(debtorpayment);
        return result.toString();
    }

    public static String createDatainvantory() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            Inventory inventory = new Inventory();

            inventory.setAmount(BigDecimal.valueOf(amount));
            total = total.add(inventory.getAmount());
            String deposit = "10.100.0." + i;

            inventory.setinventoryNumber(deposit);
            inventoryList.add(inventory);
            result.append(inventory).append("\n");
        }
        Inventory debtorininventory = new Inventory();
        int amount1 = (int) (Math.random() * 10000);
        debtorininventory.setAmount(BigDecimal.valueOf(amount1));
        debtorininventory.setinventoryNumber("10.100.0.4");
        inventoryList.add(debtorininventory);
        result.append(debtorininventory).append("\n");
        return result.toString();
    }

    public static Payment datadebtor() {
        for (Payment payment : paymentList) {
            if (payment.getisDebtor()) {
                return payment;
            }
        }
        return null;
    }

    public static boolean checkAccountInventorydebtor() {
        for (Inventory inventory : inventoryList) {
            if (inventory.getinventoryNumber().equals(Objects.requireNonNull(datadebtor()).getInventoryNumber())) {
                if (inventory.getAmount().compareTo(Objects.requireNonNull(datadebtor()).getAmount()) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String convertTDataTransactionListTostring() {
        StringBuilder result = new StringBuilder();

        for (Transaction transaction : transactionList) {
            result.append(transaction.toString()).append("\n");
        }
        return result.toString();
    }

    public static void finalPayments() {
        Payment debtorpayment = datadebtor();
        assert debtorpayment != null;
        String debtorinventorynumber = debtorpayment.getInventoryNumber();

        Mythread mythread = new Mythread(paymentList, debtorinventorynumber);
        executorService.execute(mythread);

        executorService.shutdown();


    }

    public static void createtransaction(String debtorinventorynumber, Payment destination) {
        if (!destination.getisDebtor()) {
            Transaction transaction = new Transaction();
            transaction.setDebtorInventoryNumber(debtorinventorynumber);
            transaction.setAmount(destination.getAmount());
            String CreditorInventoryNumber = destination.getInventoryNumber();
            transaction.setCreditorInventoryNumber(CreditorInventoryNumber);
            transactionList.add(transaction);
            try {
                FileManager.write(convertTDataTransactionListTostring(), "src\\main\\java\\resource\\transaction.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}










