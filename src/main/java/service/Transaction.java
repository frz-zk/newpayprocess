package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String debtorInventoryNumber;
    private String creditorInventoryNumber;
    private BigDecimal Amount;

    public Transaction(String debtorInventoryNumber, String creditorInventoryNumber, BigDecimal amount) {
        this.debtorInventoryNumber = debtorInventoryNumber;
        this.creditorInventoryNumber = creditorInventoryNumber;
        this.Amount = amount;
    }

    public Transaction() {
    }



    public String getDebtorInventoryNumber() {
        return debtorInventoryNumber;
    }

    public void setDebtorInventoryNumber(String debtorInventoryNumber) {
        this.debtorInventoryNumber = debtorInventoryNumber;
    }

    public String getCreditorInventoryNumber() {
        return creditorInventoryNumber;
    }

    public void setCreditorInventoryNumber(String creditorInventoryNumber) {
        this.creditorInventoryNumber = creditorInventoryNumber;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        this.Amount = amount;
    }

    public String getText() {
        return debtorInventoryNumber + "\t" + creditorInventoryNumber + "\t" + Amount;
    }
    public static String getTransactionText(List<Transaction> transactions) {
        StringBuilder result = new StringBuilder();
        for (Transaction tr : transactions) {
            result.append(tr.getText()).append("\r");
        }
        return result.toString();
    }


//    public
//    List<Transaction> transactions = new ArrayList<>();
//        for (Payment pay : payments) {
//
//        for (Inventory inventory : inventories) {
//            if (inventory.getAmount().compareTo(pay.getAmount()) >= 0) {
//                if (pay.getisDebtor()) {
//                    inventory.decrease(pay.getAmount());
//
//                } else {
//                    inventory.increase(pay.getAmount());
//                }
//            }
//        }
//

}
