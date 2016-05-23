package cn.edu.zucc.personplan.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mashiro on 2016/5/10.
 */
public class DataFormat {
    public static String TimeStampToString(long timestamp) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(timestamp));
    }

    public static String IntToString(int number) {
        return String.valueOf(number);
    }
    public static long StringToTimeStamp(String time) throws BusinessException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("请输入正确的日期");
        }
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
        long timestamp = date.getTime();
        return timestamp;
    }
}
