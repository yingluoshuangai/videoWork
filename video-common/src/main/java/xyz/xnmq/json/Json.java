package xyz.xnmq.json;

import com.google.common.base.Strings;
import org.assertj.core.util.Lists;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by YangLiang
 * @Date: 2017-10-21
 * @Version: 1.0.0
 * @Description: 自定义响应数据结构
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017-10-21            00000001         创建文件
 */
public class Json {
    private static final String SUCCESS_CODE = "1";               //获取数据成功状态码
    private static final String ERROR_CODE = "0";                 //获取数据出错状态码
    private static final String PARAM_ERROR_CODE = "0";           //参数传递错误状态码
    private static final String BUSSINESS_ERROR_CODE = "-101";   //业务异常
    private static final String LOGIN_CODE = "502"; //登陆错误

    private static final String SUCCESS_MESSAGE = "请求数据成功!";          //获取数据成功
    private static final String ERROR_MESSAGE = "请求数据出错!";            //获取数据出错
    private static final String PARAM_ERROR_MESSAGE = "请求参数传递错误!"; //参数传递错误
    private static final String LOGIN_ERROR_MESSAGE = "登陆错误";

    private static final String _status = "status";
    private static final String _data = "data";
    private static final String _detail = "detail";
    private static final String _totalSize = "totalSize";
    private static final String _pageSize = "pageSize";
    private static final String _pageNumber = "pageNumber";
    private static final String _totalPage = "totalPage";
    private Object data;

    private Map<String, Object> _this = new HashMap<String, Object>();

    public Json() {

    }

    public Json(String status) {
        this(status, null);
    }

    public Json(String status, Object data) {
        this(status, data, "");
    }

    private Json(String status, Object data, String detail) {
        this.setDetail(detail);
        this.setStatus(status);
        this.setData(data);
    }

    public static Json success() {
        return newInstance(SUCCESS_CODE, "", SUCCESS_MESSAGE);
    }

    public static Json success(Object data) {
        return newInstance(SUCCESS_CODE, data, SUCCESS_MESSAGE);
    }

    public static Json error() {
        return newInstance(ERROR_CODE, "", ERROR_MESSAGE);
    }

    public static Json error(String msg) {
        return newInstance(ERROR_CODE, "", msg);
    }

    public static Json paramError() {
        return newInstance(PARAM_ERROR_CODE, "", PARAM_ERROR_MESSAGE);
    }

    public static Json paramError(String msg) {
        return newInstance(PARAM_ERROR_CODE, "", msg);
    }

    public static Json bussinessError(String msg) {
        return newInstance(BUSSINESS_ERROR_CODE, "", msg);
    }

    public static Json bussinessError(Object data, String msg) {
        return newInstance(BUSSINESS_ERROR_CODE, data, msg);
    }

    public static Json loginError(){
        return newInstance(LOGIN_CODE, "", LOGIN_ERROR_MESSAGE);
    }
    public static Json loginError(String msg){
        return newInstance(LOGIN_CODE, "", msg);
    }

    public static Json newInstance(String status) {
        return new Json(status);
    }

    public static Json newInstance(String status, Object data) {
        return new Json(status, data);
    }

    public static Json newInstance(String status, Object data, String detail) {
        return new Json(status, data, detail);
    }

    public String getStatus() {
        return (String) _this.get(_status);
    }

    public Json setStatus(String status) {
        _this.put(this._status, Strings.nullToEmpty(status));
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public Json setData(Object data) {
        this.data = data;
        return this;
    }

    public String getDetail() {
        return (String) _this.get(_detail);
    }

    public Json setDetail(String message) {
        _this.put(_detail, Strings.nullToEmpty(message));
        return this;
    }

    @JsonValue
    public Map<String, Object> toMap() {
        return _this;
    }

    private List collectionToList(Collection collection) {
        if (collection instanceof List)
            return (List) collection;
        else
            return Lists.newArrayList(collection);
    }
}
