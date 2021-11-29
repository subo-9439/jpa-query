package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.MyEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//AuditingENtitiyListener jpa에서 제공해줌
@Entity
//@EntityListeners(value = AuditingEntityListener.class)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue
    private Long Id;

    private Long userId;

    private String name;

    private String email;

//    @CreatedDate //Jpa에서 제공해주는 Listener
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate //Jpa에서 제공해주는 Listener
//    private LocalDateTime updatedAt;
}
