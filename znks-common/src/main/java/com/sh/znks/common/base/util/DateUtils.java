package com.sh.znks.common.base.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class DateUtils {
    /**
     * date转String
     * format是转换格式 如"yyyy-MM-dd"
     * @param date
     * @param format
     * @return
     */
    public static String dateFormatYYYYMMDD(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * string转date类型
     * format是strDate的格式;strDate是 2018-02-26, format就是"yyyy-MM-dd"
     * @param strDate
     * @param format
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取某个日期之前或者之后的某个日期
     * @param nowdate
     * @param delay 间隔天数
     * @param formatter
     * @return
     */
    public static String getNextDay(String nowdate, String delay, String formatter) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatter);
            Date d = strToDate(nowdate, formatter);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            return format.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *获取date增加offset时间
     * @param offset
     * @param date
     * @return Date
     * */
    public static Date getOffSetDateTime(Integer offset,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     *获取date增加offset时间
     * @param offset
     * @param date
     * @return String yyyy-MM-dd
     * */
    public static String getOffSetStrTime(Integer offset,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        String offsetDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return offsetDate;
    }

}
