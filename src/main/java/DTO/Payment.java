package DTO;

import java.math.BigDecimal;


public class Payment {
    private BigDecimal amount;
    private String inventoryNumber;
    private Boolean isdebtor;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public Payment() {

    }


    public Boolean getisDebtor() {
        return isdebtor;
    }

    public void setIsdebtor(Boolean isdebtor) {

        this.isdebtor = isdebtor;

    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    @Override
    public String toString() {
        String debtor;
        if (isdebtor) {
            debtor = "debtor";

        } else debtor = "creditor";


        return debtor + "\t" + inventoryNumber + "\t" + amount;
    }


    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
}
