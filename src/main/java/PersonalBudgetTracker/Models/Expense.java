package PersonalBudgetTracker.Models;

import PersonalBudgetTracker.Enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="expense")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String tag;
}
