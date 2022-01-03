package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass //상위 Entity의 컬럼으로 사용하겠다
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {

    //columnDefinition에 type을 정해줘야한다. ex)datetime(6)
    //Ejb3Column 클래스 확인 comment 는 주석이라는 뜻
    //현업에선 드문케이스라 원리만 이해하자
    //now(6) 초 미만 최대 6자리까지 표현해줌 7이상은 쓸 수 없음

    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6) ",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6) ", nullable = false)
    private LocalDateTime updatedAt;
}
