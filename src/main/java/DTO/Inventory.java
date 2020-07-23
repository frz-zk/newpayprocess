package DTO;

import java.math.BigDecimal;


public class Inventory {
    private String inventoryNumber;
    private BigDecimal Amount;

    public Inventory() {

    }


    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        this.Amount = amount;
    }

    @Override
    public String toString() {
        return inventoryNumber + "\t" + Amount;
    }


    public String getinventoryNumber() {
        return inventoryNumber;
    }

    public void setinventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

}

