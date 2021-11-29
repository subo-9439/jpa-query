package com.fastcampus.jpa.bookmanager.domain;


import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.MyEntityListener;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(value =  UserEntityListener.class)
@Builder
//@Table(name = "user_legacy")// 테이블 이름 변경 하지만
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Table
public class User extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    //기본 디폴트가 ordinal이라 오류가 날수 있음
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    //임의로 mapping
    //    @Column(name = "creatAt", nullable = false) not null <<
    //  @Column(unique = true) unique한 속성을 가지게됨
    @Column(updatable = false)// update시에 생략함

    @CreatedDate //jpad에서 제공해주는 기본 Listener
    private LocalDateTime createdAt;

//    @LastModifiedDate //jpa에서 제공해주는 기본Listener
//    private LocalDateTime updatedAt;
//
//    @Transient // db에 반영하지 않음 객체에서 따로 쓰기위한용도
//    private String testData;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    //Persist => insert
    //Update => merge
    //Remove => delte
    //Load => select
//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//        System.out.println(">>> prePersist");
//    }
//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>> postPersist");
//    }
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//        System.out.println(">>> preUpdate");
//    }
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> postUpdate");
//    }
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> preRemove");
//    }
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> postRemove");
//    }
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>> postLoad");
//    }
}
