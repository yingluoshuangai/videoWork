package xyz.xnmq.annototion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Created by YangLiang
 * @Date: 2017/6/11
 * @Version: 1.0.0
 * @Description: 用于生成属性文档
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017/6/11            00000001         创建文件
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Filed {
    int id() default 0;

    String desc() default "";

    String filed() default "";

    //是否必须
    boolean isMust() default false;

    String value() default "";

    //字段变更自动记录日志
    boolean autoLog() default false;

    //群组名，一般情况指需要统一逻辑处理的枚举名称
    String enumName() default "";

    //是否是数字
    boolean isNumeric() default false;

    //日期格式
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";

}