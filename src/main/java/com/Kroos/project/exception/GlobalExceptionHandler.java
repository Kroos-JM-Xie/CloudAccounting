package com.Kroos.project.exception;

import com.Kroos.project.enums.CodeAndMessage;
import com.Kroos.project.tool.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理，待完善
 * @author Kroos
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handleArithmetic( ) {
        return JSON.toJSONString(new Result(CodeAndMessage.Network_error.getCode(),
                CodeAndMessage.Network_error.getMessage()));
    }
}
