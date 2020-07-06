package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//import java.nio.*

public class Payment {
    private BigDecimal amount;
    private String inventoryNumber;
    private Boolean isdebtor;

    public Payment(BigDecimal amount, String inventoryNumber, Boolean isdebtor) {
        this.amount = amount;
        this.inventoryNumber = inventoryNumber;
        this.isdebtor = isdebtor;
    }

    public Payment() {

    }


    public Boolean getisDebtor() {
        if (isdebtor) return true;
        else return false;
    }

    public void setisdebtor(Boolean isdebtor) {
        this.isdebtor = isdebtor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setinventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }


    public void readLine(String line) {
        String[] items = line.split("\t");
        isdebtor = convertisDebtorStringToBoolean(items[0]);
        inventoryNumber = items[1];
        amount = new BigDecimal(items[2]);
    }
    public static List<Payment> readPaymentFromPaymentFile(String[] rawData) {


        List<Payment> payments = new ArrayList<>();

        for (String line : rawData) {
            Payment paymen = new Payment();
            paymen.readLine(line);
            payments.add(paymen);
        }

        return payments;
    }

//    @Override
//    public String toString() {
//        return isdebtor + " " + isdebtor;
//    }


//    public static void printDeposite(Payment payment) {
//        System.out.print(" isdebtor:" + payment.isdebtor);
//        System.out.print(" amount:" + payment.amount);
//        System.out.print("depositeNumber:" + payment.inventoryNumber);
//    }


    public static Boolean convertisDebtorStringToBoolean(String isdebtor) {
        if (isdebtor.equals("isdebtor")) {
            return true;
        } else {
            return false;
        }
    }

//    public void CheckIfDebtAndCreditAreTheSame( List<Payment> payments, obj  deptorPayment) {
//
//        BigDecimal totalDebtorPayment = new BigDecimal(0);
//        BigDecimal totalCreditorPayment = new BigDecimal(0);
//
//        for (
//                Payment pay : payments)
//            if (pay.getisDebtor()) {
//                deptorPayment = pay;
//                totalDebtorPayment = totalDebtorPayment.add(pay.getAmount());
//            } else {
//                totalCreditorPayment = totalCreditorPayment.add(pay.getAmount());
//            }
//
//        if (totalCreditorPayment.compareTo(totalDebtorPayment) < 0) {
//            System.out.println("debt and credit are not the same!");
//            return;
//        }

}
