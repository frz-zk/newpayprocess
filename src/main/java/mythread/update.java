package mythread;

import DTO.Inventory;
import DTO.Payment;

import java.util.ArrayList;
import java.util.List;

import static service.Service.createtransaction;
import static service.Service.inventoryList;


public class update implements Runnable {

    private final String debtorinventorynumber1;
    List<Payment> paymentList = new ArrayList<>();
    String paymentstring;

    public update(String paymentFilecontent, String debtorinventorynumber1) {
        this.paymentstring = paymentFilecontent;
        this.debtorinventorynumber1 = debtorinventorynumber1;

    }

    public  void convertotlist() {

        String[] paymentitemsString = paymentstring.split("\n");
        for (String item : paymentitemsString) {
            if (item==null||item.length()<4)
                continue;
            Payment payment = new Payment();
            payment.fromTostring(item);
            paymentList.add(payment);
        }

    }


    @Override
    public void run() {
        convertotlist();
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



