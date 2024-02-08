package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request,@RequestParam(defaultValue = "0")int page) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {

        Board board = boardRepository.findById(id);
        request.setAttribute("board", board);

        return "board/updateForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request){


        System.out.println(requestDTO);
        if (requestDTO.getTitle().length() > 20){
            request.setAttribute("status", 400);
            request.setAttribute("msg", "title의 길이가 20자를 초과했습니다.");
            return "error/40x";
        } else if (requestDTO.getContent().length() > 20){
            request.setAttribute("status", 400);
            request.setAttribute("msg", "content의 길이가 20자를 초과했습니다.");
            return "error/40x";
        }

        boardRepository.save(requestDTO);

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){

        boardRepository.update(requestDTO,id);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){

        boardRepository.deleteById(id);

        return "redirect:/";
    }
}
