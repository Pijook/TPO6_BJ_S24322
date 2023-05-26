package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.*;
import pl.pijok.tpo6_bj_s24322.lib.Dto;

@Getter
public class BookDto extends Dto {

    public BookDto() {
    }

    private int bookId;
    private String title;
    private String author;
    private String description;

}
