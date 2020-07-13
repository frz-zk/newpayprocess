package main;

import myException.myexceptio;
import service.Mythread;
import service.Service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class process {

    static Logger logger = Logger.getLogger("loggerPay");

    public static void main(String[] args) throws Exception {
        try {


            Mythread paymentthread = new Mythread();
            Mythread inventorythread = new Mythread();
            Mythread transactionthread = new Mythread();

            FileHandler handlerfile;
            handlerfile = new FileHandler("src\\main\\java\\resource\\filehandler.txt");

            SimpleFormatter formatter = new SimpleFormatter();
            handlerfile.setFormatter(formatter);
            logger.setLevel(Level.ALL);
            logger.addHandler(handlerfile);
            logger.entering("Paymentpr", "main");


            Service.checkeExistencefile("src\\main\\java\\resource\\payment.txt");
            Service.createDataPayment();
            String detapayment = Service.convertDataPaymentTostring();
//        FileManager.write(detapay, "src\\main\\java\\resource\\payment.txt");
            paymentthread.setPath("src\\main\\java\\resource\\payment.txt");
            paymentthread.setText(detapayment);
            paymentthread.start();

            Service.checkeExistencefile("src\\main\\java\\resource\\inventory.txt");
            Service.createDatainvantory();
            String detainventory = Service.convertDataInventorytTostring();
//        FileManager.write(detainv, "src\\main\\java\\resource\\inventory.txt");
            inventorythread.setPath("src\\main\\java\\resource\\inventory.txt");
            inventorythread.setText(detainventory);
            inventorythread.start();

            if (!Service.checkAccountInventorydebtor()) {
                throw new myexceptio("The account balance is not enough");
            }

            Service.finalPayments();
            String datetransactionpath = Service.convertTDataTransactionListTostring();
//        FileManager.write(datetransactionpath, "src\\main\\java\\resource\\transaction.txt");
            transactionthread.setPath("src\\main\\java\\resource\\transaction.txt");
            transactionthread.setText(datetransactionpath);
            transactionthread.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (myException.myexceptio myexceptio) {
            myexceptio.printStackTrace();
        }
    }


}




