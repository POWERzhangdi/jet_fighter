package com.jet.fighter.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 没事多吃华莱士,喷射战士
 * oracel 将 注释 跟 查询语句融合，成注释需要的
 *
 * @author 张迪
 * @version 1.0.0
 * @ClassName OracelCommentPrase.java
 * @Description TODO
 * @createTime 2023年06月21日 16:44
 */
public class OracelCommentPrase {


    public static void main(String[] args) {

        String fileName = "D:\\截取数据oracel.txt";

        String table = "YL_TMS_ALL_FAST_ROUTE";

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            //分隔符
            sc.useDelimiter(";");
            while (sc.hasNext()) {   //按分隔符读取字符串
                String str = sc.next().toUpperCase();

                //判断表名字出现的位置
                int tableIndex = str.indexOf(table);

                //判断is最后出现的位置
                int isIndex = str.lastIndexOf("IS");
                //获取字段
                String column = str.substring(tableIndex + table.length() + 1, isIndex - 1);

                //获取字段注释
                String columnComment = str.substring(isIndex + 4, str.length() - 1);

                map.put(column, columnComment);

                System.out.println(str);
            }


            //拼接成查询sql
            StringBuilder sb = new StringBuilder();
            sb.append("select ");
            for (Map.Entry<String, String> entry : map.entrySet()) {

                sb.append(entry.getKey()).append(" as ").append("\"" + entry.getValue() + "\"").append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" from ").append(table);

            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
