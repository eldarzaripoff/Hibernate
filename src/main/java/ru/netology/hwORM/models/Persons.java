package ru.netology.hwORM.models;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "PERSONS", schema = "netology")
@NoArgsConstructor
@AllArgsConstructor
public class Persons {
    @EmbeddedId
    private People people;
    @Column(length = 12)
    private String phone_number;
    @Column
    private String cityOfLiving;

}
