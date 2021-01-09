package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

//    List<Person> findByBlockIsNull(); //block이 되지 않은 경우

//    List<Person> findByBloodType(String bloodType);

    //    @Query(value = "select person from Person person where person.birthday.monthOfBirthday=?1 and person.birthday.dayOfBirthday=?2")
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday "
//            +"and person.birthday.dayOfBirthday = :dayOfBirthday"
    )
//    @Query(value = "select * from person where month_of_birthday = :monthOfBirthday and day_of_birthday = :dayOfBirthday",nativeQuery = true)
    List<Person> findByMonthOfBirthday(
            @Param("monthOfBirthday") int monthOfBirthday
//            ,@Param("dayOfBirthday") int dayOfBirthday
    );

    @Query(value = "select * from person where person.deleted=true", nativeQuery = true)
    List<Person> findPeopleDeleted();

}
