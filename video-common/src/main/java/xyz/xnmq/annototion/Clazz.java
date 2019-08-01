package xyz.xnmq.annototion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Created by YangLiang
 * @Date: 2017/6/11
 * @Version: 1.0.0
 * @Description: 用于生成类文档
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017/6/11            00000001         创建文件
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Clazz {
    String desc() default "";

    String value() default "";
}
