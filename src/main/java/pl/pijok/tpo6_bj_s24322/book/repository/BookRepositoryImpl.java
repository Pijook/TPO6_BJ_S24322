package pl.pijok.tpo6_bj_s24322.book.repository;

import jakarta.inject.Inject;
import pl.pijok.tpo6_bj_s24322.DataSource;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDtoMapper;
import pl.pijok.tpo6_bj_s24322.book.dto.BookSearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.entity.BookEntity;
import pl.pijok.tpo6_bj_s24322.lib.Mapper;
import pl.pijok.tpo6_bj_s24322.lib.Repository;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl extends Repository implements BookRepository {

    private static final BookEntity entity = new BookEntity(1, "Title", "Author", "Desc", "ISBN", LocalDate.now(), 1, LocalDate.now());

    private static final String INIT_TABLES = "CREATE TABLE IF NOT EXISTS pjatk.books (" +
            "book_id serial primary key," +
            "title varchar(64) not null," +
            "author varchar(64) not null," +
            "description varchar(512)," +
            "isbn varchar(13) not null," +
            "publishDate date," +
            "rating integer," +
            "creationDate date not null" +
            ");";

    @Inject
    private BookDtoMapper mapper;

    @Override
    public List<BookDto> getBooks() {
        String sql = createSelectSql(BookSearchCriteria.builder().build(), entity, false);
        return executeBookDtoQuery(sql);
    }

    @Override
    public Optional<BookDto> findBook(int bookId) {
        String sql = createSelectSql(BookSearchCriteria.builder().bookId(bookId).build(), entity, true);
        ResultSet resultSet = null;
        List<BookDto> temp = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()){
            resultSet = connection.prepareStatement(sql).executeQuery();
            temp = mapper.map(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return temp.size() > 0 ? Optional.of(temp.get(0)) : Optional.empty();
    }

    @Override
    public List<BookDto> searchBooks(SearchCriteria criteria) {
        String sql = createSelectSql(criteria, entity, false);
        return executeBookDtoQuery(sql);
    }

    @Override
    public boolean addBook(BookDto bookDto) {
        String sql = createInsertSql(bookDto, entity);
        try (Connection connection = DataSource.getConnection()){
            connection.prepareStatement(sql).execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void initTable() {

        try (Connection connection = DataSource.getConnection()) {
            connection.prepareStatement(INIT_TABLES).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<BookDto> executeBookDtoQuery(String sql) {
        ResultSet resultSet = null;
        List<BookDto> result = null;
        try (Connection connection = DataSource.getConnection()){
            resultSet = connection.prepareStatement(sql).executeQuery();
            result = mapper.map(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
