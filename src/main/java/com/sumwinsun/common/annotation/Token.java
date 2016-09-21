package com.sumwinsun.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于处理数据重复提交的 annotation，结合拦截器使用
 * Created by Administrator on 2016/9/21 0021.
 * @Retention(RetentionPolicy.RUNTIME)          ：声明注解为运行时注解，
        注解@Retention可以用来修饰注解，是注解的注解，称为元注解。
        Retention注解有一个属性value，是RetentionPolicy类型的，Enum RetentionPolicy是一个枚举类型，
        这个枚举决定了Retention注解应该如何去保持，也可理解为Rentention 搭配 RententionPolicy使用。RetentionPolicy有3个值：CLASS  RUNTIME   SOURCE
        用@Retention(RetentionPolicy.CLASS)修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，但不会被虚拟机读取在运行的时候；
        用@Retention(RetentionPolicy.SOURCE )修饰的注解,表示注解的信息会被编译器抛弃，不会留在class文件中，注解的信息只会留在源文件中；
        用@Retention(RetentionPolicy.RUNTIME )修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时，
        所以他们可以用反射的方式读取。RetentionPolicy.RUNTIME 可以让你从JVM中读取Annotation注解的信息，以便在分析程序的时候使用.
 * @Target(ElementType.METHOD)注解的意思是：     ：该注解只能加在方法上面
 *      注解@Target也是用来修饰注解的元注解，它有一个属性ElementType也是枚举类型，
        值为：ANNOTATION_TYPE CONSTRUCTOR  FIELD LOCAL_VARIABLE METHOD PACKAGE PARAMETER TYPE
        如@Target(ElementType.METHOD) 修饰的注解表示该注解只能用来修饰在方法上。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    boolean save() default false;

    boolean remove() default false;
}