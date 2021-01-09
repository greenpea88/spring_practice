package com.fastcampus.javaallinone.project3.mycontact.controller;


import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.mycontact.exception.RenameNotPermittedException;
import com.fastcampus.javaallinone.project3.mycontact.exception.dto.ErrorResponse;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import com.fastcampus.javaallinone.project3.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;
//    @Autowired
//    private PersonRepository personRepository;

    @GetMapping
    public Page<Person> getAll(@PageableDefault Pageable pageable) {
        return personService.getAll(pageable);
    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{id}")
//    @RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonDto personDto) {
        personService.put(personDto);

//        log.info("person -> {}" ,personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);

//        log.info("person -> {}" ,personRepository.findAll());
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name) {
//        try {
        personService.modify(id, name);
//        }catch (RuntimeException ex){
//            log.error(ex.getMessage(),ex);
//        }

//        log.info("person -> {}" ,personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);

//        log.info("person -> {}" ,personRepository.findAll());

//        return personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(id));
    }

//    @ExceptionHandler(value = RenameNotPermittedException.class)
//    public ResponseEntity<ErrorResponse> handleRenameNoPermittedException(RenameNotPermittedException ex)
//    {
//        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = PersonNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(PersonNotFoundException ex)
//    {
//        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException ex)
//    {
//        log.error("server error : {}",ex.getMessage());
//        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"unknown error is occurred"),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
