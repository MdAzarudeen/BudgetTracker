package PersonalBudgetTracker.Controllers;

import PersonalBudgetTracker.Models.Expense;
import PersonalBudgetTracker.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Create a new expense
    @PostMapping("/create")
    public ResponseEntity<String> createExpense(@RequestBody Expense expense, @PathVariable Integer budgetId) {
        String response = expenseService.saveExpense(expense,budgetId);
        return ResponseEntity.ok(response);
    }

    // Update an existing expense
    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateExpense(@PathVariable Integer id, @RequestBody Expense updatedExpense) {
        String response = expenseService.updateExpense(id, updatedExpense);
        return ResponseEntity.ok(response);
    }

    // Delete an expense by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer id) {
        String response = expenseService.deleteExpense(id);
        return ResponseEntity.ok(response);
    }

    // Get all expenses for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Integer userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }
}

