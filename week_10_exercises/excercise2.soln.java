public class OverdraftAccount extends BankAccount {

    @Override
    public void deposit(double amount) {
        // Log the transaction
        System.out.println("Depositing: " + amount);
        
        // Use parent's method since its logic handles positive deposits correctly
        super.deposit(amount); 
    }

    @Override
    public void withdraw(double amount) {
        // Log the transaction
        System.out.println("Withdrawing: " + amount);

        // Custom logic to allow overdraft down to -500
        if (amount > 0 && (balance - amount >= -500)) {
            balance -= amount;
        } else if (amount <= 0) {
            System.out.println("Transaction failed: Invalid withdrawal amount.");
        } else {
            System.out.println("Transaction failed: Overdraft limit of -500 exceeded.");
        }
    }
}

//Key Insight from Hints
//You cannot rely on super.withdraw(amount) because the parent class completely blocks the withdrawal if the amount exceeds the current balance. Instead, you must bypass the parent's rule by directly modifying the protected double balance field.


//Critical Architecture Observation
//As the slides point out, this exercise illustrates the "Fragility of Inheritance":

//Because OverdraftAccount directly reads and writes to balance, it is tightly coupled with the parent class's internal structure.

//If the author of BankAccount updates their class to make balance private instead of protected (a standard encapsulation practice), your OverdraftAccount subclass will instantly break and fail to compile.