public class Payments {
    
    private double amount;
    private String paymentMethod;
    private String transactionId;
    private boolean isProcessed;
    
    // Constructor
    public Payments(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isProcessed = false;
    }
    
    // Process Payment
    public boolean processPayment() {
        if (amount > 0 && paymentMethod != null && !paymentMethod.isEmpty()) {
            this.transactionId = generateTransactionId();
            this.isProcessed = true;
            System.out.println("Payment of $" + amount + " processed successfully!");
            return true;
        }
        System.out.println("Payment processing failed!");
        return false;
    }
    
    // Generate Transaction ID
    private String generateTransactionId() {
        return "TXN_" + System.currentTimeMillis();
    }
    
    // Get Payment Details
    public void displayPaymentDetails() {
        System.out.println("Amount: "$" + amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Status: " + (isProcessed ? "Processed" : "Pending"));
    }
    
    // Getters and Setters
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public boolean isProcessed() {
        return isProcessed;
    }
}