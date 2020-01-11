package com.Kroos.project.enums;


import lombok.Getter;
import lombok.Setter;

/**
 * 返回码和提示信息
 */
@Setter
@Getter
public enum CodeAndMessage {
    Success(200,"成功！"),
    //1开头的错误编码用于用户信息
    User_not_found(1,"该用户不存在"),
    Wrong_passwork(10,"密码错误"),
    Wrong_mobile_phone(11,"手机号格式错误"),
    Wrong_mail(12,"邮箱格式错误"),
    Wrong_ICard(13,"中国大陆居民身份证号格式错误"),

    //3开头的错误编码用于账本账单信息
    Add_bill_fail(3,"记账失败"),
    Wrong_number_for_bill_amount(30,"账单金额数字格式错误"),
    Wrong_date_format(31,"日期格式错误"),
    Date_is_future(32,"账单日期大于当前日期"),
    Amount_is_zero(33,"记账金额小于0"),
    Bill_type_is_null(34,"未选择收入或支出"),
    Bill_kind_is_null(35,"未选择账单来源或去处"),
    Currency_kind_is_null(36,"未选择货币种类"),

    Network_error(999,"网络异常"),
    ;

    private Integer code;
    private String message;
    CodeAndMessage(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
