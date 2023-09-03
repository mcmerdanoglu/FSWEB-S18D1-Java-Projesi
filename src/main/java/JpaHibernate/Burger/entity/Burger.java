package JpaHibernate.Burger.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="burger", schema = "springjpa")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    //@Positive => Id herzaman 0dan büyük olmalı anlamında.
    private int id;

    @Column(name="name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "price")
    @DecimalMin("50")
    private double price;

    @Column(name= "is_vegan")
    private boolean isVegan;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Column(name = "contents")
    private List<String> contents;
}
