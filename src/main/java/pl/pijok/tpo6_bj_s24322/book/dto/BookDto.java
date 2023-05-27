package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.*;
import pl.pijok.tpo6_bj_s24322.lib.Dto;

@Getter
@ToString
public class BookDto extends Dto {

    public BookDto() {
    }

    private Integer bookId;
    private String title;
    private String author;
    private String description;

}
