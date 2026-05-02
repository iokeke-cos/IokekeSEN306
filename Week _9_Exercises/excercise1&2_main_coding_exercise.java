import java.util.Date;

public class CheckoutFacade {
    private final Inventory inventory;
    private final Payment payment;
    private final Shipping shipping;
    private final Email email;
    private final TaxCalculator taxCalculator;
    private final Logger logger;

    public CheckoutFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.email = new Email();
        this.taxCalculator = new TaxCalculator();
        this.logger = new Logger();
    }

    public OrderResult checkout(String userId, String productId, double price, String address) {
        // Exercise 2: Log attempt
        logger.log(userId, "Attempting checkout for " + productId);

        // 1. Check Stock
        if (!inventory.checkStock(productId)) {
            logger.log(userId, "FAIL: Out of stock");
            return new OrderResult(false, null, "Product out of stock");
        }

        // 2. Reserve Inventory
        inventory.reserve(productId);

        // 3. Process Payment
        if (!payment.charge(userId, price)) {
            // Rollback: No payment, so release inventory
            inventory.release(productId);
            logger.log(userId, "FAIL: Payment failed");
            return new OrderResult(false, null, "Payment failed");
        }

        // 4. Process Shipping
        if (!shipping.isAvailable()) {
            // Rollback: Refund payment and release inventory
            payment.refund(userId, price);
            inventory.release(productId);
            logger.log(userId, "FAIL: Shipping unavailable");
            return new OrderResult(false, null, "Shipping service unavailable");
        }

        String tracking = shipping.createLabel(address);
        shipping.schedulePickup(tracking);

        // 5. Exercise 2: Calculate Tax and Email
        double taxRate = taxCalculator.calculateTax(address);
        double totalPrice = price + (price * taxRate);
        email.send(userId, "Order Success", "Total Paid: $" + totalPrice + ". Tracking: " + tracking);

        logger.log(userId, "SUCCESS: Order complete");
        return new OrderResult(true, tracking, "Order placed successfully!");
    }
}

// Supporting Classes for Exercise 2
class TaxCalculator {
    public double calculateTax(String address) {
        return (address != null && address.contains("CA")) ? 0.08 : 0.0;
    }
}

class Logger {
    public void log(String userId, String message) {
        System.out.println("[" + new Date() + "] User: " + userId + " - " + message);
    }
}