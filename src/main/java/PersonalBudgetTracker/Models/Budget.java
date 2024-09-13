package PersonalBudgetTracker.Models;

import PersonalBudgetTracker.Enums.BudgetPeriod;
import PersonalBudgetTracker.Enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name="budget")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    private BudgetPeriod period;

    @Column(nullable = false)
    private Integer budgetLimit;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Expense> expenses;


}
