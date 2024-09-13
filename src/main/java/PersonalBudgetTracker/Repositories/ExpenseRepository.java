package PersonalBudgetTracker.Repositories;

import PersonalBudgetTracker.Models.Expense;
import PersonalBudgetTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUserId(Integer userId);

    List<Expense> findByUser(User user);
}
