package com.sakura.utils.okhttp3;

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

    public void get() {

        String s = OkhttpUtils.get("http://www.baidu.com");
        System.out.println(s);
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.get();

    }

}
