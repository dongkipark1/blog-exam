package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String author;
        private String title;
        private String content;

    }
}
