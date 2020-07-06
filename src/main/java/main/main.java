package main;

import myException.myexceptio;
import service.FileManager;
import service.Inventory;
import service.Payment;
import service.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static service.Inventory.getInventoryText;
import static service.Transaction.getTransactionText;


public class main {
    static Logger logger = Logger.getLogger("loggerPay");

    public static void main(String[] args) throws myexceptio, IOException {
        Payment payment = new Payment();
        Transaction transaction = new Transaction();
        Inventory inventoryy = new Inventory();

        FileHandler handlerfile = null;
        handlerfile = new FileHandler("src\\main\\java\\resource\\filehandler.txt");

        SimpleFormatter formatter = new SimpleFormatter();
        handlerfile.setFormatter(formatter);
        logger.setLevel(Level.ALL);
        logger.addHandler(handlerfile);
        logger.entering("Paymentpr", "main");


        FileManager paymentFile = new FileManager("src\\main\\java\\resource\\payment.txt");
        FileManager inventoryFile = new FileManager("src\\main\\java\\resource\\inventory.txt");
        FileManager transactionFile = new FileManager("src\\main\\java\\resource\\transaction.txt");

        try {
            if (!Files.exists(Paths.get("src\\main\\java\\resource\\payment.txt"))) {
                throw new myexceptio("not found file ");
            }
        } catch (myexceptio myexception) {
            myexception.printStackTrace();
        }


        String[] paymentLines = paymentFile.openfile();
        List<Payment> payments = payment.readPaymentFromPaymentFile(paymentLines);
        Payment deptorPayment = new Payment();
        //Check if debt and credit are the same
//        payment.CheckIfDebtAndCreditAreTheSame(payments, deptorPayment );
        BigDecimal totalDebtorPayment = new BigDecimal(0);
        BigDecimal totalCreditorPayment = new BigDecimal(0);

        for (
                Payment pay : payments)
            if (pay.getisDebtor()) {
                deptorPayment = pay;
                totalDebtorPayment = totalDebtorPayment.add(pay.getAmount());
            } else {
                totalCreditorPayment = totalCreditorPayment.add(pay.getAmount());
            }

        if (totalCreditorPayment.compareTo(totalDebtorPayment) < 0) {
            System.out.println("debt and credit are not the same!");
            return;
        }
    

        if (!Files.exists(Paths.get("src\\main\\java\\resource\\inventory.txt"))) {
            throw new myexceptio("not found file");
        }


        String[] inventoryLines = inventoryFile.openfile();
        List<Inventory> inventories = inventoryy.readInventoriesFromFile(inventoryLines);
        inventoryy.CheckToseeisInventoryeEough(inventoryLines, payments);

        //Now that everything is ok let's start proccess
        //Update inventory and log transactions
        List<Transaction> transactions = new ArrayList<>();
        for (
                Payment pay : payments) {

            for (Inventory inventory : inventories) {
                if (inventory.getAmount().compareTo(pay.getAmount()) >= 0) {
                    if (pay.getisDebtor()) {
                        inventory.decrease(pay.getAmount());

                    } else {
                        inventory.increase(pay.getAmount());
                    }
                }
            }

            if (!pay.getisDebtor()) {
//                Transaction transaction = new Transaction();

                transaction.setDebtorInventoryNumber(deptorPayment.getInventoryNumber());
                transaction.setCreditorInventoryNumber(pay.getInventoryNumber());
                transaction.setAmount(pay.getAmount());
                transactions.add(transaction);
            }

        }

        String inventoryData = getInventoryText(inventories);
        inventoryFile.overrideFile(inventoryData);

        String transactionData = getTransactionText(transactions);
        transactionFile.append(transactionData);
        logger.exiting(" paymentpr", "main");

    }
}

