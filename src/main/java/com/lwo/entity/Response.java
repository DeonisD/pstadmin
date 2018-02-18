package com.lwo.entity;

/**
 *
 * @author drozdov_d
 */
public class Response {
    private String code     = "000";
    private String message  = "success";
    
    public Response() {
        
    }
    
    public Response( String code, String message ){
        this.code       = code;
        this.message    = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
