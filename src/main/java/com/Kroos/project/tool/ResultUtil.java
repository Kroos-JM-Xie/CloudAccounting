package com.Kroos.project.tool;

import com.Kroos.project.enums.CodeAndMessage;

/**
 * 统一返回数据
 * @author Kroos
 */
public class ResultUtil {

    /**
     * 返回对象数据和响应编码和消息
     * @param object
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(CodeAndMessage.Success.getCode());
        result.setMessage(CodeAndMessage.Success.getMessage());
        result.setData(object);
        return result;
    }

    /**
     * 只返回响应编码和消息
     * @return
     */
    public static Result success(){
        Result result=new Result();
        result.setCode(CodeAndMessage.Success.getCode());
        result.setMessage(CodeAndMessage.Success.getMessage());
        return result;
    }

    /**
     * 错误消息返回
     * @param code
     * @param message
     * @return
     */
    public static Result fail(Integer code,String message){
        Result result=new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
