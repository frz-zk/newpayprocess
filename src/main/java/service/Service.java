package service;

import DTO.Inventory;
import DTO.Payment;
import DTO.Transaction;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {
    public Service() {

    }

    public static List<Payment> paymentList = new ArrayList<>();
    public static List<Inventory> inventoryList = new ArrayList<>();
    public static List<Transaction> transactionList = new ArrayList<>();


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

    public static void checkeExistencefile(String path) {
        if (!Files.exists(Paths.get(path))) {
            create(path);
        }
    }


    public static void createDatainvantory() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);


        for (int i = 1; i <= 3; i++) {
            Inventory inventory;
            inventory = new Inventory();
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

        for (Payment payment : paymentList) {
            update(payment);
            createtransaction(debtorinventorynumber, payment);
        }


    }

    public static void update(Payment payment) {
        for (Inventory inventory : inventoryList) {
            if (payment.getInventoryNumber().equals(inventory.getinventoryNumber())) {

                if (!payment.getisDebtor()) {
                    inventory.setAmount(payment.getAmount().add(inventory.getAmount()));
                } else {
                    inventory.setAmount(payment.getAmount().subtract(inventory.getAmount()));
                }
                break;
            }

        }
    }

    public static void createtransaction(String debtorinventorynumber, Payment destination) {
        if (!destination.getisDebtor()) {
            Transaction transaction = new Transaction();
            transaction.setDebtorInventoryNumber(debtorinventorynumber);
            transaction.setAmount(destination.getAmount());
            String CreditorInventoryNumber = destination.getInventoryNumber();
            transaction.setCreditorInventoryNumber(CreditorInventoryNumber);
            transactionList.add(transaction);

        }
    }

    public static void create(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();
    }
}










