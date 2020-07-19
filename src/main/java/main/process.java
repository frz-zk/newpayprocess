package main;

import myException.myexceptio;
import org.apache.log4j.BasicConfigurator;
import service.FileManager;


import java.util.logging.FileHandler;
import java.util.logging.Level;
//import java.util.logging.Leveogger;
import java.util.logging.SimpleFormatter;
import org.apache.log4j.Logger;

import static java.util.logging.Level.ALL;
import static service.Service.*;


public class process {

    static Logger logger = Logger.getLogger(process.class.getName());
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        logger.setLevel(org.apache.log4j.Level.ALL);

        String inventorypath = "src\\main\\java\\resource\\inventory.txt";
        String paymentpath = "src\\main\\java\\resource\\payment.txt";

        FileManager.write(createDataPayment(), paymentpath);

        FileManager.write(createDatainvantory(), inventorypath);


        if (!checkAccountInventorydebtor()) {
            throw new myexceptio("The account balance is not enough");
        }

        finalPayments();
        logger.info("Exiting the program");
    }
}




