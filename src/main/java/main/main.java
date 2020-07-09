package main;

import myException.myexceptio;
import service.FileManager;
import service.Inventory;
import service.Payment;
import service.service;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class main {
    static Logger logger = Logger.getLogger("loggerPay");

    public static void main(String[] args) throws Exception {

        FileHandler handlerfile = null;
        handlerfile = new FileHandler("src\\main\\java\\resource\\filehandler.txt");

        SimpleFormatter formatter = new SimpleFormatter();
        handlerfile.setFormatter(formatter);
        logger.setLevel(Level.ALL);
        logger.addHandler(handlerfile);
        logger.entering("Paymentpr", "main");

        String paymentpath = "src\\main\\java\\resource\\payment.txt";
        String inventorypath = "src\\main\\java\\resource\\inventory.txt";
        String transactionpath = "src\\main\\java\\resource\\transaction.txt";

        FileManager paymentFile = new FileManager(paymentpath);
        FileManager inventoryFile = new FileManager(inventorypath);
        FileManager transactionFile = new FileManager(transactionpath);
        service service = new service();

        service.checkeExistencefil(paymentpath);
         service.createDataPayment();
        String detapay = service.convertDataPaymentTostring();
        paymentFile.write(detapay, paymentpath);


        service.checkeExistencefil(inventorypath);
         service.createDatainvantory();
        String detainv = service.convertDataInventorytTostring();
        inventoryFile.write(detainv, inventorypath);

        if (service.checkAccountInventorydebtor() == false) {
            throw new myexceptio("The account balance is not enough");
        }
        service.finalPayments();
        String datetransactionpath = service.convertTDataTransactionListTostring();
        transactionFile.write(datetransactionpath, transactionpath);
    }
}




