package PersonalBudgetTracker.Services;

import PersonalBudgetTracker.Exceptions.BudgetAlreadyExistsException;
import PersonalBudgetTracker.Exceptions.BudgetNotFoundException;
import PersonalBudgetTracker.Models.Budget;
import PersonalBudgetTracker.Repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    // Create a new budget
    public String saveBudget(Budget budget) {
        boolean budgetExists = budgetRepository.findByName(budget.getName()).isPresent();
        if (budgetExists) {
            throw new BudgetAlreadyExistsException("Budget with name " + budget.getName() + " already exists.");
        }

        budgetRepository.save(budget);
        return "Budget has been added successfully.";
    }

    // Update an existing budget
    public String updateBudget(Integer id, Budget updatedBudget) {
        Optional<Budget> existingBudget = budgetRepository.findById(id);
        if (existingBudget.isPresent()) {
            Budget budget = existingBudget.get();
            budget.setName(updatedBudget.getName());
            budget.setCategory(updatedBudget.getCategory());
            budget.setBudgetLimit(updatedBudget.getBudgetLimit());
            budget.setPeriod(updatedBudget.getPeriod());
            budgetRepository.save(budget);
            return "Budget has been updated successfully.";
        } else {
            throw new BudgetNotFoundException("Budget with ID " + id + " not found.");
        }
    }

    // Delete a budget by ID
    public String deleteBudget(Integer id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return "Budget with ID " + id + " has been deleted successfully.";
        } else {
            throw new BudgetNotFoundException("Budget with ID " + id + " not found.");
        }
    }

    // Retrieve all budgets
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // Retrieve a budget by ID
    public Budget getBudgetById(Integer id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            return budget.get();
        } else {
            throw new BudgetNotFoundException("Budget with ID " + id + " not found.");
        }
    }

    // Method to compare budget limit and current budget
    public String compareBudgetLimit(Integer budgetId) {
        Optional<Budget> budgetOpt = budgetRepository.findById(budgetId);
        if (budgetOpt.isPresent()) {
            Budget budget = budgetOpt.get();
            Integer budgetLimit = budget.getBudgetLimit();
            Integer currentBudget = budget.getCurrentBudget();

            if (currentBudget < budgetLimit) {
                return "You have " + (budgetLimit - currentBudget) + " left to spend.";
            } else {
                return "Your current budget matches the limit.";
            }
        } else {
            throw new BudgetNotFoundException("Budget with ID " + budgetId + " not found.");
        }
    }
}
