package com.Kroos.project.exception;

import com.Kroos.project.enums.CodeAndMessage;

public class EncryptException extends RuntimeException {
    private  CodeAndMessage result;

    public EncryptException(CodeAndMessage result){
        this.result=result;
    }

    public CodeAndMessage getResult(){
        return result;
    }
}
