package PersonalBudgetTracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    private String profileName;
   // @Column(nullable = false, unique = true)
    private Integer phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Budget> budgets;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Income income;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;

}
