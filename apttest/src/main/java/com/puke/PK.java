package com.puke;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zijiao
 * @version 16/8/1
 */
@Inherited
@Retention(RetentionPolicy.CLASS)
public @interface PK {

    String value();

}
