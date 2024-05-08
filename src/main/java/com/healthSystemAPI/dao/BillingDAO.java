package com.healthSystemAPI.dao;

import com.healthSystemAPI.classes.Billing;
import com.healthSystemAPI.classes.Patient;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    // Store Bills in an ArrayList
    private static List<Billing> billingsList = new ArrayList<>();

    static {
        List<Patient> allPatients = PatientDAO.getAllPatients();

        billingsList.add(new Billing(allPatients.get(0), 1, 001, 001, 44000, 40000, 4000));
        billingsList.add(new Billing(allPatients.get(1), 2, 002, 002, 36000, 10000, 16000));
    }

    // Method to retrieve All Bills
    public List<Billing> getAllBillings() {
        return billingsList;
    }

    // Method to retrieve Bills by ID, browsing through the entire billingsList
    public Billing getBilling(int Id) {
        for (Billing billing : billingsList) {
            if (billing.getBillingId() == Id) {
                return billing;
            }
        }
        return null;
    }

    // Creating a new Bill in the Bills.(billingsList)
    public void addBilling(Billing billing) {
        int newUserId = getNextBillId();
        billing.setBillingId(newUserId);
        billingsList.add(billing);
    }

    // Updating the Existing bill by an ID in the (billingsList)
    public void updateBilling(Billing updatedBilling) {
        for (int i = 0; i < billingsList.size(); i++) {
            Billing billing = billingsList.get(i);
            if (billing.getBillingId() == updatedBilling.getBillingId()) {
                billingsList.set(i, updatedBilling);
                System.out.println("Student updated: " + updatedBilling);
                return;
            }
        }
    }

    // Deleting the Existing bill by an ID in the (billingsList)
    public void deleteBills(int No) {
        billingsList.removeIf(Billing -> Billing.getBillingId() == No);
    }

    // The below method acts to maintain the Unique ID for billigs.
    public int getNextBillId() {
        int maxUserId = Integer.MIN_VALUE;
        for (Billing Bill : billingsList) {
            int userId = Bill.getBillingId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }
        return maxUserId + 1;
    }
}
