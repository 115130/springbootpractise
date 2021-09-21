package com.example.android.domain.result;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String rspCode = "000000";
    private String rspMsg = "操作成功";

    public Response(ExceptionMsg msg){
        this.rspCode =msg.getCode();
        this.rspMsg = msg.getMessage();
    }
}
