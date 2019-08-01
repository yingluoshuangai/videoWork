package xyz.xnmq.entity;


import xyz.xnmq.annototion.Filed;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Copyright (C), 2017-2077 上海从开网络科技有限公司 版权所有
 * @Website www.congkai.net
 * @Author: Created by YangLiang
 * @Date: 2017/6/4
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017/6/4            00000001         创建文件
 */
@MappedSuperclass
public class AutoId {
    @Id
    @GeneratedValue
    @Filed("编号")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
