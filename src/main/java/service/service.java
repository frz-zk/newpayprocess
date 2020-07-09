package service;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class service {
    public service() {

    }

    public static List<Payment> paymentList = new ArrayList<Payment>();
    public static List<Inventory> inventoryList = new ArrayList<Inventory>();
    public static List<Transaction> transactionList = new ArrayList<Transaction>();


    public List<Payment> createDataPayment() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);
        for (int i = 1; i <= 3; i++) {
            Payment payment = new Payment();
            payment.setIsdebtor(false);
            payment.setAmount(BigDecimal.valueOf(amount));
            total = total.add(payment.getAmount());
            payment.setInventoryNumber1(i);
            paymentList.add(payment);
        }
        Payment debtorpayment = new Payment();
        debtorpayment.setIsdebtor(true);
        debtorpayment.setAmount(total);
        debtorpayment.setInventoryNumber1(4);
        paymentList.add(debtorpayment);
        return paymentList;
    }

    public String convertDataPaymentTostring() {
        String result = "";
        for (Payment pay : paymentList) {
            result += pay.toString() + "\n";
        }
        return result;
    }

    public void checkeExistencefil(String path) {
        if (!Files.exists(Paths.get(path))) {
            FileManager.create(path);
        }
    }





    public List<Inventory> createDatainvantory() {
        BigDecimal total = new BigDecimal(0);
        int amount = (int) (Math.random() * 1000);

        for (int i = 1; i <= 3; i++) {
            Inventory inventory = new Inventory();
            inventory.setAmount(BigDecimal.valueOf(amount));
            total = total.add(inventory.getAmount());
            inventory.setinventoryNumber1(i);
            inventoryList.add(inventory);
        }
        Inventory debtorininventory = new Inventory();
        int amount1 = (int) (Math.random() * 10000);
        debtorininventory.setAmount(BigDecimal.valueOf(amount1));
        debtorininventory.setinventoryNumber1(4);
        inventoryList.add(debtorininventory);
        return inventoryList;
    }

    public String convertDataInventorytTostring() {
        String result = "";
        for (Inventory inventory :inventoryList ) {
            result += inventory.toString() + "\n";
        }
        return result;
    }

    public Payment datadebtor() {
        for (Payment payment : paymentList) {
            if (payment.getisDebtor() == true) {
                return payment;
            }
        }
        return null;
    }

    public boolean checkAccountInventorydebtor() throws Exception {
        for (Inventory inventory : inventoryList) {
            if (inventory.getinventoryNumber1() == datadebtor().getInventoryNumber1()) {
                if (inventory.getAmount().compareTo(datadebtor().getAmount()) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
    public String convertTDataTransactionListTostring() {
        String result = "";
        for (Transaction transaction :transactionList ) {
            result += transaction.toString() + "\n";
        }
        return result;
    }

    public void finalPayments() {
        Payment debtorpayment = datadebtor();
        String debtorinventorynumber = debtorpayment.getInventoryNumber() + "." + debtorpayment.getInventoryNumber1();

        for (Payment payment : paymentList) {
            update(payment);
            createtransaction(debtorinventorynumber,payment);
        }


    }
    public void update (Payment payment) {
        for (Inventory inventory : inventoryList) {
            if (payment.getInventoryNumber1() == inventory.getinventoryNumber1()) {

                if (payment.getisDebtor() == true) {
                    inventory.setAmount(payment.getAmount().add(inventory.getAmount()));
                } else {
                    inventory.setAmount(payment.getAmount().subtract(inventory.getAmount()));
                }
                break;
            }

        }
    }
    public  void createtransaction(String debtorinventorynumber,Payment destination){
        if (destination.getisDebtor()==false) {
            Transaction transaction = new Transaction();
            transaction.setDebtorInventoryNumber(debtorinventorynumber);
            transaction.setAmount(destination.getAmount());
            String CreditorInventoryNumber = destination.getInventoryNumber() + "." + destination.getInventoryNumber1();
            transaction.setCreditorInventoryNumber(CreditorInventoryNumber);
            transactionList.add(transaction);

        }
    }
}


//    public void CheckIfDebtAndCreditAreTheSame(String[] paymentLines) throws myexceptio {
//        Payment payment =new Payment();
//        List<Payment> payments =payment.readPaymentFromPaymentFile(paymentLines);
//        Payment deptorPayment = new Payment();
//        BigDecimal totalDebtorPayment = new BigDecimal(0);
//        BigDecimal totalCreditorPayment = new BigDecimal(0);
//
//        for (Payment pay : payments)
//            if (pay.getisDebtor()) {
//                deptorPayment = pay;
//                totalDebtorPayment = totalDebtorPayment.add(pay.getAmount());
//                totalCreditorPayment = totalCreditorPayment.add(pay.getAmount());
//
//            }
//
//
//        if (totalCreditorPayment.compareTo(totalDebtorPayment) < 0) {
//            throw new myexceptio("debt and credit are not the same!");
//
//
//
//
//        }


//    public void CheckToseeisInventoryeEough(String[] inventoryLines, List<Payment> payments) throws myexceptio {
//        List<Inventory> inventories = Inventory.readInventoriesFromFile(inventoryLines);
//        for (Inventory inventory : inventories) {
//            BigDecimal totalDebt = new BigDecimal(0);
//            for (Payment pay : payments) {
//                if (pay.getisDebtor() && pay.getInventoryNumber().equals(inventory.getDepositNumber())) {
//                    totalDebt = totalDebt.add(pay.getAmount());
//
//                }
//            }
//
//            if (inventory.getAmount().compareTo(totalDebt) < 0) {
//                throw new myexceptio("The account balance is not enough");
//            }
//        }
//








