package com.jet.fighter.txt;

import com.jet.fighter.date.DateFormat;
import com.jet.fighter.date.DateTimeUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 解析 Oracle 注释 为查询语句
 * <p>
 * Description: {@link OracleCommentParse}
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public class OracleCommentParse {


    /**
     * 示例：
     * comment on column table.name IS '姓名';
     * comment on column table.age IS '年龄';
     * 解析后的sql:
     * select name as "姓名",age as "年龄" from table
     */
    public static void main(String[] args) {

        String fileName = "文件地址\\文件名称.txt";

        String table = "表明";

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
