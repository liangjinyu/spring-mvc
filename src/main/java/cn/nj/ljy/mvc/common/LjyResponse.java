package cn.nj.ljy.mvc.common;

import java.io.Serializable;

public class LjyResponse<T> implements Serializable {

    private static final long serialVersionUID = -443620517695762605L;
    
    static final String CODE = "code";
    static final String DESC = "desc";
    static final String CONTENT = "content";

    /**
     * 结果编码
     */
    private String code;

    /**
     * 结果信息
     */
    private String desc;

    /**
     * 返回结果
     */
    private T content;

    /**
     * 无参构造
     */
    public LjyResponse() {
    }

    /**
     * @param code
     * @param desc
     */
    public LjyResponse(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @param code
     * @param desc
     * @param content
     */
    public LjyResponse(String code, String desc, T content) {
        this.code = code;
        this.desc = desc;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }

}
