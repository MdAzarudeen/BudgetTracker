package PersonalBudgetTracker.Repositories;

import PersonalBudgetTracker.Models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import PersonalBudgetTracker.Models.User;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Integer> {
    Income findByUserId(Integer userId);

    List<Income> findByUser(User user);


}
