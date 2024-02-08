package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO){
        Query query = em.createNativeQuery("insert into board_tb(author, title, content) values (?, ? ,?)");

        query.setParameter(1, requestDTO.getAuthor());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());

        query.executeUpdate();
    }

    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc",Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }
}
