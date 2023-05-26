package pl.pijok.tpo6_bj_s24322.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    public String tableName() default "";

}
