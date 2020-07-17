package mythread;

import DTO.Payment;
import DTO.Transaction;
import service.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static service.Service.convertTDataTransactionListTostring;
import static service.Service.transactionList;

public class createtransaction implements Runnable {

    private String debtorinventorynumber1;

    List<Payment> paymentList = new ArrayList<>();


    public void paymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;

    }

    public void setdebtorinventorynumber(String debtorinventorynumber) {
        this.debtorinventorynumber1 = debtorinventorynumber;
    }

    @Override

    public void run() {
        for (Payment payment : paymentList) {

            if (!payment.getisDebtor()) {
                Transaction transaction = new Transaction();
                transaction.setDebtorInventoryNumber(debtorinventorynumber1);
                transaction.setAmount(payment.getAmount());
                String CreditorInventoryNumber =payment.getInventoryNumber();
                transaction.setCreditorInventoryNumber(CreditorInventoryNumber);
                transactionList.add(transaction);
                String datetransactionpath = convertTDataTransactionListTostring();
                try {
                    FileManager.write(datetransactionpath, "src\\main\\java\\resource\\transaction.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            }



    }
}


