package service;

import java.math.BigDecimal;



public class Payment {
    private BigDecimal amount;
    private String inventoryNumber;
    private int inventoryNumber1;
    private Boolean isdebtor;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public Payment() {
        this.amount = amount;
        this.inventoryNumber = "1.10.100";
        this.inventoryNumber1 = inventoryNumber1;
        this.isdebtor = isdebtor;

    }


    public Boolean getisDebtor() {
        if (isdebtor) return isdebtor;
        else return false;
    }

    public void setIsdebtor(Boolean isdebtor) {

        this.isdebtor = isdebtor;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getInventoryNumber1() {
        return inventoryNumber1;
    }

    public void setInventoryNumber1(int inventoryNumber1) {
        this.inventoryNumber1 = inventoryNumber1;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    @Override
    public String toString() {
        String debtor ="" ;
        if (isdebtor==true){
            debtor="debtor";

        }else if(isdebtor==false) {

           debtor = "creditor";
        }


        return debtor + "\t" + inventoryNumber + "." + inventoryNumber1 + "\t" + amount;
    }


}
