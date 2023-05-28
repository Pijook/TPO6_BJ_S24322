package pl.pijok.tpo6_bj_s24322.book.dto;

import pl.pijok.tpo6_bj_s24322.lib.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDtoMapper extends Mapper {

    @Override
    public List map(ResultSet set) {
        List<BookDto> dtos = new ArrayList<>();
        try{
            while (set.next()) {
                BookDto dto = new BookDto(
                        set.getInt("book_id"),
                        set.getString("title"),
                        set.getString("author"),
                        set.getString("description"),
                        set.getString("isbn"),
                        new Date(set.getDate("publishDate").getTime()),
                        set.getInt("rating"),
                        new Date(set.getDate("creationDate").getTime())
                );

                dtos.add(dto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dtos;
    }
}
