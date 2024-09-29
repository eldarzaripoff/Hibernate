package ru.netology.hwORM.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.DBRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final DBRepository dbRepository;

    public RoleController(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Secured("ROLE_READ")
    @GetMapping("/read")
    public ResponseEntity<Persons> readPerson(@RequestParam String name,
                                              @RequestParam String surname,
                                              @RequestParam int age) {
        Optional<Persons> foundPerson = dbRepository.findByPeople(name, surname, age);
        if (foundPerson.isPresent()) {
            Persons person = foundPerson.get();
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RolesAllowed("WRITE")
    @PostMapping("/write")
    public ResponseEntity<Persons> writeMethod(@RequestBody Persons persons) {
        Persons createdPerson = dbRepository.save(persons);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    @GetMapping("/write-or-delete")
    public ResponseEntity<List<Persons>> writeOrDeleteMethod(@RequestParam String cityOfLiving) {
        List<Persons> foundPersons = dbRepository.findByCityOfLiving(cityOfLiving);
        if (!foundPersons.isEmpty()) {
            return new ResponseEntity<>(foundPersons, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Persons> userMethod(@RequestParam String username, @RequestParam String surname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals(username)) {
            Optional<Persons> foundPerson = dbRepository.findByPeople_NameAndPeople_Surname(username, surname);
            if (foundPerson.isPresent()) {
                Persons person = foundPerson.get();
                return new ResponseEntity<>(person, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }
    }
}
