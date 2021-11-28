package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User,Long> {

    //1
//    List <User> findByName(String name);
//    User findByEmail(String name);
//    User getByEmail(String email);
//    User readByEmail(String email);
//    User queryByEmail(String email);
//    User searchByEmail(String email);
//    User streamByEmail(String email);
//    //find(Entity명)Email
//    User findUserByEmail(String email);
//    //find(Entity명)Email 안해도 작동하긴함
//    User findSomethingByEmail(String email);

    //2
    //명명법 오류로 인한 런타임 오류
    //    User findByByName(String email);

//    List<User> findFirst1ByName(String name);
//    List<User> findTop1ByName(String name);
//
//    //인식되지 않음 find1이랑 같은 쿼리 결과가나옴
////    List<User> findLast1ByName(String name);
//
//    //And ,or where의 의미이다
//    List<User> findByEmailAndName(String email, String name);
//    List<User> findByEmailOrName(String email, String name);
//    //크다(>) After 작다(<) Before
//    List<User> findByCreatedAtAfter(LocalDateTime yesterday);
//    List<User> findByIdAfter(Long id);
//    //GreaterThan equals 를 쓸 수있음 (Before After은 = 을 포함하지않음으로 범용성이 더 높음)
//    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
//    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
//    //Between
//    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
//    List<User> findByIdBetween(Long id1, Long id2);

    //3
    List<User> findByIdIsNotNull();
//    List<User> findByIdIsNotEmpty(); 객체명에 맞게 해줘야함 >> Address
    List<User> findByAddressIsNotEmpty(); // collection 객체 자체가 empty이다

    List<User> findByNameIn(List<String> names);
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
//    List<User> findByName(String name); findUserByNameIs, findUserByNameEquals 같은 결과지만 코딩 가독성을 위해 쓰기도한다.
    List<User> findTop1ByName(String name);
    //sort
    //정순이라면 Asc
    List<User> findTop1ByNameOrderByIdDesc(String name);
    //Top 이랑 First는 같은 의미 Order를 나타내는 By키워드는  And나 or 없이 그대로 이어서 작성
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);
    //Sort 클래스 이용하기
    List<User> findFirstByName(String name, Sort sort);

    //page
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String,Object> findRawRecord();

}
