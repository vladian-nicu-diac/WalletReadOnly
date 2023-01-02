package org.binance.vnd.wro.https.maker.response;


import org.binance.vnd.wro.https.base.IResponseHttps;

public class ResponseHttps implements IResponseHttps {

    private Integer respCode;
    private String resp;

    @Override
    public Integer getRespCode() {
        return respCode;
    }

    @Override
    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    @Override
    public String getResp() {
        return resp;
    }

    @Override
    public void setResp(String resp) {
        this.resp = resp;
    }

}
