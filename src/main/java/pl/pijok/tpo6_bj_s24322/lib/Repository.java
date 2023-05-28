package pl.pijok.tpo6_bj_s24322.lib;

import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository {

    public String createSelectSql(SearchCriteria searchCriteria, Entity entity) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + entity.getClass().getAnnotation(Table.class).tableName());

        boolean allFieldsEmpty = true;
        for(Field field : searchCriteria.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(field.get(searchCriteria) != null) {
                    allFieldsEmpty = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(allFieldsEmpty) {
            sql.append(";");
            System.out.println("CREATED SELECT QUERY: " + sql.toString());
            return sql.toString();
        }

        sql.append(" WHERE ");

        int filledEntries = 0;
        for(int i = 0; i < searchCriteria.getClass().getDeclaredFields().length; i++) {
            Field criteriaField = searchCriteria.getClass().getDeclaredFields()[i];
            criteriaField.setAccessible(true);
            try {
                if(criteriaField.get(searchCriteria) == null) {
                    continue;
                }

                if(filledEntries > 0) {
                    sql.append(" AND ");
                }

                Field entityField = entity.getClass().getDeclaredField(criteriaField.getName());
                entityField.setAccessible(true);

                sql.append(entityField.getAnnotation(Column.class).name()).append(" LIKE '%").append(criteriaField.get(searchCriteria)).append("%'");

                filledEntries++;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        sql.append(";");
        System.out.println("CREATED SELECT QUERY: " + sql.toString());
        return sql.toString();
    }

    public String createInsertSql(Dto dto, Entity entity) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + entity.getClass().getAnnotation(Table.class).tableName());
        sql = new StringBuilder(sql + " (");

        boolean filledFirst = false;

        List<String> skipFields = new ArrayList<>();

        for(int i = 0; i < entity.getClass().getDeclaredFields().length; i++) {
            Field field = entity.getClass().getDeclaredFields()[i];
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if(column.ignore()) {
                skipFields.add(field.getName());
                continue;
            }

            if(filledFirst) {
                sql.append(", ");
            }
            else{
                filledFirst = true;
            }

            sql.append(column.name());
        }

        sql.append(") VALUES (");

        filledFirst = false;
        for(int i = 0; i < dto.getClass().getDeclaredFields().length; i++) {
            Field field = dto.getClass().getDeclaredFields()[i];
            field.setAccessible(true);

            if(skipFields.contains(field.getName())) {
                continue;
            }

            if(filledFirst) {
                sql.append(", ");
            }
            else{
                filledFirst = true;
            }

            try {
                sql.append("'").append(field.get(dto)).append("'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sql.append(");");
        System.out.println("CREATED INSERT: " + sql.toString());
        return sql.toString();
    }

}
