package PersonalBudgetTracker.Controllers;

import PersonalBudgetTracker.Models.Budget;
import PersonalBudgetTracker.Services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // Create a new budget
    @PostMapping("/create")
    public ResponseEntity<String> createBudget(@RequestBody Budget budget) {
        String response = budgetService.saveBudget(budget);
        return ResponseEntity.ok(response);
    }

    // Update an existing budget
    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateBudget(@PathVariable Integer id, @RequestBody Budget updatedBudget) {
        String response = budgetService.updateBudget(id, updatedBudget);
        return ResponseEntity.ok(response);
    }

    // Delete a budget by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteBudget(@PathVariable Integer id) {
        String response = budgetService.deleteBudget(id);
        return ResponseEntity.ok(response);
    }

    // Retrieve all budgets
    @GetMapping("/all")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetService.getAllBudgets();
        return ResponseEntity.ok(budgets);
    }

    // Retrieve a budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Integer id) {
        Budget budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }
}
