package com.fastcampus.jpa.bookmanager.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Builder
//@Table(name = "user_legacy")// 테이블 이름 변경 하지만
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Table
public class User {
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
    private LocalDateTime createdAt;
    @Column(insertable = false)// insert시에 생략함
    private LocalDateTime updatedAt;

    @Transient // db에 반영하지 않음 객체에서 따로 쓰기위한용도
    private String testData;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;
}
