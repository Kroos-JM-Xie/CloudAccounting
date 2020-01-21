package com.Kroos.project.tool;



import java.io.Serializable;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

/**
 * 请求返回类
 * 统一返回处理结果
 * @author Kroos
 */

@Getter
@Setter
@JsonSerialize
public class Result implements Serializable {

    //返回码，200表示成功处理
    private Integer code;

    //返回描述,success表示成功
    private String message;

    //返回数据,如果有异常或错误，此时应为null
    private Object data;

    public Result(){

    }

    public  Result(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Result(Integer code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }



}
