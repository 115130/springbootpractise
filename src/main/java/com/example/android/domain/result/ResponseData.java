package com.example.android.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseData extends Response{
    private Object data;


    public ResponseData(ExceptionMsg msg) {
        super(msg);
    }

    public ResponseData(String rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public ResponseData(String rspCode, String rspMsg, Object data) {
        super(rspCode, rspMsg);
        this.data = data;
    }

    public ResponseData(ExceptionMsg msg, Object data) {
        super(msg);
        this.data = data;
    }
}
