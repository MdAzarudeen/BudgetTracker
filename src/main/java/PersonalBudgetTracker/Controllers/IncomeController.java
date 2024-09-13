package PersonalBudgetTracker.Controllers;

import PersonalBudgetTracker.Models.Income;
import PersonalBudgetTracker.Services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    // Create a new income entry
    @PostMapping("/create")
    public ResponseEntity<String> createIncome(@RequestBody Income income) {
        String response = incomeService.saveIncome(income);
        return ResponseEntity.ok(response);
    }

    // Update an existing income entry
    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateIncome(@PathVariable Integer id, @RequestBody Income updatedIncome) {
        String response = incomeService.updateIncome(id, updatedIncome);
        return ResponseEntity.ok(response);
    }

    // Delete an income entry by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteIncome(@PathVariable Integer id) {
        String response = incomeService.deleteIncome(id);
        return ResponseEntity.ok(response);
    }

    // Get all income entries for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getIncomesByUserId(@PathVariable Integer userId) {
        List<Income> incomes = incomeService.getIncomesByUserId(userId);
        return ResponseEntity.ok(incomes);
    }
}
