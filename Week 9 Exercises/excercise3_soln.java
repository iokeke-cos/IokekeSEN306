public class LegacyOrderFacade {
    // Composition: We hold a reference to the legacy class
    private final LegacyOrderProcessor legacyProcessor;

    public LegacyOrderFacade() {
        this.legacyProcessor = new LegacyOrderProcessor();
    }

    // This provides the "clean placeOrder" method requested 
     
    public void placeOrder(String customerEmail, String itemCode, 
                           double amount, String deliveryAddress) {

        legacyProcessor.processOrder(customerEmail, itemCode, amount, deliveryAddress);
    }
}

//Encapsulation: The client doesn't need to know how to talk to 4-6 different classes; they just call checkout().
//Rollback Logic: By centralizing the process in the Facade, we can easily handle "undo" operations (refunds/releases) if a middle step fails.
//Decoupling: Adding the TaxCalculator or Logger only required changes inside the Facade, leaving the "client" (the code calling the facade) completely untouched.