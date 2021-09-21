package com.example.android.domain.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ExceptionMsg {
    StudentSuccess("000010","学生登录成功"),
    TeacherSuccess("000011","老师登录成功"),
    SUCCESS("000000","操作成功"),
    FAILED("999999","操作失败"),
    ParamError("000001","参数错误"),

    LoginNameOrPasswordError("000100","用户名或者密码错误"),
    UserNameError("000101","用户名已经存在"),
    StudentNumberError("000102","学号已存在"),
    PasswordError("000103","密码错误"),
    UserNameLengthLimit("000104","用户名长度超过限制"),
    LoginNameNoteExists("000107","该用户名未注册"),
    UserNameSame("000108","新用户名和原来一致"),
    PasswordSame("000109","密码与原来一致");



    private String code;
    private String message;
}
