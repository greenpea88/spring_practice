package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void findByName() {
        List<Person> people = personRepository.findByName("tony");
        assertThat(people.size()).isEqualTo(1);

        Person person = people.get(0);
        assertAll(
                () -> assertThat(person.getName()).isEqualTo("tony"),
                () -> assertThat(person.getHobby()).isEqualTo("engineering"),
                () -> assertThat(person.getAddress()).isEqualTo("NYC"),
                () -> assertThat(person.getJob()).isEqualTo("CEO"),
                () -> assertThat(person.getBirthday()).isEqualTo(Birthday.of(LocalDate.of(1991, 7, 10))),
                () -> assertThat(person.getPhoneNumber()).isEqualTo("010-1111-2222"),
                () -> assertThat(person.isDeleted()).isEqualTo(false)
        );
    }

    @Test
    void findByNameDeleted() {
        List<Person> people = personRepository.findByName("andrew");
        assertThat(people.size()).isEqualTo(0);
    }

    @Test
    void findByMonthOfBirthday() {
        List<Person> people = personRepository.findByMonthOfBirthday(7);
        assertThat(people.size()).isEqualTo(2);
        assertAll(
                () -> assertThat(people.get(0).getName()).isEqualTo("david"),
                () -> assertThat(people.get(1).getName()).isEqualTo("tony")
        );
    }

    @Test
    void findPeopleDeleted() {
        List<Person> people = personRepository.findPeopleDeleted();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("andrew");
    }

//    @Test
//    void crud()
//    {
//        Person person=new Person();
//
//        person.setName("john");
////        person.setAge(10);
////        person.setBloodType("A");
//
//        personRepository.save(person);
//
////        List<Person> people=personRepository.findAll();
//
//        List<Person> people=personRepository.findByName("john");
//
//        assertThat(people.size()).isEqualTo(1);
//        assertThat(people.get(0).getName()).isEqualTo("john");
////        assertThat(people.get(0).getAge()).isEqualTo(10);
//
//    }
//
////    @Test
////    void constructorTest()
////    {
////        Person person=new Person("martin",10);
////    }
//
////    @Test
////    void hashCodeAndEquals()
////    {
////        Person person1=new Person("martin",10,"A");
////        Person person2=new Person("martin",10,"A");
////
////        System.out.println(person1.equals(person2));
////        System.out.println(person1.hashCode());
////        System.out.println(person2.hashCode());
////
////        Map<Person,Integer> map=new HashMap<>();
////        map.put(person1,person1.getAge());
////        System.out.println(map);
////        System.out.println(map.get(person2));
////    }
//
////    @Test
////    void findByBloodType()
////    {
//////        givenPerson("martin",10,"A");
//////        givenPerson("david",9,"B");
//////        givenPerson("dennis",8,"O");
//////        givenPerson("sophia",7,"AB");
//////        givenPerson("benny",6,"A");
////
//////        List<Person> result=personRepository.findByBloodType("A");
////
//////        System.out.println(result);
////        assertThat(result.size()).isEqualTo(2);
////        assertThat(result.get(0).getName()).isEqualTo("martin");
////        assertThat(result.get(1).getName()).isEqualTo("benny");
////    }
//
//    @Test
//    void findByBirthdayBetween()
//    {
////        givenPerson("martin",10,"A",LocalDate.of(1991,8,15));
////        givenPerson("david",9,"B",LocalDate.of(1992,7,10));
////        givenPerson("dennis",8,"O",LocalDate.of(1993,1,5));
////        givenPerson("sophia",7,"AB",LocalDate.of(1994,6,30));
////        givenPerson("benny",6,"A",LocalDate.of(1995,8,30));
//
////        List<Person> result=personRepository.findByBirthdayBetween(
////                LocalDate.of(1991,8,1),LocalDate.of(1991,8,31));
//
//        List<Person> result=personRepository.findByMonthOfBirthday(8);
//
////        Person martin=new Person();
////        martin.getAge();
////        martin.isBirthdayToday();
//
////        result.forEach(System.out::println);
//        assertThat(result.size()).isEqualTo(2);
//        assertThat(result.get(0).getName()).isEqualTo("martin");
//        assertThat(result.get(1).getName()).isEqualTo("sophia");
//    }
//
////    private void givenPerson(String name, int age, String bloodType)
////    {
////        givenPerson(name,age,bloodType,null);
////    }
////
////    private void givenPerson(String name, int age, String bloodType, LocalDate birthDay)
////    {
////        Person person=new Person(name,age,bloodType);
//////        person.setBirthday(new Birthday(birthDay.getYear(),birthDay.getMonthValue(),birthDay.getDayOfMonth()));
////        person.setBirthday(new Birthday(birthDay));
////        personRepository.save(person);
////    }

}