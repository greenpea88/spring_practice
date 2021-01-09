package com.fastcampus.javaallinone.project3.mycontact.domain;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode
@Data
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

//    @NonNull
//    @Min(1)
//    private int age;

    private String hobby;

//    @NonNull
//    @NotEmpty
//    @Column(nullable = false)
//    private String bloodType;

    private String address;
    private String job;

    @Valid
    @Embedded
    private Birthday birthday;

    //    @ToString.Exclude
    private String phoneNumber;

//    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
//    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
//    @ToString.Exclude
//    private Block block;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDto personDto) {
//        if(personDto.getAge()!=0)
//        {
//            this.setAge(personDto.getAge());
//        }

        if (!StringUtils.isEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }

//        if(!StringUtils.isEmpty(personDto.getBloodType()))
//        {
//            this.setBloodType(personDto.getBloodType());
//        }

        if (!StringUtils.isEmpty(personDto.getAddress())) {
            this.setAddress(personDto.getAddress());
        }

        if (!StringUtils.isEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }

        if (!StringUtils.isEmpty(personDto.getPhoneNumber())) {
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

        if (personDto.getBirthday() != null) {
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }

    public Integer getAge() {
        if (this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        } else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(
                this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}
