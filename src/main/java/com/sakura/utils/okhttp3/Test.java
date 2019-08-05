package com.sakura.utils.okhttp3;

import okhttp3.*;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Description: test
 *
 * @author sakura
 * <p>
 * Date: 2019-02-24 5:15 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class Test {


    private static final OkHttpClient client;

    private static int connectTimeout = 10;
    private static int writeTimeout = 10;
    private static int readTimeout = 30;


    public void get() {

        String s = OkhttpUtils.get("http://www.baidu.com");
    }


    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

    }

}
