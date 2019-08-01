package xyz.xnmq.inf.video.dto;

import xyz.xnmq.entity.video.Bgm;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
public class BgmDto {
    private Bgm bgm = new Bgm();

    public String getAuthor() {
        return bgm.getAuthor();
    }

    public void setAuthor(String author) {
        bgm.setAuthor(author);
    }

    public String getName() {
        return bgm.getName();
    }

    public void setName(String name) {
        bgm.setName(name);
    }

    public String getPath() {
        return bgm.getPath();
    }

    public void setPath(String path) {
        bgm.setPath(path);
    }

    public int getDisabled() {
        return bgm.getDisabled();
    }

    public void setDisabled(int disabled) {
        bgm.setDisabled(disabled);
    }

    public Long getId() {
        return bgm.getId();
    }

    public void setId(Long id) {
        bgm.setId(id);
    }
}
