package service;

import java.math.BigDecimal;


public class Inventory {
    private String inventoryNumber;
    private int inventoryNumber1;
    private BigDecimal Amount;

    public int getinventoryNumber1() {
        return inventoryNumber1;
    }

    public void setinventoryNumber1(int depositNumber1) {
        inventoryNumber1 = depositNumber1;
    }

    public Inventory() {
        this.inventoryNumber = "1.10.100";

    }


    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        this.Amount = amount;
    }

    @Override
    public String toString() {
        return inventoryNumber + "." + inventoryNumber1 + "\t" + Amount;
    }

    public void fromstring(String line) {

        String[] items = line.split("\t");
        String[] inventoryNumbers = items[0].split(".");
        inventoryNumber=inventoryNumbers[0]+"."+inventoryNumbers[1];
        Amount = new BigDecimal(items[1]);
    }
}

