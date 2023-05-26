package pl.pijok.tpo6_bj_s24322.lib;

import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.annotations.Table;

import java.lang.reflect.Field;

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

                sql.append(entityField.getAnnotation(Column.class).name()).append(" = '").append(criteriaField.get(searchCriteria)).append("'");

                filledEntries++;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        sql.append(";");
        return sql.toString();
    }

    public String createInsertSql(Dto dto, Entity entity) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + entity.getClass().getAnnotation(Table.class).tableName());
        sql = new StringBuilder(sql + " (");

        for(int i = 0; i < Entity.class.getFields().length; i++) {
            if(i > 0) {
                sql.append(", ");
            }
            Field field = Entity.class.getFields()[i];
            Column column = field.getAnnotation(Column.class);
            sql.append(column.name());
        }

        sql.append(") VALUES (");

        for(int i = 0; i < dto.getClass().getDeclaredFields().length; i++) {
            if(i > 0) {
                sql.append(", ");
            }

            Field field = dto.getClass().getFields()[i];
            field.setAccessible(true);

            try {
                sql.append(field.get(dto));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sql.append(");");

        return sql.toString();
    }

}
