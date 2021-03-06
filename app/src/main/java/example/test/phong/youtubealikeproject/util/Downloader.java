package example.test.phong.youtubealikeproject.util;

import android.util.Log;

import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by user on 1/5/2018.
 */
@Singleton
public class Downloader implements org.schabi.newpipe.extractor.Downloader {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0";
    private static String mCookies = "";

    @Inject
    public Downloader() {
    }

    /**
     * Download the text file at the supplied URL as in download(String),
     * but set the HTTP header field "Accept-Language" to the supplied string.
     *
     * @param siteUrl  the URL of the text file to return the contents of
     * @param language the language (usually a 2-character code) to set as the preferred language
     * @return the contents of the specified text file
     */
    @Override
    public String download(String siteUrl, String language) throws IOException, ReCaptchaException {
        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("Accept-Language", language);
        return download(siteUrl, requestProperties);
    }

    /**
     * Download the text file at the supplied URL as in download(String),
     * but set the HTTP headers included in the customProperties map.
     *
     * @param siteUrl          the URL of the text file to return the contents of
     * @param customProperties set request header properties
     * @return the contents of the specified text file
     * @throws IOException
     */
    @Override
    public String download(String siteUrl, Map<String, String> customProperties) throws IOException, ReCaptchaException {
        URL url = new URL(siteUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        Iterator it = customProperties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            con.setRequestProperty((String) pair.getKey(), (String) pair.getValue());
        }
        return dl(con);
    }

    /**
     * Download (via HTTP) the text file located at the supplied URL, and return its contents.
     * Primarily intended for downloading web pages.
     *
     * @param siteUrl the URL of the text file to download
     * @return the contents of the specified text file
     */
    @Override
    public String download(String siteUrl) throws IOException, ReCaptchaException {
        URL url = new URL(siteUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        //HttpsURLConnection con = NetCipher.getHttpsURLConnection(url);
        return dl(con);
    }

    /**
     * Common functionality between download(String url) and download(String url, String language)
     */
    private static String dl(HttpsURLConnection con) throws IOException, ReCaptchaException {
        StringBuilder response = new StringBuilder();
        BufferedReader in = null;

        try {
            con.setReadTimeout(30 * 1000);// 30s
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            if (getCookies().length() > 0) {
                con.setRequestProperty("Cookie", getCookies());
            }

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (Exception e) {
            Log.e("Downloader", "dl() ----- Exception thrown → " + e.getClass().getName());

            if (CustomExtractorHelper.isInterruptedCaused(e)) {
                throw new InterruptedIOException(e.getMessage());
            }

            /*
             * HTTP 429 == Too Many Request
             * Receive from Youtube.com = ReCaptcha challenge request
             * See : https://github.com/rg3/youtube-dl/issues/5138
             */
            if (con.getResponseCode() == 429) {
                throw new ReCaptchaException("reCaptcha Challenge requested");
            }

            throw new IOException(con.getResponseCode() + " " + con.getResponseMessage(), e);
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return response.toString();
    }


    public static synchronized void setCookies(String cookies) {
        Downloader.mCookies = cookies;
    }

    public static synchronized String getCookies() {
        return Downloader.mCookies;
    }

}
