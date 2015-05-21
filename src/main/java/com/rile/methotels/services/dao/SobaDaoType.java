package com.rile.methotels.services.dao;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Documented
public @interface SobaDaoType {

}
