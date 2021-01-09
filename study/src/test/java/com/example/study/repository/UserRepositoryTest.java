package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired //dependency injection(DI) : spring이 의존성을 주입시켜줌
               //instance를 생성하지 않아도 자동적으로 생성이 되어 사용됨 -> singleton
    private UserRepository userRepository;

    @Test
    public void create()
    {
        User user=new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser=userRepository.save(user);
        System.out.println("newUser : "+newUser);
    }

    @Test
    public void read()
    {
        Optional<User> user=userRepository.findById(2L); //있을수도 있고 없을 수도 있음

        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
        }); //if data is present~
    }

    public void update()
    {

    }

    public void delete()
    {

    }
}
