package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setEmail("matifadsf@faste.com");
        user.setName("adsf");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

//        User user1 = new User(null,"sdaf", "asdfsdaf2@sdaf.com",LocalDateTime.now(),LocalDateTime.now(),null);
//        User user2 = new User(null,"asdfdas","asdfsadf@adsf.com",LocalDateTime.now(),LocalDateTime.now(),null);

        User user3 = User.builder()
                .name("subo")
                .email("subo@naver.com")
                .build();
        System.out.println(">>>"+user.toString());
    }
}
