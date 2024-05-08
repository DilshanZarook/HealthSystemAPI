package com.healthSystemAPI.classes;

public class Billing {
    
    private Patient patient;
    private int billingId;
    private int invoiceId;
    private int paymentId;
    private double totalBill;
    private double totalPayable;
    private double balance;
    
    public Billing(){
    }
    
    public Billing(Patient patient, int billingId, int invoiceId, int paymentId,double totalBill, double totalPayable, double balance ) {
        this.patient = patient;
        this.billingId = billingId;
        this.invoiceId = invoiceId;
        this.paymentId = paymentId;
        this.totalBill =totalBill;
        this.totalPayable = totalPayable;
        this.balance = balance;
  }
    
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = totalBill- totalPayable ;
    }
   
}
