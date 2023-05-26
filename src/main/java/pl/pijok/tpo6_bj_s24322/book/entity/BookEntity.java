package pl.pijok.tpo6_bj_s24322.book.entity;

import lombok.NoArgsConstructor;
import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.annotations.Table;
import pl.pijok.tpo6_bj_s24322.lib.Entity;

@Table(tableName = "books")
@NoArgsConstructor
public class BookEntity extends Entity {

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

}
