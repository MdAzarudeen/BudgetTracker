package PersonalBudgetTracker.Services;

import PersonalBudgetTracker.Exceptions.IncomeNotFoundException;
import PersonalBudgetTracker.Exceptions.UserNotFoundException;
import PersonalBudgetTracker.Models.Income;
import PersonalBudgetTracker.Repositories.IncomeRepository;
import PersonalBudgetTracker.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PersonalBudgetTracker.Models.User;


import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new income entry
    public String saveIncome(Income income) {
        incomeRepository.save(income);
        return "Income has been added successfully.";
    }

    // Update an existing income entry
    public String updateIncome(Integer id, Income updatedIncome) {
        Optional<Income> existingIncome = incomeRepository.findById(id);
        if (existingIncome.isPresent()) {
            Income income = existingIncome.get();
            income.setAmount(updatedIncome.getAmount());
            income.setDate(updatedIncome.getDate());
            income.setSource(updatedIncome.getSource());
            income.setDescription(updatedIncome.getDescription());
            income.setTag(updatedIncome.getTag());
            incomeRepository.save(income);
            return "Income has been updated successfully.";
        } else {
            throw new IncomeNotFoundException("Income with ID " + id + " not found.");
        }
    }

    // Delete an income entry by ID
    public String deleteIncome(Integer id) {
        if (incomeRepository.existsById(id)) {
            incomeRepository.deleteById(id);
            return "Income with ID " + id + " has been deleted successfully.";
        } else {
            throw new IncomeNotFoundException("Income with ID " + id + " not found.");
        }
    }


    // Retrieve all income entries for a specific user
    public List<Income> getIncomesByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return incomeRepository.findByUser(user.get());
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
