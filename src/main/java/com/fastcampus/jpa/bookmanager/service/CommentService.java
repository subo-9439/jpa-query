package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import com.fastcampus.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고예요");

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for(Comment comment : comments) {
            comment.setComment("별로예요");
//            commentRepository.save(comment);
        }
    }

    //insert는 영속화 과정이 없기 때문에 별도로 save를 통해서 해당 entity를 영속화 해줘야한다.
    //update문은 save가 없더라도 @Trasactionl 어노테이션이 있다면 영속화 대상이므로 더티 체크가 일어난다.
    //findbyId 영속화대상
    //하지만 매번 더티체킹을하면 성능이슈가 일어나므로 readOnly = true를 달아준다.
    //대용량 데이터를 조회할때 아주 큰 장점이다
    //스프링 프레임워크가 하이버네이트 세션 플러시 모드를 manual로 설정시켜 강제로 플러시를 호출시키지 않는 한 플러시가 일어나지 않는다.
    //따라서 트랜잭션을 커밋하더라도 영속성 컨텍스트가 플러시 되지 않아서 엔티티의 등록, 수정 ,삭제가 동작하지 않고,
    //읽기 전용으로, 영속성 컨텍스트는 변경 감지를 위한 스냅샷을 보관힞 ㅏㅇㄶ으므로 성능이 향상된다.
    //https://willseungh0.tistory.com/75
    @Transactional
    public void insertSomething() {
//        Comment comment = new Comment();
        Comment comment = commentRepository.findById(1L).get();
        comment.setComment("이건뭐죠?");
        commentRepository.save(comment);
    }
}
