package main;

import myException.myexceptio;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import service.FileManager;

import static service.Service.*;

//import java.util.logging.Leveogger;


public class process {

    static Logger logger = Logger.getLogger(process.class.getName());

    public static void main(String[] args) throws Exception {
       FileManager.Readfile("src\\main\\java\\resource\\payment.txt");
        BasicConfigurator.configure();
        logger.setLevel(org.apache.log4j.Level.ALL);

        String inventorypath = "src\\main\\java\\resource\\inventory.txt";
        String paymentpath = "src\\main\\java\\resource\\payment.txt";
        String DataPayment = createDataPayment();
        FileManager.write(DataPayment, paymentpath);
        String Datainvantory = createDatainvantory();
        FileManager.write(Datainvantory, inventorypath);


        if (!checkAccountInventorydebtor()) {
            throw new myexceptio("The account balance is not enough");
        }

        finalPayments();
        logger.info("Exiting the program");
    }
}




