package service;

import DTO.Inventory;
import DTO.Payment;
import DTO.Transaction;
import mythread.createtransaction;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    public Service() {

    }

    static mythread.Mythread payments = new mythread.Mythread();
    static ExecutorService executorService = Executors.newCachedThreadPool();
    static ExecutorService executorrService = Executors.newCachedThreadPool();

    public static List<Payment> paymentList = new ArrayList<>();

    public static createtransaction threadtarnsaction = new createtransaction();

    public static List<Inventory> inventoryList = new ArrayList<>();
    public static List<Transaction> transactionList = new ArrayList<>();


    public static void checkeExistencefile(String path) {
        if (!Files.exists(Paths.get(path))) {
            FileManager.create(path);
        }
    }

    public static void createDataPayment() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);


        for (int i = 1; i <= 3; i++) {
            Payment payment = new Payment();
            payment.setIsdebtor(false);
            payment.setAmount(BigDecimal.valueOf(amount));
            total = total.add(payment.getAmount());
            String deposit = "10.100.0." + i;

            payment.setInventoryNumber(deposit);
            paymentList.add(payment);
        }

        Payment debtorpayment = new Payment();
        debtorpayment.setIsdebtor(true);
        debtorpayment.setAmount(total);
        debtorpayment.setInventoryNumber("10.100.0.4");
        paymentList.add(debtorpayment);
    }

    public static String convertDataPaymentTostring() {
        StringBuilder result = new StringBuilder();
        for (Payment pay : paymentList) {
            result.append(pay.toString()).append("\n");
        }
        return result.toString();
    }

    public static void createDatainvantory() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);


        for (int i = 1; i <= 3; i++) {
            Inventory inventory = new Inventory();

            inventory.setAmount(BigDecimal.valueOf(amount));
            total = total.add(inventory.getAmount());
            String deposit = "10.100.0." + i;

            inventory.setinventoryNumber(deposit);
            inventoryList.add(inventory);
        }
        Inventory debtorininventory = new Inventory();
        int amount1 = (int) (Math.random() * 10000);
        debtorininventory.setAmount(BigDecimal.valueOf(amount1));
        debtorininventory.setinventoryNumber("10.100.0.4");
        inventoryList.add(debtorininventory);
    }

    public static String convertDataInventorytTostring() {
        StringBuilder result = new StringBuilder();
        for (Inventory inventory : inventoryList) {
            result.append(inventory.toString()).append("\n");
        }
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

        payments.paymentList(paymentList);
        executorService.execute(payments);
        threadtarnsaction.paymentList(paymentList);
        threadtarnsaction.setdebtorinventorynumber(debtorinventorynumber);
        executorrService.execute(threadtarnsaction);
        executorService.shutdown();
        executorrService.shutdown();

    }

}










