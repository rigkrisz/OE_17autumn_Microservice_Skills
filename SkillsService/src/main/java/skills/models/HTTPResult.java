/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skills.models;

/**
 *
 * @author hallgato
 * @param <T>
 */
public class HTTPResult<T> {
    private boolean success;
    private String message;
    private T data;
    
    public HTTPResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public HTTPResult() {
    }
    public HTTPResult(boolean success) {
        this.success = success;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
