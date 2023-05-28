package pl.pijok.tpo6_bj_s24322.lib;

import jakarta.servlet.http.HttpServletRequest;
import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.book.dto.BookSearchCriteria;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public SearchCriteria mapBookSearchCriteria(HttpServletRequest request) {
        String bookIdString = getValueFromParameter(request, "bookId");
        Integer bookId = bookIdString != null ? Integer.parseInt(bookIdString) : null;

        String ratingString = getValueFromParameter(request, "rating");
        Integer rating = ratingString != null ? Integer.parseInt(ratingString) : null;

        String creationDateString = getValueFromParameter(request, "creationDate");
        LocalDate creationDate = creationDateString != null ? LocalDate.parse(creationDateString) : null;

        String publishDateString = getValueFromParameter(request, "publishDate");
        LocalDate publishDate = publishDateString != null ? LocalDate.parse(publishDateString) : null;

        return BookSearchCriteria.builder()
                .bookId(bookId)
                .rating(rating)
                .creationDate(creationDate)
                .publishDate(publishDate)
                .author(getValueFromParameter(request, "author"))
                .title(getValueFromParameter(request, "title"))
                .description(getValueFromParameter(request, "description"))
                .build();
    }

    public List map(ResultSet set) {
        return new ArrayList();
    }

/*    public List mapResultSet(ResultSet set, Entity entity, Class resultClass) {
        List resultList = new ArrayList();

        try{
            while(set.next()) {
                Object object = resultClass.newInstance();

                for(Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object resultSetField = set.getObject(field.getAnnotation(Column.class).name());

                    Field dtoField = object.getClass().getDeclaredField(field.getName());
                    dtoField.setAccessible(true);
                    dtoField.set(object, resultSetField);
                }

                resultList.add(object);
            }
        }
        catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;

        }

        return resultList;
    }*/

    protected String getValueFromParameter(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter) != null ? request.getParameter(parameter) : null;
    }

}
