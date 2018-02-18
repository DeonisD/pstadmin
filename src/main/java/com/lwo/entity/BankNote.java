package com.lwo.entity;

/**
 *
 * @author drozdov_d
 */
public class BankNote {
    private Double value;
    private String currency;
    private Double amount;
    private Integer count;


    public BankNote() {
    }

    public BankNote(Double value, String currency, Double amount, Integer count) {
        this.value      = value;
        this.currency   = currency;
        this.amount     = amount;
        this.count      = count;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BankNote{" + "value=" + value + ", currency=" + currency + ", amount=" + amount + ", count=" + count + '}';
    }
}