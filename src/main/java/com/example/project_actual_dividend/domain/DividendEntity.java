package com.example.project_actual_dividend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "dividend")
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"companyId", "date"}
        )
    }
)
public class DividendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;
    private LocalDateTime date;
    private String dividend;
}
