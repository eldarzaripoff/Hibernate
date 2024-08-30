package ru.netology.hwORM.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@Entity
@Table(name = "CUSTOMERS", schema = "netology")
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    @Transient
    private int age;

    @Column(length = 12)
    private String phone_number;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders;
}
