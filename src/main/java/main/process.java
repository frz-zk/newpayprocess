package main;

import myException.myexceptio;
import service.FileManager;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static service.Service.*;


public class process {

    static Logger logger = Logger.getLogger("loggerPay");

    public static void main(String[] args) throws Exception {

        FileHandler handlerfile;
        handlerfile = new FileHandler("src\\main\\java\\resource\\filehandler.txt");

        SimpleFormatter formatter = new SimpleFormatter();
        handlerfile.setFormatter(formatter);
        logger.setLevel(Level.ALL);
        logger.addHandler(handlerfile);
        logger.entering("Paymentpr", "main");

        String inventorypath = "src\\main\\java\\resource\\inventory.txt";
        String paymentpath = "src\\main\\java\\resource\\payment.txt";

        checkeExistencefile(paymentpath);
        createDataPayment();
        String detapayment = convertDataPaymentTostring();
        FileManager.write(detapayment, paymentpath);

        checkeExistencefile(inventorypath);
        createDatainvantory();
        String detainventory = convertDataInventorytTostring();
        FileManager.write(detainventory, inventorypath);


        if (!checkAccountInventorydebtor()) {
            throw new myexceptio("The account balance is not enough");
        }

        finalPayments();


    }


}




