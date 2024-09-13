package PersonalBudgetTracker.Services;

import PersonalBudgetTracker.Exceptions.BudgetNotFoundException;
import PersonalBudgetTracker.Exceptions.ExpenseNotFoundException;
import PersonalBudgetTracker.Exceptions.UserNotFoundException;
import PersonalBudgetTracker.Models.Budget;
import PersonalBudgetTracker.Models.Expense;
import PersonalBudgetTracker.Repositories.BudgetRepository;
import PersonalBudgetTracker.Repositories.ExpenseRepository;
import PersonalBudgetTracker.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import PersonalBudgetTracker.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    // Create a new expense
    public String saveExpense(Expense expense, Integer budgetId) {
        // Find the budget associated with the expense
        Optional<Budget> budgetOpt = budgetRepository.findById(budgetId);
        if (budgetOpt.isPresent()) {
            Budget budget = budgetOpt.get();

            // Check if there’s enough budget left
            if (budget.getCurrentBudget() >= expense.getAmount()) {
                // Deduct the expense amount from the current budget
                budget.setCurrentBudget(budget.getCurrentBudget() - expense.getAmount());
                budgetRepository.save(budget);

                // Save the expense
                expense.setBudget(budget); // Associate the expense with the budget
                expenseRepository.save(expense);

                return "Expense has been added and deducted from the budget.";
            } else {
                return "Insufficient budget to cover this expense.";
            }
        } else {
            throw new BudgetNotFoundException("Budget with ID " + budgetId + " not found.");
        }
    }

    // Update an existing expense
    public String updateExpense(Integer id, Expense updatedExpense) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expense expense = existingExpense.get();
            expense.setAmount(updatedExpense.getAmount());
            expense.setDate(updatedExpense.getDate());
            expense.setCategory(updatedExpense.getCategory());
            expense.setDescription(updatedExpense.getDescription());
            expense.setTag(updatedExpense.getTag());
            expenseRepository.save(expense);
            return "Expense has been updated successfully.";
        } else {
            throw new ExpenseNotFoundException("Expense with ID " + id + " not found.");
        }
    }

    // Delete an expense by ID
    public String deleteExpense(Integer id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return "Expense with ID " + id + " has been deleted successfully.";
        } else {
            throw new ExpenseNotFoundException("Expense with ID " + id + " not found.");
        }
    }

    // Retrieve all expenses for a specific user
    public List<Expense> getExpensesByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return expenseRepository.findByUser(user.get());
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }

}
