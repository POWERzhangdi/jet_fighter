package com.jet.fighter.date;

import java.time.LocalTime;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 日期 基础 格式
 * <p>
 * Description: {@link DateFormat }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:21
 * @Version: v1.0
 */
public interface DateFormat {

    /* hh */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17
     */
    String HH = "HH";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05
     */
    String hh = "hh";

    /* mm */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 03
     */
    String mm = "mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 03
     */
    String MM = "MM";

    /* dd */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06
     */
    String dd = "dd";

    /* ss */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 01
     */
    String ss = "ss";

    /* mm:ss */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 03:01
     */
    String mm_ss = "mm:ss";

    /* HH:mm */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03
     */
    String HH_mm = "HH:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17-03
     */
    String HH__mm = "HHmm";


    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03
     */
    String hh_mm = "hh:mm";

    /* HH:mm:ss */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03:01
     */
    String HH_mm_ss = "HH:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03:01
     */
    String hh_mm_ss = "hh:mm:ss";


    /* yyyy */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022
     */
    String yyyy = "yyyy";


    /* yyyy-MM */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08
     */
    String yyyy_MM = "yyyy-MM";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08
     */
    String yyyy_MM_slash = "yyyy/MM";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 08-2022
     */
    String MM_yyyy = "MM_yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 08/2022
     */
    String MM_yyyy_slash = "MM/yyyy";

    /* yyyy-MM-dd */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06
     */
    String yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06
     */
    String yyyy_MM_dd_slash = "yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022
     */
    String dd_MM_yyyy = "dd-MM-yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022
     */
    String dd_MM_yyyy_slash = "dd/MM/yyyy";

    /* yyyy-MM-dd HH */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 17
     */
    String yyyy_MM_dd_HH = "yyyy-MM-dd HH";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 05
     */
    String yyyy_MM_dd_hh = "yyyy-MM-dd hh";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 05
     */
    String yyyy_MM_dd_hh_slash = "yyyy/MM/dd hh";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 17
     */
    String yyyy_MM_dd_HH_slash = "yyyy/MM/dd HH";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05 2022/08/06
     */
    String hh_yyyy_MM_dd_slash = "hh yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17 2022/08/06
     */
    String HH_yyyy_MM_dd_slash = "HH yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 17
     */
    String dd_MM_yyyy_HH = "dd-MM-yyyy HH";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 05
     */
    String dd_MM_yyyy_hh = "dd-MM-yyyy hh";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 05
     */
    String dd_MM_yyyy_hh_slash = "dd/MM/yyyy hh";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 17
     */
    String dd_MM_yyyy_HH_slash = "dd/MM/yyyy HH";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05 06/08/2022
     */
    String hh_dd_MM_yyyy_slash = "hh dd/MM/yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17 06/08/2022
     */
    String HH_dd_MM_yyyy_slash = "HH yyyy/MM/dd";

    /* yyyy-MM-dd HH:mm */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 17:03
     */
    String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 05:03
     */
    String yyyy_MM_dd_hh_mm = "yyyy-MM-dd hh:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 17:03
     */
    String yyyy_MM_dd_HH_mm_slash = "yyyy/MM/dd HH:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 05:03
     */
    String yyyy_MM_dd_hh_mm_slash = "yyyy/MM/dd hh:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03 2022-08-06
     */
    String HH_mm_yyyy_MM_dd = "HH:mm yyyy-MM-dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03 2022-08-06
     */
    String hh_mm_yyyy_MM_dd = "hh:mm yyyy-MM-dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03 2022/08/06
     */
    String HH_mm_yyyy_MM_dd_slash = "HH:mm yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03 2022/08/06
     */
    String hh_mm_yyyy_MM_dd_slash = "hh:mm yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 17:03
     */
    String dd_MM_yyyy_HH_mm = "dd-MM-yyyy HH:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 05:03
     */
    String dd_MM_yyyy_hh_mm = "dd-MM-yyyy hh:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 17:03
     */
    String dd_MM_yyyy_HH_mm_slash = "dd/MM/yyyy HH:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 05:03
     */
    String dd_MM_yyyy_hh_mm_slash = "yyyy/MM/dd hh:mm";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03 06-08-2022
     */
    String HH_mm_dd_MM_yyyy = "HH:mm dd-MM-yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03 06-08-2022
     */
    String hh_mm_dd_MM_yyyy = "hh:mm dd-MM-yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03 06/08/2022
     */
    String HH_mm_dd_MM_yyyy_slash = "HH:mm dd/MM/yyyy";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03 06/08/2022
     */
    String hh_mm_dd_MM_yyyy_slash = "hh:mm dd/MM/yyyy";

