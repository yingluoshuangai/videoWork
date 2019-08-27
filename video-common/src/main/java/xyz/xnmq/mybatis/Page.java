package xyz.xnmq.mybatis;

import xyz.xnmq.annototion.Filed;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Created by YangLiang
 * @Date: 2017/6/14
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017/6/14            00000001         创建文件
 */
public class Page<T> implements Serializable {
    private Object query;
    private List data;
    private Long totalSize;
    @Filed(desc = "每页多少条")
    private Integer pageSize = 20;
    @Filed(desc = "页码")
    private Integer pageNum = 1;
    @Filed("总页数")
    private Integer totalPage;

    public Page() {
        query = new HashMap<Object, Object>(0);
    }

    public Object getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query = query;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {

        this.totalSize = totalSize;
        this.totalPage = Double.valueOf(Math.ceil(new Double(totalSize) / pageSize)).intValue();
    }


    public Integer getStart() {
        return (pageNum - 1) * pageSize;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = Math.max(1, pageNum);
    }

    public Integer getTotalPage() {
        return totalPage;
    }
}
