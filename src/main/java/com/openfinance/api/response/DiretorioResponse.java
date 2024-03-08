package com.openfinance.api.response;

import com.openfinance.api.enums.DiretorioSituacaoType;
import com.openfinance.api.enums.DiretorioStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiretorioResponse<T> extends ApiResponseMessage {
    private Message message;
    private T data;
    private String token;

    public DiretorioResponse<T> info(T user, DiretorioStatusType diretorioStatusType) {
        this.setData(user);
        Message msg = new Message();
        msg.setStatus(diretorioStatusType.toString());
        msg.setAdditionalInfo(diretorioStatusType.desc);
        msg.setCode(diretorioStatusType.getCode());
        this.setMessage(msg);
        return this;
    }

    public DiretorioResponse<T> info(T user, DiretorioSituacaoType diretorioSituacaoType) {
        this.setData(user);
        Message msg = new Message();
        msg.setStatus(diretorioSituacaoType.toString());
        msg.setAdditionalInfo(diretorioSituacaoType.desc);
        msg.setCode(diretorioSituacaoType.getCode());
        this.setMessage(msg);
        return this;
    }

    public DiretorioResponse<T> withToken(String token) {
        this.setToken(token);
        return this;
    }

    public static <T> DiretorioResponse<T> body(T data) {
        DiretorioResponse<T> response = new DiretorioResponse<>();
        response.setData(data);
        return response;
    }
}
