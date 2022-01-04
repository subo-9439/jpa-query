package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private EntityManager entityManager;


    @Test
    @Transactional //3
    void crud() { // create , read , update , delete
        //1
        //이름역순 정리
        //List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        //id 1, 3, 5 select
        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

        //2
//        User user1 = new User("jack","jdbs@cfasdf.com");
//        User user2 = new User("stteve", "dsafasdf@dsfad.com");
//
//        userRepository.saveAll(Lists.newArrayList(user1,user2));
//        //userRepository.save(user1);
//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);

//        //3
//        User user = userRepository.getOne(1L);
//        System.out.println(user);
//        //또는 @Transactionl 없애고 Optional<User> user = userRepository.findById(1L).orElse(null);

//        //4 insert
//        userRepository.save(new User("new martin", "sdfad@naver.com"));
//        userRepository.flush();
////        userRepository.saveAndFlush(new User("new martin", "sdfad@naver.com"));
//
//        userRepository.findAll().forEach(System.out::println);

//        //5
//        long count = userRepository.count();
//        System.out.println(count);

//        //6
//        boolean exists = userRepository.existsById(1L);
//        System.out.println(exists);

//        //7
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

//        //8 select하고 그거를 삭제함
//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L,3L)));
//
//        userRepository.findAll().forEach(System.out::println);
//         deleteAllInBatch select없이 삭제함 delete from user

//        //9 paging 하는법
//        Page<User> users = userRepository.findAll(PageRequest.of(1,3));
//        System.out.println("pages : " + users);
//        System.out.println("totalElements : " + users.getTotalElements());
//        System.out.println("totalPages : " + users.getTotalPages());
//        System.out.println("numberOfElements " + users.getNumberOfElements());
//        System.out.println("sort : "+ users.getSort());
//        System.out.println("size : "+ users.getSize());

//        users.getContent().forEach(System.out::println);

//        //10 QueryByExample(QueryMatcher) name은 무시하고 email만 일치하는 사람 두명받아옴
//        ////matcher 생성없이 Example<User> example  = Example.of만 사용하면 정확하게 일치하는 데이터만 가능
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")
//                .withMatcher("email",endsWith());
//        Example<User> example = Example.of(new User("ma", "fastcampus.com"),matcher);
//        userRepository.findAll(example).forEach(System.out::println);
//        //10.5
//        User user = new User();
//        user.setEmail("slow");
//        ExampleMatcher matcher1 = ExampleMatcher.matching().withMatcher("email", contains());
//        Example<User> example1 = Example.of(user,matcher);

        //11 update에 대한 이해 SimpleJpaRepository.java save가 insert와 update가 구현되어있음
//        userRepository.save(new User("david", " david@fastcampus@.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);

    }

    @Test
    void select(){
//        System.out.println(userRepository.findByName("martin"));
        //다 같은 select 구문
        /*
        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
        */
        //find(Entity명)Email 안해도 작동하긴함
        /*
        System.out.println("findSometthingByEmail :" + userRepository.findSomethingByEmail("martin@fastcampus.com"));
//        System.out.println("findByByName :" + userRepository.findByByName("martin@fastcampus.com"));

        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));
        //위와 같은 결과가나옴
//        System.out.println("findlast1ByName : " + userRepository.findLast1ByName("martin"));

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com","martin"));
        System.out.println("findByOrAndName : " + userRepository.findByEmailOrName("martin@fastcampus.com","denis"));
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqaul : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        //Between 어제와 오늘 사이에 만든
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L),LocalDateTime.now().plusDays(1L  )));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L,3L));
        */

        //3
        System.out.println("findByIdIsNotNull : "+ userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty : "+ userRepository.findByAddressIsNotEmpty());
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin","dennis")));

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
        //findByNameLike("%art%"); mar%, %tin 가독성이 별로여서 contains를 씀
    }

    @Test
    void pagingAndSortingTest(){
        //sort
        //숫자 1 생략해도 같은 의미
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));  제기능을 못함 위처럼해야 제기능이됨
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
        //Sort 클래스 이용해서 정렬하기
        //Order 클래스 직접 임포팅해줘여함
        System.out.println("findFirstByNameWithSortParam : " + userRepository.findFirstByName("martin",Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin", Sort.by(Order.desc("id"),Order.asc("email"))));
        System.out.println("findFirstByNameWithSortParamsMethod:" + userRepository.findFirstByName("martin",getSort()));

        //page 페이지는 제로베이스 인덱스라 0페이지부터 보여준다.
        System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(0,1,Sort.by(Order.desc("id")))));
        //페이지의 내용
        System.out.println("findByNameWithPaging내용 : " + userRepository.findByName("martin", PageRequest.of(0,1,Sort.by(Order.desc("id")))).getContent());
        //2
        System.out.println("findByNameWithPaging내용 : " + userRepository.findByName("martin", PageRequest.of(0,1,Sort.by(Order.desc("id")))).getTotalElements());

    }

    private Sort getSort(){
        return Sort.by(
                Order.desc("id"),
                Order.asc("email"),
                Order.desc("createdAt"),
                Order.asc("updatedAt")
        );
    }

    @Test
    void insertAndUpdateTest(){
        User user = new User();
        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");

        userRepository.save(user);
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrrrrrrtin");
        userRepository.save(user2);
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }
    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");


        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    }

    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("martin22");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("martin-new@fastcampus.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("martint-new-new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);
        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@fastcampus.com").getId());
        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser() : " + userHistoryRepository.findAll().get(0).getUser());
    }
    @Test
    void embedTest(){
        userRepository.findAll().forEach(System.out::println);
        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로 364 미왕빌딩", "06241"));
        user.setCompanyAddress(new Address("서울시", "성동구", "성수이로 113 제강빌딩", "01234"));
        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());
        userRepository.save(user2);
        entityManager.clear();


        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));
    }
}