package pl.pijok.tpo6_bj_s24322.lib;

import jakarta.servlet.http.HttpServletRequest;
import pl.pijok.tpo6_bj_s24322.annotations.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public SearchCriteria mapSearchCriteria(HttpServletRequest request) {
        return SearchCriteria.builder()
                .author(getValueFromParameter(request, "author"))
                .title(getValueFromParameter(request, "title"))
                .description(getValueFromParameter(request, "description"))
                .build();
    }

    public List mapResultSet(ResultSet set, Entity entity, Class resultClass) {
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
    }

    private String getValueFromParameter(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter) != null ? request.getParameter(parameter) : null;
    }

}
