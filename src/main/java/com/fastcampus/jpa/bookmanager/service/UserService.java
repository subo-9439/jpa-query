package com.fastcampus.jpa.bookmanager.service;


import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");

//        userRepository.save(user);
        entityManager.persist(user);
        entityManager.detach(user);

        user.setName("newUserAfterPersist");
        //준영속상태일땐 merge로 데이터 반영을 해준다.
        entityManager.merge(user);
//        entityManager.flush();
//        entityManager.clear();

        User user1 = userRepository.findById(1L).get();
//        entityManager.remove(user1);
    }
}
