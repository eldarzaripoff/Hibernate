package ru.netology.hwORM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.hwORM.models.People;
import ru.netology.hwORM.models.Persons;

import java.util.List;
import java.util.Optional;

public interface CustomDBRepository extends JpaRepository<Persons, Long> {
    Optional<Persons> findByPeople(People people);

    List<Persons> findByCityOfLiving(String cityOfLiving);

    List<Persons> findByPeople_AgeLessThanOrderByPeople_AgeAsc(int age);

    Optional<Persons> findByPeople_NameAndPeople_Surname(String name, String surname);

}
