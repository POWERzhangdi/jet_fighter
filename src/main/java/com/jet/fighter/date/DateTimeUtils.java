package com.jet.fighter.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UnknownFormatFlagsException;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 日期格式化工具类
 * <p>
 * Description: {@link DateTimeUtils,DateFormat }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public class DateTimeUtils {

    private static final String START_LOCAL_DATE_TIME = "T 00:00:00";

    private static final String END_LOCAL_DATE_TIME = "T 23:59:59";

    private static final int TIME_LENGTH = 10;

    private static final String COLON = ":";

    private static final char T = 'T';

    private static final long UNIT = 60L;

    private static final long C_NUMBER = 9L;

    private static final int ZERO = 0;

    private DateTimeUtils() {
    }

    /**
     * Description: 格式化 localDateTime to String
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 17:56
     * @Param [java.time.LocalDateTime, java.lang.String]
     * @Return java.lang.String
     * @see LocalDateTime
     */
    public static String formatLocalDateTimeToString(LocalDateTime time, String format) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString time is null");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString format is blank");
        }
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 String to localDateTime
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 17:56
     * @Param [java.time.LocalDateTime, java.lang.String]
     * @Return java.lang.String
     * @see LocalDateTime
     */
    public static LocalDateTime formatStringToLocalDateTime(String time, String format) {
        if (null == time || time.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime time is blank");
        }
        if (time.length() < TIME_LENGTH) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime time format error");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime format is blank");
        }

        //当传入 yyyy-MM-dd || yyyy/MM/dd的数据时候
        if (time.length() == TIME_LENGTH) {
            return LocalDateTime.parse(time + START_LOCAL_DATE_TIME, DateTimeFormatter.ofPattern(format));
        }

        //当传入 T 处理 2022-01-01T13:11:09
        if (time.length() > TIME_LENGTH && time.charAt(TIME_LENGTH) == T) {
            return LocalDateTime.parse(time);
        }

        //无 T 处理
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 localDateTime to String
     * 此方法 会根据 语言进行国际化处理
     * 注意：如果要做 国际化处理 format 入参: d MMM uuuu 这样的格式
     * 不支持 yyyy mm dd
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 17:56
     * @Param [java.time.LocalDateTime, java.lang.String]
     * @Return java.lang.String
     * @see LocalDateTime
     * @see Locale
     */
    public static String formatLocalDateTimeToString(LocalDateTime time, String format, Locale locale) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString time is null");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString format is blank");
        }
        if (null == locale) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString locale is null");
        }
        return time.format(DateTimeFormatter.ofPattern(format, locale));
    }

    /**
     * Description: 格式化 localDate to String
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 18:10
     * @Param [java.time.LocalDate, java.lang.String]
     * @Return java.lang.String
     * @see LocalDate
     */
    public static String formatLocalDateToString(LocalDate time, String format) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString time is null");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString format is blank");
        }
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 String to localDate
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 18:37
     * @Param [java.lang.String, java.lang.String]
     * @Return java.time.LocalDate
     */
    public static LocalDate formatStringToLocalDate(String time, String format) {
        if (null == time || time.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime time is blank");
        }
        if (time.length() < TIME_LENGTH) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime time format error");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalDateTime format is blank");
        }

        return LocalDate.parse(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 localDate to String
     * 此方法 会根据 语言进行国际化处理
     * 注意：如果要做 国际化处理 format 入参: d MMM uuuu 这样的格式
     * 不支持 yyyy mm dd
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 18:10
     * @Param [java.time.LocalDate, java.lang.String]
     * @Return java.lang.String
     * @see LocalDate
     * @see Locale
     */
    public static String formatLocalDateToString(LocalDate time, String format, Locale locale) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString time is null");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString format is blank");
        }
        if (null == locale) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString locale is null");
        }
        return time.format(DateTimeFormatter.ofPattern(format, locale));
    }

    /**
     * Description: 格式化 LocalTime to String
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 18:10
     * @Param [java.time.LocalDate, java.lang.String]
     * @Return java.lang.String
     * @see LocalTime
     */
    public static String formatLocalTimeToString(LocalTime time, String format) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalTimeToString time is null");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatLocalTimeToString format is blank");
        }
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 String to LocalTime
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 18:39
     * @Param [java.lang.String, java.lang.String]
     * @Return java.time.LocalTime
     */
    public static LocalTime formatStringToLocalTime(String time, String format) {
        if (null == time || time.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalTime time is blank");
        }
        if (!time.contains(COLON)) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalTime time format error");
        }
        if (null == format || format.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils formatStringToLocalTime format is blank");
        }

        return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Description: 格式化 localDateTime to String
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 12:02
     * @Param [java.time.LocalDateTime, java.time.format.FormatStyle]
     * @Return java.lang.String
     * @see FormatStyle
     * 样式:
     * FULL {2022年1月6日星期四 中国标准时间下午3:50:09}
     * LONG {2022年1月6日 CST 下午3:53:14}
     * MEDIUM {2022年1月6日下午3.57:51}
     * SHORT {2022/1/6 下午3:58}
     */
    public static String formatLocalDateTimeToString(LocalDateTime time, FormatStyle style) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString time is null");
        }
        if (null == style) {
            throw new NullPointerException("DateTimeUtils formatLocalDateTimeToString style is null");
        }
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(style);
        return time.format(formatter);
    }

    /**
     * Description: 格式化 LocalDate to String
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 12:02
     * @Param [java.time.LocalDate, java.time.format.FormatStyle]
     * @Return java.lang.String
     * @see FormatStyle
     * 样式:
     * FULL {2022年1月6日星期四}
     * LONG {2022年8月5日}
     * MEDIUM {2022-8-5}
     * SHORT {22-8-5}
     */
    public static String formatLocalDateToString(LocalDate time, FormatStyle style) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString time is null");
        }
        if (null == style) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToString style is null");
        }
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(style);
        return time.format(formatter);
    }


    /**
     * Description: 格式化 LocalDateTime to String
     * TODO 还不能使用
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 12:02
     * @Param [java.time.LocalTime, java.time.format.FormatStyle]
     * @Return java.lang.String
     * @see FormatStyle
     * 样式:
     * FULL {2022年1月6日星期四}
     * LONG {2022年8月5日}
     * MEDIUM {2022-8-5}
     * SHORT {22-8-5}
     */
    public static String formatCombinationLocalDateTimeToString(LocalDateTime time, FormatStyle dateTimeStyle, FormatStyle timeStyle) {
        if (null == time) {
            throw new NullPointerException("DateTimeUtils formatCombinationLocalDateTimeToString time is null");
        }
        if (null == dateTimeStyle) {
            throw new NullPointerException("DateTimeUtils formatCombinationLocalDateTimeToString style is null");
        }
        if (null == timeStyle) {
            throw new NullPointerException("DateTimeUtils formatCombinationLocalDateTimeToString style is null");
        }
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(dateTimeStyle, timeStyle);
        return time.format(formatter);
    }

    /**
     * Description: 格式化 Minute to HH:mm
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 10:34
     * @Param [java.lang.Long]
     * @Return java.lang.String
     */
    public static String formatMinuteToHHmm(Long time) {
        if (null == time || Long.valueOf(0).equals(time)) {
            throw new NullPointerException("DateTimeUtils formatLongToLocalTime time is null");
        }

        StringBuffer buffer = new StringBuffer();

        if (time == UNIT) {
            buffer.append("01:00");
        }

        if (time < UNIT) {
            buffer.append("00:");
            if (time < C_NUMBER || time == C_NUMBER) {
                buffer.append("0" + time);
            }

            if (time > C_NUMBER) {
                buffer.append(time);
            }
        }

        if (time > UNIT) {
            long hour = time / UNIT;
            if (hour <= C_NUMBER) {
                buffer.append("0" + hour);
            } else {
                buffer.append(hour);
            }

            buffer.append(":");

            long diff = time - hour * UNIT;
            if (diff == ZERO) {
                buffer.append("00");
            }
            if (diff > ZERO && (diff < C_NUMBER || diff == C_NUMBER)) {
                buffer.append("0" + diff);
            }
            if (diff > ZERO && diff > C_NUMBER) {
                buffer.append(diff);
            }
        }

        return buffer.toString();
    }

    /**
     * Description: 格式化 LocalDate to LocalDateTime
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 13:06
     * @Param [java.time.LocalDate, java.time.LocalTime]
     * @Return java.time.LocalDateTime
     */
    public static LocalDateTime formatLocalDateToLocalDateTime(LocalDate localDate, LocalTime localTime) {
        if (null == localDate) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToLocalDateTime localDate is null");
        }
        if (null == localTime) {
            throw new NullPointerException("DateTimeUtils formatLocalDateToLocalDateTime localTime is null");
        }

        LocalDateTime time = LocalDateTime.of(localDate, localTime);
        return time;
    }

    /**
     * Description: 比较两个时间大小
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime > endTime 返回 true
     * 2.当 startTime < endTime 返回 false
     * 3.当 startTime = endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalDateTime, java.time.LocalDateTime]
     * @Return boolean
     */
    public static boolean compareLocalDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) > ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较两个时间大小
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime > endTime 返回 true
     * 2.当 startTime < endTime 返回 false
     * 3.当 startTime = endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalDate, java.time.LocalDate]
     * @Return boolean
     */
    public static boolean compareLocalDate(LocalDate startTime, LocalDate endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) > ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较两个时间大小
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime > endTime 返回 true
     * 2.当 startTime < endTime 返回 false
     * 3.当 startTime = endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalDate, java.time.LocalDate]
     * @Return boolean
     */
    public static boolean compareLocalTime(LocalTime startTime, LocalTime endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) > ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较两个时间是否相等
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime = endTime 返回 true
     * 2.当 startTime != endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalDateTime, java.time.LocalDateTime]
     * @Return boolean
     */
    public static boolean compareEqualLocalDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) == ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较两个时间是否相等
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime = endTime 返回 true
     * 2.当 startTime != endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalDate, java.time.LocalDate]
     * @Return boolean
     */
    public static boolean compareEqualLocalDate(LocalDate startTime, LocalDate endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) == ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较两个时间是否相等
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime = endTime 返回 true
     * 2.当 startTime != endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.time.LocalTime, java.time.LocalTime]
     * @Return boolean
     */
    public static boolean compareEqualLocalTime(LocalTime startTime, LocalTime endTime) {
        boolean result = false;
        if (null == startTime || null == endTime) {
            return result;
        }
        if (startTime.compareTo(endTime) == ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较格式为 ?:? 样式的数据大小
     * 注意: 不要被 hh:mm误解 这是对于 mm:ss 这样的数据也会对比
     * 样式为: time:time 即可
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime > endTime 返回 true
     * 2.当 startTime < endTime 返回 false
     * 3.当 startTime = endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:33
     * @Param [java.lang.String, java.lang.String]
     * @Return boolean
     */
    public static boolean compareHHmm(String startTime, String endTime) {
        if (null == startTime || startTime.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils compareHHmm startTime is blank");
        }
        if (null == endTime || endTime.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils compareHHmm endTime is blank");
        }
        if (startTime.charAt(3) != ':') {
            throw new UnknownFormatFlagsException("DateTimeUtils compareHHmm startTime format error");
        }
        if (endTime.charAt(3) != ':') {
            throw new UnknownFormatFlagsException("DateTimeUtils compareHHmm endTime format error");
        }
        boolean result = false;
        if (startTime.compareTo(endTime) > ZERO) {
            result = true;
        }
        return result;
    }

    /**
     * Description: 比较格式为 ?:? 样式的数据是否相等
     * 参数说明：
     * 以 startTime为标准 对 endTime进行大小比较
     * 返回值说明：
     * 1.当 startTime = endTime 返回 true
     * 2.当 startTime != endTime 返回 false
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 11:19
     * @Param [java.lang.String, java.lang.String]
     * @Return boolean
     */
    public static boolean compareEqualHHmm(String startTime, String endTime) {
        if (null == startTime || startTime.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils compareHHmm startTime is blank");
        }
        if (null == endTime || endTime.length() == ZERO) {
            throw new NullPointerException("DateTimeUtils compareHHmm endTime is blank");
        }
        if (startTime.charAt(3) != ':') {
            throw new UnknownFormatFlagsException("DateTimeUtils compareHHmm startTime format error");
        }
        if (endTime.charAt(3) != ':') {
            throw new UnknownFormatFlagsException("DateTimeUtils compareHHmm endTime format error");
        }
        boolean result = false;
        if (startTime.compareTo(endTime) == ZERO) {
            result = true;
        }
        return result;
    }


    /**
     * Description: 计算 LocalDateTime 相差时间
     * 注意:
     * 依据 startTime 为标准跟 endTime做时间处理
     * 因此出现负数的情况下 意味着 startTime < endTime
     * 当 abs 开启为true的时候,会将 相差时间进行绝对值运算
     * 1.当差为 -1 abs开启获取结果 1
     * 2.当差为 -1 abs未开启获取结果 -1
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 13:24
     * @Param [java.time.LocalDateTime, java.time.LocalDateTime, org.diba.com.bean.lookup.time.DateTimeUtils.TimeUnit,java.lang.Boolean]
     * @Return long
     */
    public static long diffLocalDateTime(LocalDateTime startTime, LocalDateTime endTime, TimeUnit timeUnit, boolean abs) {

        if (null == startTime) {
            throw new NullPointerException("DateTimeUtils diffLocalDateTime startTime is null");
        }
        if (null == endTime) {
            throw new NullPointerException("DateTimeUtils diffLocalDateTime startTime is null");
        }
        if (null == timeUnit) {
            throw new NullPointerException("DateTimeUtils diffLocalDateTime timeUnit is null");
        }

        long diffTime = ZERO;

        Duration duration = null;
        if (timeUnit != TimeUnit.YEARS || timeUnit != TimeUnit.MONTHS) {
            duration = Duration.between(startTime, endTime);
        }

        switch (timeUnit) {
            case YEARS:
                diffTime = ChronoUnit.YEARS.between(startTime, endTime);
                break;
            case MONTHS:
                diffTime = ChronoUnit.MONTHS.between(startTime, endTime);
                break;
            case DAYS:
                diffTime = duration.toDays();
                break;
            case HOURS:
                diffTime = duration.toHours();
                break;
            case MINUTES:
                diffTime = duration.toMinutes();
                break;
            case SECONDS:
                diffTime = duration.toMillis() / 1000;
                break;
            case MILLIS:
                diffTime = duration.toMillis();
                break;
            case NANOS:
                diffTime = duration.toNanos();
                break;
        }

        if (abs) {
            diffTime = Math.abs(diffTime);
        }

        return diffTime;
    }

    /**
     * Description: 计算 LocalDateTime 相差时间
     * 注意:
     * 依据 startTime 为标准跟 endTime做时间处理
     * 因此出现负数的情况下 意味着 startTime > endTime
     * 当 abs 开启为true的时候,会将 相差时间进行绝对值运算
     * 1.当差为 -1 abs开启获取结果 1
     * 2.当差为 -1 abs未开启获取结果 -1
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 13:24
     * @Param [java.time.LocalDate, java.time.LocalDate, org.diba.com.bean.lookup.time.DateTimeUtils.TimeUnit,java.lang.Boolean]
     * @Return long
     */
    public static long diffLocalDate(LocalDate startTime, LocalDate endTime, TimeUnit timeUnit, boolean abs) {

        if (null == startTime) {
            throw new NullPointerException("DateTimeUtils diffLocalDate startTime is null");
        }
        if (null == endTime) {
            throw new NullPointerException("DateTimeUtils diffLocalDate startTime is null");
        }
        if (null == timeUnit) {
            throw new NullPointerException("DateTimeUtils diffLocalDate timeUnit is null");
        }

        long diffTime = ZERO;

        Duration duration = null;
        if (timeUnit != TimeUnit.YEARS || timeUnit != TimeUnit.MONTHS) {
            duration = Duration.between(startTime, endTime);
        }

        switch (timeUnit) {
            case YEARS:
                diffTime = ChronoUnit.YEARS.between(startTime, endTime);
                break;
            case MONTHS:
                diffTime = ChronoUnit.MONTHS.between(startTime, endTime);
                break;
            case DAYS:
                diffTime = duration.toDays();
                break;
            case HOURS:
                diffTime = duration.toHours();
                break;
            case MINUTES:
                diffTime = duration.toMinutes();
                break;
            case SECONDS:
                diffTime = duration.toMillis() * 1000;
                break;
            case MILLIS:
                diffTime = duration.toMillis();
                break;
            case NANOS:
                diffTime = duration.toNanos();
                break;
        }

        if (abs) {
            diffTime = Math.abs(diffTime);
        }

        return diffTime;
    }

    /**
     * Description: 获取 LocalDateTime 时间的 min
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 14:36
     * @Param [java.time.LocalDateTime]
     * @Return java.time.LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeMin(LocalDateTime time) {
        return formatLocalDateToLocalDateTime(time.toLocalDate(), DateFormat.HH_mm_ss_min);
    }

    /**
     * Description:获取 LocalDateTime 时间的 max
     *
     * @Author: di.zhang
     * @Date: 2022/8/8 14:37
     * @Param [java.time.LocalDateTime]
     * @Return java.time.LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeMax(LocalDateTime time) {
        return formatLocalDateToLocalDateTime(time.toLocalDate(), DateFormat.HH_mm_ss_max);
    }


    public enum TimeUnit {

        YEARS("year"),
        MONTHS("month"),
        DAYS("day"),
        HOURS("hour"),
        MINUTES("minute"),
        SECONDS("seconds"),
        MILLIS("millis"),
        NANOS("nanos");

        TimeUnit(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        private String type;
    }
}
