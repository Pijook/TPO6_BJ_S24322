package pl.pijok.tpo6_bj_s24322.book.entity;

import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.annotations.Table;

@Table(tableName = "Author")
public class AuthorEntity {

    @Column(name = "name")
    private String name;

}
