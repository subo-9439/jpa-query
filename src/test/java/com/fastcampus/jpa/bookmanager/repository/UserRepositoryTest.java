package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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
        userRepository.save(new User("david", " david@fastcampus@.com"));

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
        System.out.println("findByAddressIsNotEmpty : "+ userRepository.findByAddressIsNotEmpty());
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin","dennis")));

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
        //findByNameLike("%art%"); mar%, %tin 가독성이 별로여서 contains를 씀
    }

    @Test
    void pagingAndSortingTest(){
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
    }
}