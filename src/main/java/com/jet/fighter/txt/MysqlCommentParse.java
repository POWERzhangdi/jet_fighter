package com.jet.fighter.txt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 解析 mysql 注释 为查询语句
 * <p>
 * Description: {@link MysqlCommentParse}
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public class MysqlCommentParse {

    /**
     * 注释示例：
     * sno         int unsigned auto_increment comment '学号',
     * name        varchar(50) not null comment '姓名',
     * create_date date not null comment '创建时间',
     * 解析后的sql:
     * select SNO as '学号',NAME as '姓名',CREATE_DATE as '创建时间' from account
     */
    public static void main(String[] args) {

        String fileName = "文件地址\\文件名称.txt";

        String table = "表名";

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            //分隔符
            sc.useDelimiter(",");
            //按分隔符读取字符串
            while (sc.hasNext()) {
                String str = sc.next().toUpperCase();

                //判断字段的位置 默认第一个空格出现的位置
                int indexColumn = str.indexOf(" ");

                //判断注释comment出现的最后位置
                int indexComment = str.lastIndexOf("COMMENT");
                //获取字段
                String column = str.substring(0,indexColumn);

                //获取字段注释
                String columnComment = str.substring(indexComment + 7, str.length());

                map.put(column, columnComment.trim());

            }


            //拼接成查询sql
            StringBuilder sb = new StringBuilder();
            sb.append("select ");
            for (Map.Entry<String, String> entry : map.entrySet()) {

                sb.append(entry.getKey()).append(" as ").append(entry.getValue()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" from ").append(table);

            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
