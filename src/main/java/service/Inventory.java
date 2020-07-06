package service;

import myException.myexceptio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String DepositNumber;
    private BigDecimal Amount;

    public Inventory(String inventoryNumber, BigDecimal amount) {
        this.DepositNumber = inventoryNumber;
        this.Amount = amount;
    }

    public static List<Inventory> readInventoriesFromFile(String[] rawData) {
        List<Inventory> inventories = new ArrayList<>();
        for (String line : rawData) {
            Inventory inventory = new Inventory();
            inventory.readLine(line);
            inventories.add(inventory);
        }
        return inventories;
    }

    public static String getInventoryText(List<Inventory> inventories) {
        StringBuilder result = new StringBuilder();
        for (Inventory inventory : inventories) {
            result.append(inventory.getText()).append("\r");
        }
        return result.toString();
    }

    public Inventory() {

    }

    public void readLine(String line) {
        String[] items = line.split("\t");
        DepositNumber = items[0];
        Amount = new BigDecimal(items[1]);
    }

    public String getDepositNumber() {
        return DepositNumber;
    }

    public void increase(BigDecimal amount) {
        this.Amount = this.Amount.add(amount);
    }

    public String getText() {
        return DepositNumber + "\t" + Amount;
    }

    public void decrease(BigDecimal amount) {
        Amount = this.Amount.subtract(amount);
    }

    public void setInventoryNumber(String depositNumber) {
        this.DepositNumber = depositNumber;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        this.Amount = amount;
    }

    public void CheckToseeisInventoryeEough( String[]inventoryLines , List<Payment> payments) {
        List<Inventory> inventories = readInventoriesFromFile(inventoryLines);
        for (Inventory inventory : inventories) {
            BigDecimal totalDebt = new BigDecimal(0);
            for (Payment pay : payments) {
                if (pay.getisDebtor() && pay.getInventoryNumber().equals(inventory.getDepositNumber())) {
                    totalDebt = totalDebt.add(pay.getAmount());
                }
            }
            try {
                if (inventory.getAmount().compareTo(totalDebt) < 0) {
                    throw new myexceptio("The account balance is not enough");
                }
            } catch (myexceptio myexception) {
                myexception.printStackTrace();
            }
        }

    }
}
