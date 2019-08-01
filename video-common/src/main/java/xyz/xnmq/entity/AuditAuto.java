package xyz.xnmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import xyz.xnmq.annototion.Clazz;
import xyz.xnmq.annototion.Filed;

import javax.persistence.*;
import java.sql.Timestamp;

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
@Clazz(desc = "自增长实体")
@MappedSuperclass
public class AuditAuto extends AutoId {
    public static final Long SYS_ID = -1l;

    @Column(name = "creater", updatable = false)
    @Filed("创建人")
    protected Long creater;

    @Column(name = "create_time", updatable = false)
    @Filed("创建时间")
//    protected Timestamp createTime = new Timestamp(System.currentTimeMillis());
    protected Timestamp createTime;

    @Column(name = "modifier")
    @Filed("修改人")
    protected Long modifier;

    @Column(name = "modify_time")
    @Filed("修改时间")
    protected Timestamp modifyTime;

    @Version
    @Column(name = "version")
    @Filed("版本号")
    protected Long version;

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    //@PreUpdate 在更新实体时，自动调用
    @PreUpdate
    public void preUpdate() {
        setModifyTime(new Timestamp(System.currentTimeMillis()));
        //setModifier(Sessions.getUserId());
        setModifier(233L);
    }

    //@PrePersist 在新建实体时，自动调用
    @PrePersist
    public void prePersist() {
        setCreateTime(new Timestamp(System.currentTimeMillis()));
//        setCreater(Sessions.getUserId());
//        setModifier(Sessions.getUserId());
        setCreater(233L);
        setModifier(233L);
        setModifyTime(new Timestamp(System.currentTimeMillis()));
    }
}
