package org.binance.vnd.wro.https.base;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class IHttps<T extends IResponseHttps> {
    public static final String GET = "GET";

    private int timeout = 1500;
    private final Map<String, String> headers = new HashMap<>();

    public abstract T getRequest(URL url);

    protected abstract void callService(final HttpsURLConnection httpsConnection, T response) throws IOException;

    public void setHeadersToHttpsConnection(HttpsURLConnection httpsConnection) {
        for (Map.Entry<String, String> m : getHeaders().entrySet()) {
            httpsConnection.setRequestProperty(m.getKey(), m.getValue());
        }
    }

    protected HttpsURLConnection initHttpsConnection(final URL url, final String method) throws IOException {
        final HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();
        httpsConnection.setRequestMethod(method);
        setHeadersToHttpsConnection(httpsConnection);
        setConnectionTimeOut(httpsConnection);
        return httpsConnection;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setConnectionTimeOut(final HttpsURLConnection httpsConnection) {
        httpsConnection.setConnectTimeout(getTimeout());
        httpsConnection.setReadTimeout(getTimeout());
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
