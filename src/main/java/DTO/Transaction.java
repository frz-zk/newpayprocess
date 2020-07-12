package DTO;

import java.math.BigDecimal;

public class Transaction {
    private String debtorInventoryNumber;
    private String creditorInventoryNumber;
    private BigDecimal Amount;

    public Transaction() {
    }

    public void setDebtorInventoryNumber(String debtorInventoryNumber) {
        this.debtorInventoryNumber = debtorInventoryNumber;
    }


    public void setCreditorInventoryNumber(String creditorInventoryNumber) {
        this.creditorInventoryNumber = creditorInventoryNumber;
    }

    public void setAmount(BigDecimal amount) {

        this.Amount = amount;
    }


    @Override
    public String toString() {
        return debtorInventoryNumber + "\t" + creditorInventoryNumber + "\t" + Amount;
    }


}
