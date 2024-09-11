package ru.netology.hwORM.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.hwORM.models.People;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.DBRepository;
import ru.netology.hwORM.requests.UpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private final DBRepository dbRepository;

    public Controller(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @PostMapping("/persons/create")
    public ResponseEntity<Persons> createPerson(@RequestBody Persons persons) {
        Persons createdPerson = dbRepository.save(persons);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping("/persons/get")
    public ResponseEntity<Persons> getPerson(@RequestParam String name,
                                             @RequestParam String surname,
                                             @RequestParam int age) {
        Optional<Persons> foundPerson = dbRepository.findByPeople(People.builder().name(name).surname(surname).age(age).build());
        if (foundPerson.isPresent()) {
            Persons person = foundPerson.get();
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/persons/update")
    public ResponseEntity<Persons> patchPerson(@RequestParam String name,
                                               @RequestParam String surname,
                                               @RequestParam int age,
                                               @RequestBody UpdateRequest updateRequest) {
        Optional<Persons> foundPerson = dbRepository.findByPeople(People.builder().name(name).surname(surname).age(age).build());
        if (foundPerson.isPresent()) {
            Persons updatedPerson = foundPerson.get();
            updatedPerson.setPhone_number(updateRequest.getPhoneNumber());
            updatedPerson.setCityOfLiving(updateRequest.getCity());
            dbRepository.save(updatedPerson);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persons/delete")
    public ResponseEntity<Persons> deletePerson(@RequestParam String name,
                                                @RequestParam String surname,
                                                @RequestParam int age) {
        Optional<Persons> foundPerson = dbRepository.findByPeople(People.builder().name(name).surname(surname).age(age).build());
        if (foundPerson.isPresent()) {
            Persons updatedPerson = foundPerson.get();
            dbRepository.delete(updatedPerson);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/persons/by-city")
    public ResponseEntity<List<Persons>> getPersonsByCity(@RequestParam String cityOfLiving) {
        List<Persons> foundPersons = dbRepository.findByCityOfLiving(cityOfLiving);
        if (!foundPersons.isEmpty()) {
            return new ResponseEntity<>(foundPersons, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/persons/by-age")
    public ResponseEntity<List<Persons>> getPersonsByAge(@RequestParam int age) {
        List<Persons> foundPersons = dbRepository.findByPeople_AgeLessThanOrderByPeople_AgeAsc(age);
        if (!foundPersons.isEmpty()) {
            return new ResponseEntity<>(foundPersons, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/persons/by-name-surname")
    public ResponseEntity<Persons> getPersonByNameAndSurname(@RequestParam String name,
                                                             @RequestParam String surname) {
        Optional<Persons> foundPerson = dbRepository.findByPeople_NameAndPeople_Surname(name, surname);
        if (foundPerson.isPresent()) {
            Persons person = foundPerson.get();
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
