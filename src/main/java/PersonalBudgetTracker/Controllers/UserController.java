package PersonalBudgetTracker.Controllers;

import PersonalBudgetTracker.Models.Budget;
import PersonalBudgetTracker.Models.Income;
import PersonalBudgetTracker.Models.User;
import PersonalBudgetTracker.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userService.saveUser(user);
        return ResponseEntity.ok(response);
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        String response = userService.deleteUserById(id);
        return ResponseEntity.ok(response);
    }

    // Update user's income
    @PutMapping("/{id}/income")
    public ResponseEntity<String> updateIncome(@PathVariable Integer id, @RequestBody Income newIncome) {
        String response = userService.updateIncome(id, newIncome);
        return ResponseEntity.ok(response);
    }

    // Get all budgets for a user
    @GetMapping("/{id}/budgets")
    public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable Integer id) {
        List<Budget> budgets = userService.getBudgetsByUserId(id);
        return ResponseEntity.ok(budgets);
    }
}
