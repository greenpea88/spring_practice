package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.mycontact.exception.RenameNotPermittedException;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
////    private BlockRepository blockRepository;

//    public List<Person> getPeopleExcludeBlocks()
//    {
//        List<Person> people=personRepository.findAll();
////        List<Block> blocks=blockRepository.findAll();
////        List<String> blockNames=blocks.stream().map(Block::getName).collect(Collectors.toList());
//
////        return people.stream().filter(person -> !blockNames.contains(person.getName())).collect(Collectors.toList());
//        //person과 block이 relation되었기 때문에 이름으로 가져올 필요 없어짐
//
////        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());
//        return personRepository.findByBlockIsNull();
//    }

    public Page<Person> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people=personRepository.findAll();

//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
        return personRepository.findByName("martin");
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
//        Person person=personRepository.findById(id).get();

        return personRepository.findById(id).orElse(null);

//        log.info("person : {}",person);

    }

    @Transactional
    public void put(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
//        Person person=personRepository.findById(id).orElseThrow(()->new RuntimeException("id is not exist"));
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if (!person.getName().equals(personDto.getName())) {
//            throw new RuntimeException("name is not correct");
            throw new RenameNotPermittedException();
        }

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
//        Person person=personRepository.findById(id).orElseThrow(()->new RuntimeException("id is not exist"));
//        Person person=personRepository.findById(id).orElseThrow(()->new PersonNotFoundException());
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
//        Person person=personRepository.findById(id).get();

        person.setName(name);

        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
//        Person person=personRepository.findById(id).orElseThrow(()->new RuntimeException("id is not exist"));
//        personRepository.delete(person);

//        personRepository.deleteById(id);

//        Person person=personRepository.findById(id).orElseThrow(()->new RuntimeException("id is not exist"));
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setDeleted(true);

        personRepository.save(person);
    }
}
