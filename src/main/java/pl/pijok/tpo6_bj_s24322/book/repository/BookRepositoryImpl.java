package pl.pijok.tpo6_bj_s24322.book.repository;

import jakarta.inject.Inject;
import pl.pijok.tpo6_bj_s24322.DataSource;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;
import pl.pijok.tpo6_bj_s24322.book.entity.BookEntity;
import pl.pijok.tpo6_bj_s24322.lib.Mapper;
import pl.pijok.tpo6_bj_s24322.lib.Repository;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl extends Repository implements BookRepository {

    private static final BookEntity entity = new BookEntity();

    private static final String INIT_TABLES = "CREATE TABLE IF NOT EXISTS pjatk.books (" +
            "book_id serial primary key," +
            "title VARCHAR(32) not null," +
            "author VARCHAR(64) not null," +
            "description VARCHAR(512)" +
            ");";

    @Inject
    private Mapper mapper;

    @Override
    public List<BookDto> getBooks() {
        String sql = createSelectSql(SearchCriteria.builder().build(), entity);
        return executeBookDtoQuery(sql);
    }

    @Override
    public Optional<BookDto> findBook(long bookId) {
        String sql = createSelectSql(SearchCriteria.builder().build(), entity);
        Connection connection = DataSource.getConnection();

        ResultSet resultSet = null;
        try{
            resultSet = connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        List<BookDto> temp = mapper.mapResultSet(resultSet, entity, BookDto.class);;
        return temp.size() > 0 ? Optional.of(temp.get(0)) : Optional.empty();
    }

    @Override
    public List<BookDto> searchBooks(SearchCriteria criteria) {
        String sql = createSelectSql(criteria, entity);
        return executeBookDtoQuery(sql);
    }

    @Override
    public void initTable() {
        Connection connection = DataSource.getConnection();
        try {
            connection.prepareStatement(INIT_TABLES).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<BookDto> executeBookDtoQuery(String sql) {
        Connection connection = DataSource.getConnection();

        ResultSet resultSet = null;
        try {
            resultSet = connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return mapper.mapResultSet(resultSet, entity, BookDto.class);
    }
}
