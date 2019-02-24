package com.sakura.utils.time;

import java.time.format.DateTimeFormatter;

/**
 * Description: 时间格式工具类
 *
 * @author sakura
 * <p>
 * Date: 2019-02-24 5:41 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public enum FormatterEnums {

    /**
     *
     */
    LONG_DATETIME_TO_SECOND_WITH_COLON("yyyy/MM/dd HH:mm:ss");


    FormatterEnums(String formatter) {
        this.formatter = formatter;
    }

    private String formatter;

    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern(formatter);
    }



}