    /* yyyy-MM-dd hh:mm:ss */

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 17:03:01
     */
    String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 17:03:01
     */
    String yyyy_MM_dd_HH_mm_ss_slash = "yyyy/MM/dd HH:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03:01 2022-08-06
     */
    String HH_mm_ss_yyyy_MM_dd = "HH:mm:ss yyyy-MM-dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 17:03:01 2022/08/06
     */
    String HH_mm_ss_yyyy_MM_dd_slash = "HH:mm:ss yyyy/MM/dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 17:03:01
     */
    String dd_MM_yyyy_HH_mm_ss = "dd-MM-yyyy HH:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 17:03:01
     */
    String dd_MM_yyyy_HH_mm_ss_slash = "dd/MM/yyyy HH:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06-08-2022 05:03:01
     */
    String dd_MM_yyyy_hh_mm_ss = "dd-MM-yyyy hh:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 06/08/2022 05:03:01
     */
    String dd_MM_yyyy_hh_mm_ss_slash = "dd/MM/yyyy hh:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022-08-06 05:03:01
     */
    String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 2022/08/06 05:03:01
     */
    String yyyy_MM_dd_hh_mm_ss_slash = "yyyy/MM/dd hh:mm:ss";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03:01 2022-08-06
     */
    String hh_mm_ss_yyyy_MM_dd = "hh:mm:ss yyyy-MM-dd";

    /**
     * 当前时间为: 2022-08-06 17:03:01
     * 格式化后: 05:03:01 2022/08/06
     */
    String hh_mm_ss_yyyy_MM_dd_slash = "hh:mm:ss yyyy/MM/dd";

    /* d-MMM-uuuu hh:mm:ss */

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17:03:01
     */
    String d_MMM_uuuu_HH_mm_ss = "d-MMM-uuuu HH:mm:ss";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 05:03:01
     */
    String d_MMM_uuuu_hh_mm_ss = "d-MMM-uuuu hh:mm:ss";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17:03:01
     */
    String d_MMM_uuuu_HH_mm_ss_slash = "d/MMM/uuuu HH:mm:ss";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5/Aug/2022 05:03:01
     */
    String d_MMM_uuuu_hh_mm_ss_slash = "d/MMM/uuuu hh:mm:ss";

    /* d-MMM-uuuu hh:mm */

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17:03
     */
    String d_MMM_uuuu_HH_mm = "d-MMM-uuuu HH:mm";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 05:03
     */
    String d_MMM_uuuu_hh_mm = "d-MMM-uuuu hh:mm";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17:03
     */
    String d_MMM_uuuu_HH_mm_slash = "d/MMM/uuuu HH:mm";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5/Aug/2022 05:03:01
     */
    String d_MMM_uuuu_hh_mm_slash = "d/MMM/uuuu hh:mm";

    /* d-MMM-uuuu hh */

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17
     */
    String d_MMM_uuuu_HH = "d-MMM-uuuu HH";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 05
     */
    String d_MMM_uuuu_hh = "d-MMM-uuuu hh";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5-Aug-2022 17
     */
    String d_MMM_uuuu_HH_slash = "d/MMM/uuuu HH";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5/Aug/2022 05:03:01
     */
    String d_MMM_uuuu_hh_slash = "d/MMM/uuuu hh";

    /* d-MMM-uuuu */

    /**
     * 当前时间为: 2022-08-05
     * 格式化后: 5-Aug-2022
     */
    String d_MMM_uuuu = "d-MMM-uuuu";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5/Aug/2022
     */
    String d_MMM_uuuu_slash = "d/MMM/uuuu";

    /**
     * 当前时间为: 2022-08-05 17:03:01
     * 格式化后: 5 Aug 2022
     */
    String d_MMM_uuuu_black = "d MMM uuuu";

    /* LocalTime */

    /**
     * 时 分
     * 00:00
     */
    LocalTime HH_mm_min = LocalTime.of(0, 0);

    /**
     * 时 分 秒
     * 00:00:00
     */
    LocalTime HH_mm_ss_min = LocalTime.of(0, 0, 0);

    /**
     * 时 分
     * 23:59
     */
    LocalTime HH_mm_max = LocalTime.of(23, 59);

    /**
     * 时 分 秒
     * 23:59:59
     */
    LocalTime HH_mm_ss_max = LocalTime.of(23, 59, 59);

}
