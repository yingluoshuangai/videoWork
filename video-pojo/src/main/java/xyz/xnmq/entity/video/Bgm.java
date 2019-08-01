package xyz.xnmq.entity.video;

import xyz.xnmq.annototion.Clazz;
import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
@Entity
@Table(name = "bgm")
@Clazz("bgm表")
public class Bgm extends AuditAuto  {
    @Filed("作者名")
    private String author;
    @Filed("名称")
    private String name;
    @Filed("地址")
    private String path;
    @Filed("是否禁用 0 使用 1 禁用")
    private int disabled;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
