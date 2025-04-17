package com.ys.customannotations.customAnnotation.SmartLog;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartLog {
    String value() default "";
}
