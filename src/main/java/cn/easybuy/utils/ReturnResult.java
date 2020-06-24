package cn.easybuy.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnResult implements Serializable {

    private int status;
    private Object data;
    private String message = "操作成功";

    /**
     * 返回成功状态
     *
     * @param obj
     */
    public ReturnResult returnSuccess(Object obj) {
        this.status = Constants.ReturnResult.SUCCESS;
        this.data = obj;
        return this;
    }

    /**
     * 返回默认成功状态
     */
    public ReturnResult returnSuccess() {
        this.status = Constants.ReturnResult.SUCCESS;
        return this;
    }

    /**
     * 返回失败状态
     *
     * @param message
     */
    public ReturnResult returnFail(String message) {
        this.status = Constants.ReturnResult.FAIL;
        this.message = message;
        return this;
    }

    public ReturnResult(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ReturnResult(Object data) {
        this.status = Constants.ReturnResult.SUCCESS;
        this.data = data;
    }

    public ReturnResult() {
    }
}
