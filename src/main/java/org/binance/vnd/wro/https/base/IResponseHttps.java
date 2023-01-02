package org.binance.vnd.wro.https.base;

public interface IResponseHttps {

    Integer getRespCode();

    void setRespCode(Integer respCode);

    String getResp();

    void setResp(String resp);

    default boolean isOk() {
        return getRespCode() != null && (getRespCode() == 200 || getRespCode() == 201);
    }
}
