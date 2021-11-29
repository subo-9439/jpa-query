package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import com.fastcampus.jpa.bookmanager.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//Autowired하기위해선 Component로 등록해야함
@Component
public class UserEntityListener {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){
        User user = (User) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistoryRepository.save(userHistory);
    }
}
