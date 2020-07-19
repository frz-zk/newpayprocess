package mythread;

import DTO.Inventory;
import DTO.Payment;

import java.util.List;

import static service.Service.createtransaction;
import static service.Service.inventoryList;


public class Mythread implements Runnable {

    private final String debtorinventorynumber1;
    List<Payment> paymentList;

    public Mythread(List<Payment> paymentList, String debtorinventorynumber1) {
        this.paymentList = paymentList;
        this.debtorinventorynumber1 = debtorinventorynumber1;

    }


    @Override
    public void run() {

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
            createtransaction(debtorinventorynumber1, payment);

        }

    }
}



