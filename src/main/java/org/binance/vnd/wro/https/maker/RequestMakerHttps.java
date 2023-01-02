package org.binance.vnd.wro.https.maker;

import org.binance.vnd.wro.https.base.IHttps;
import org.binance.vnd.wro.https.base.IResponseHttps;
import org.binance.vnd.wro.https.maker.response.ResponseHttps;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class RequestMakerHttps<T extends IResponseHttps> extends IHttps<ResponseHttps> {

    private static RequestMakerHttps<ResponseHttps> makeCallHttps;

    private RequestMakerHttps() {
    }

    public static RequestMakerHttps<ResponseHttps> getSingleTon() {
        if (makeCallHttps == null) {
            makeCallHttps = new RequestMakerHttps<>();
        }
        return makeCallHttps;
    }

    public RequestMakerHttps<T> configure(int timeout) {
        setTimeout(timeout);
        return this;
    }

    @Override
    public ResponseHttps getRequest(URL url) {
        try {
            if (url != null) {
                HttpsURLConnection httpsConnection = initHttpsConnection(url, GET);
                ResponseHttps response = new ResponseHttps();
                callService(httpsConnection, response);
                httpsConnection.disconnect();
                return response;
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @Override
    protected void callService(HttpsURLConnection httpsConnection, ResponseHttps response) throws IOException {
        int responseCode = httpsConnection.getResponseCode();
        response.setRespCode(responseCode);
        if (responseCode == 200 || responseCode == 201) {
            response.setResp(getResponseFromRequest(httpsConnection.getInputStream()));
        } else {
            response.setResp(getResponseFromRequest(httpsConnection.getErrorStream()));
        }
    }

    public String getResponseFromRequest(final InputStream inputStream) throws IOException {
        if (inputStream != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String outLine;
            StringBuilder sb = new StringBuilder();
            try {
                while ((outLine = in.readLine()) != null) {
                    sb.append(outLine);
                }
            } finally {
                in.close();
            }
            return sb.toString();
        }
        return null;
    }

}
