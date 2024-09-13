package PersonalBudgetTracker.Exceptions;

public class BudgetAlreadyExistsException extends RuntimeException{
    public BudgetAlreadyExistsException(String message) {
        super(message);
    }
}
