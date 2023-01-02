package org.binance.vnd.wro.https.maker.utility;

import java.net.MalformedURLException;
import java.net.URL;

public class UtilityMakerHttps {

    UtilityMakerHttps() {
        //
    }

    public static URL createAndGetURL(final String url, final String path) {
        if (path != null) {
            return createAndGetURL(url + path);
        }
        return createAndGetURL(url);
    }

    public static URL createAndGetURL(final String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static URL createAndGetURL(final String url, final String path, final String qs) {
        if (url != null) {
            if (path != null && qs != null) {
                return createAndGetURL(url + path + (qs.startsWith("?") ? qs : ("?" + qs)));
            } else if (path != null) {
                return createAndGetURL(url, path);
            }
            return createAndGetURL(url);
        }
        return null;
    }

}
