package ru.netology.hwORM.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.hwORM.models.People;
import ru.netology.hwORM.models.Persons;

import java.util.List;
import java.util.Optional;

public interface CustomDBRepository extends JpaRepository<Persons, Long> {
    @Query("SELECT p FROM Persons p WHERE p.people.name = :name AND p.people.surname = :surname AND p.people.age = :age")
    Optional<Persons> findByPeople(@Param("name") String name, @Param("surname") String surname, @Param("age") int age);

    @Query("SELECT p FROM Persons p WHERE p.cityOfLiving = :cityOfLiving")
    List<Persons> findByCityOfLiving(@Param("cityOfLiving") String cityOfLiving);

    @Query("SELECT p FROM Persons p WHERE p.people.age < :age")
    List<Persons> findByPeople_AgeLessThanOrderByPeople_AgeAsc(@Param("age") int age, Sort sort);

    @Query("SELECT p FROM Persons p WHERE p.people.name = :name AND p.people.surname = :surname")
    Optional<Persons> findByPeople_NameAndPeople_Surname(@Param("name") String name, @Param("surname") String surname);

}
