package PersonalBudgetTracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="income")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private LocalDate date;

    private String source;

    private String description;

    private String tag;

}
