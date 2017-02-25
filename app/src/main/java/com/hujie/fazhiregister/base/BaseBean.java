package com.hujie.fazhiregister.base;

/**
 * Created by hujie on 2017/2/22.
 */

public class BaseBean<T> {
    private boolean is_success;
    private String error_content;
    private T data;

    public String getError_content() {
        return error_content;
    }

    public void setError_content(String error_content) {
        this.error_content = error_content;
    }

    public boolean is_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
