package pl.pijok.tpo6_bj_s24322.book.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.annotations.Table;
import pl.pijok.tpo6_bj_s24322.lib.Entity;

@Table(tableName = "books")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity extends Entity {

    @Column(name = "book_id", ignore = true)
    private Integer bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

}
