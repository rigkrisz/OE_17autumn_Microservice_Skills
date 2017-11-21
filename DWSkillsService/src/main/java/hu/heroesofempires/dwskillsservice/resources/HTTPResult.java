/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.heroesofempires.dwskillsservice.resources;

/**
 *
 * @author hallgato
 */
public class HTTPResult<T>
{
    private boolean success;
    private String message;
    private T data;
    
    public HTTPResult(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }
    
    public HTTPResult(boolean success)
    {
        this.success = success;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
