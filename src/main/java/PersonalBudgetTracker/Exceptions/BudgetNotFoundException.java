package PersonalBudgetTracker.Exceptions;

public class BudgetNotFoundException extends RuntimeException{
    public BudgetNotFoundException(String message) {
        super(message);
    }
}
