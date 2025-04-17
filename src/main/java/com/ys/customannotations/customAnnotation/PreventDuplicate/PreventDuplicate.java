package com.ys.customannotations.customAnnotation.PreventDuplicate;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PreventDuplicate {

    int expiration() default 1000;
}
