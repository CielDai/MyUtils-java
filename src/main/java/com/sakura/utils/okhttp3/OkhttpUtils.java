package com.sakura.utils.okhttp3;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: okhttp3工具类
 *
 * @author sakura
 * <p>
 * Date: 2019-02-24 4:20 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class OkhttpUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(OkhttpUtils.class);


    private static final OkHttpClient client;

    private static int connectTimeout = 10;
    private static int writeTimeout = 10;
    private static int readTimeout = 30;

    static {

        client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }

    private OkhttpUtils() {
        throw new RuntimeException("can't be created");
    }

    public static String get(String url) {
        Request request = new Request.Builder().url(url).get().build();

        Call call = client.newCall(request);

        String resp = null;
        try {
            Response execute = call.execute();
            resp = execute.body().string();
        } catch (IOException e) {
            LOGGER.error("请求失败, e={}", e.getMessage());
        }
        return resp;

    }

    public static String get(String url, Map<String,String> params) {
        StringBuilder paramStr = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            params.forEach((k, v) -> paramStr.append(k).append("=").append(v).append("&"));
        }
        url += paramStr;

        String resp = get(url);
        return resp;
    }

    private final static MediaType MEDIATYPE_JSON = MediaType.parse("application/json; charset=utf-8");


    public static String postJson(String url, Map<String, String> params) {
        RequestBody requestBody = RequestBody.create(MEDIATYPE_JSON, JSON.toJSONString(params));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        String string = null;
        try {
            Response execute = call.execute();
            string = execute.body().string();
        } catch (IOException e) {
            LOGGER.error("请求失败, e={}", e.getMessage());
        }

        return string;

    }

}
