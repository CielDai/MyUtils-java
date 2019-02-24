package com.sakura.utils.time;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.zone.ZoneRules;
import java.util.Date;

import static com.sakura.utils.time.FormatterEnums.*;

/**
 * Description: 时间工具类，基于java8
 *
 * @author sakura
 * <p>
 * Date: 2019-02-24 5:35 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class TimeUtils {

    /**
     * 获取当前时间
     * 默认格式 "yyyy/MM/dd HH:mm:ss"
     *
     * @return
     */
    public static String getCurrentTime() {
        return LONG_DATETIME_TO_SECOND_WITH_COLON.formatter().format(LocalDateTime.now());
    }

    /**
     * 获取当前时间
     *
     * @param formatter 时间格式
     * @return
     */
    public static String getCurrentTime(FormatterEnums formatter) {
        return formatter.formatter().format(LocalDateTime.now());
    }

    /**
     * 时间格式化
     *
     * @param dateTime  时间
     * @param formatter 格式
     * @return
     */
    public static String formatDatetime(LocalDateTime dateTime, FormatterEnums formatter) {
        return formatter.formatter().format(dateTime);
    }

    /**
     * 字符串转时间
     *
     * @param datetime  时间字符串
     * @param formatter 格式
     * @return
     */
    public static LocalDateTime parseDatetime(String datetime, FormatterEnums formatter) {
        return LocalDateTime.parse(datetime, formatter.formatter());
    }

    /**
     * 时间类型转化
     *
     * @param date java.util.Date
     * @return
     * @see java.util.Date
     * 转
     * @see java.time.LocalDateTime
     */
    public static LocalDateTime dateToLocalDatetime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 时间类型转化
     *
     * @param ldt java.time.LocalDateTime
     * @return
     * @see java.time.LocalDateTime
     * 转
     * @see java.util.Date
     */
    public static Date ldtToDate(LocalDateTime ldt) {
        return Date.from(ldt.toInstant(ZoneOffset.ofHours(8)));
    }

    /**
     * 获取一天的开始
     *
     * @param time
     * @return
     */
    public static LocalDateTime getStartOfDay(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取一天的结束
     *
     * @param time
     * @return
     */
    public static LocalDateTime getEndOfDay(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);
    }

    public static void main(String[] args) {
        String currentTime = TimeUtils.getCurrentTime(LONG_DATETIME_TO_SECOND_WITH_COLON);
        String s = TimeUtils.formatDatetime(LocalDateTime.MAX, LONG_DATETIME_TO_SECOND_WITH_COLON);
        LocalDateTime localDateTime = TimeUtils.parseDatetime("2019/09/11 19:12:12", LONG_DATETIME_TO_SECOND_WITH_COLON);
        LocalDateTime localDateTime1 = TimeUtils.dateToLocalDatetime(new Date());
        localDateTime1.toString();
        Date date = TimeUtils.ldtToDate(LocalDateTime.now());
        LocalDateTime startOfDay = TimeUtils.getStartOfDay(LocalDateTime.now());
        LocalDateTime endOfDay = TimeUtils.getEndOfDay(LocalDateTime.now());
        System.out.println(1);
    }

}
