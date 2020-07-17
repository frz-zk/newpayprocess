package mythread;

import DTO.Inventory;
import DTO.Payment;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static service.Service.inventoryList;


public class Mythread implements Runnable {

    Payment payment = new Payment();
    List<Payment> paymentList = new ArrayList<>();


    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void paymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;

    }

    @Override
    public void run() {


//    public static void update(Payment payment) {
        for (Payment payment : paymentList) {

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
    }
}



