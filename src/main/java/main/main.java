package main;

import myException.myexceptio;
import service.FileManager;
import service.service;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class main {
    static Logger logger = Logger.getLogger("loggerPay");

    public static void main(String[] args) throws Exception {

        FileHandler handlerfile;
        handlerfile = new FileHandler("src\\main\\java\\resource\\filehandler.txt");

        SimpleFormatter formatter = new SimpleFormatter();
        handlerfile.setFormatter(formatter);
        logger.setLevel(Level.ALL);
        logger.addHandler(handlerfile);
        logger.entering("Paymentpr", "main");

        String paymentpath = "src\\main\\java\\resource\\payment.txt";
        String inventorypath = "src\\main\\java\\resource\\inventory.txt";
        String transactionpath = "src\\main\\java\\resource\\transaction.txt";
        service service = new service();

        service.checkeExistencefile(paymentpath);
        service.createDataPayment();
        String detapay = service.convertDataPaymentTostring();
        FileManager.write(detapay, paymentpath);


        service.checkeExistencefile(inventorypath);
        service.createDatainvantory();
        String detainv = service.convertDataInventorytTostring();
        FileManager.write(detainv, inventorypath);

        if (!service.checkAccountInventorydebtor()) {
            throw new myexceptio("The account balance is not enough");
        }
        service.finalPayments();
        String datetransactionpath = service.convertTDataTransactionListTostring();
        FileManager.write(datetransactionpath, transactionpath);
    }
}




