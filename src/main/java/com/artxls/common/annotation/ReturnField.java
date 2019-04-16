package com.artxls.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.artxls.common.body.rewrite.FieldPattern;

/**
 * 返回对象需要返回的字段
 * @author Daryl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnField {
	
	/**
	 * 包含或者删除的字段集合
	 * @return
	 */
	String[] value() default {};
	
	/**
	 * 包含或者删除
	 * @return
	 */
	FieldPattern pattern() default FieldPattern.INCLUDE;
}
