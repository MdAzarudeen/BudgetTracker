package PersonalBudgetTracker.Services;

import PersonalBudgetTracker.Exceptions.UserAlreadyExistsException;
import PersonalBudgetTracker.Exceptions.UserNotFoundException;
import PersonalBudgetTracker.Models.Budget;
import PersonalBudgetTracker.Models.Income;
import PersonalBudgetTracker.Repositories.UserRepository;

import PersonalBudgetTracker.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Create a new user, check if the user already exists
    public String saveUser(User user) {
        boolean userExistsByEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExistsByEmail) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }

        // Check if a user with the same phone number exists
        boolean userExistsByPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent();
        if (userExistsByPhoneNumber) {
            throw new UserAlreadyExistsException("User with phone number " + user.getPhoneNumber() + " already exists.");
        }

        // Save the new user
        userRepository.save(user);
        return "User has been added successfully.";
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }

    // Delete a user by ID, throw exception if not found
    public String deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " has been deleted successfully.";
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }

    // Update user's income
    public String updateIncome(Integer userId, Income newIncome) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setIncome(newIncome);
            userRepository.save(user);
            return "Income has been updated successfully.";
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }

    // Get all budgets for a user
    public List<Budget> getBudgetsByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getBudgets();
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }

}
