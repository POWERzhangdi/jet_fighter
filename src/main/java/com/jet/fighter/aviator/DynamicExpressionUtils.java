package com.jet.fighter.aviator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.exception.CompileExpressionErrorException;
import com.googlecode.aviator.exception.ExpressionRuntimeException;
import com.jet.fighter.collectios.CollectionUtils;
import com.jet.fighter.string.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 动态计算表达式
 * <p>
 * Description: {@link DynamicExpressionUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:57
 * @Version: v1.0
 */
public class DynamicExpressionUtils {

    /**
     * 四舍五入
     */
    private static final String ROUND = "round(a)";

    /**
     * scale自定义精度运算
     */
    private static final String SCALE = "scale(a,x,y)";

    private DynamicExpressionUtils() {
    }

    /**
     * Description: 根据表达式计算运算结果
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 返回 Integer 类型
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:37
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.lang.Integer
     */
    public static Integer executeInteger(String expression, Map<String, Integer> env) {
        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        Map<String, Object> oEnv = new HashMap<>(env.size());

        for (Map.Entry<String, Integer> entry : env.entrySet()) {
            oEnv.put(entry.getKey(), Long.valueOf(entry.getValue()));
        }

        Long result = (Long) AviatorEvaluator.execute(expression, oEnv, true);
        return result.intValue();
    }

    /**
     * Description: 根据表达式计算运算结果
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 返回 Long 类型
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:37
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.lang.Integer
     */
    public static Long executeLong(String expression, Map<String, Long> env) {
        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        Map<String, Object> oEnv = new HashMap<>(env.size());
        oEnv.putAll(env);

        return (Long) AviatorEvaluator.execute(expression, oEnv, true);
    }

    /**
     * Description: 根据表达式计算运算结果
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 返回 Double 类型
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:37
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.lang.Integer
     */
    public static Double executeDouble(String expression, Map<String, Double> env) {
        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        Map<String, Object> oEnv = new HashMap<>(env.size());
        oEnv.putAll(env);

        return (Double) AviatorEvaluator.execute(expression, oEnv, true);
    }

    /**
     * Description: 根据表达式计算运算结果
     * 返回 Boolean 类型
     * 需要特别注意 不支持 a > b > 1 这样的写法 必须使用 运算符 && || != ==
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:37
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.lang.Integer
     */
    public static Boolean executeBoolean(String expression, Map<String, Object> env) {
        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }
        return (Boolean) AviatorEvaluator.execute(expression, env, true);
    }

    /**
     * Description: 根据表达式计算运算结果
     * 返回 BigDecimal 类型
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 对返回结果数据进行 四舍五入
     * 这里需要注意 :
     * 当常量为 浮点的时候 表达式 格式:
     * 例如:  (a * 2.33) / (b * 3.33) 转换成 (a * 2.33M) / (b * 3.33M)
     * 当常量为 整数的时候 表达式 格式
     * 例如: (a * 2) / (b * 3)
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:57
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.math.BigDecimal
     */
    public static BigDecimal executeBigDecimalRound(String expression, Map<String, BigDecimal> env) {

        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }

        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        AviatorEvaluatorInstance instance = openHighPrecisionMeter();

        Map<String, Object> oEnv = new HashMap<>(env.size());
        oEnv.putAll(env);

        BigDecimal result = (BigDecimal) instance.execute(expression, oEnv);

        Map<String, Object> var = new HashMap<>(1);
        var.put("a", result);

        return (BigDecimal) instance.execute(ROUND, var);
    }

    /**
     * Description: 根据表达式计算运算结果
     * 返回 BigDecimal 类型
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 对返回结果数据进行 scale 运算
     * 这里需要注意 :
     * 当常量为 浮点的时候 表达式 格式:
     * 例如:  (a * 2.33) / (b * 3.33) 转换成 (a * 2.33M) / (b * 3.33M)
     * 当常量为 整数的时候 表达式 格式
     * 例如: (a * 2) / (b * 3)
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:57
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.math.BigDecimal
     */
    public static BigDecimal executeBigDecimalScale(String expression, Map<String, BigDecimal> env, Map<String, Integer> scale) {
        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        if (CollectionUtils.isEmpty(scale)) {
            throw new ExpressionRuntimeException("scale is null");
        }

        AviatorEvaluatorInstance instance = openHighPrecisionMeter();

        Map<String, Object> oEnv = new HashMap<>(env.size());
        oEnv.putAll(env);

        BigDecimal result = (BigDecimal) instance.execute(expression, oEnv);

        Map<String, Object> sEnv = new HashMap<>();
        sEnv.putAll(scale);
        sEnv.put("a", result);

        return (BigDecimal) instance.execute(SCALE, sEnv);
    }


    /**
     * Description: 根据表达式计算运算结果
     * 返回 BigDecimal 类型
     * 高精度运算
     * 运算 数据 不能出现非 < = > ! ? 等字符
     * 这里需要注意 :
     * 当常量为 浮点的时候 表达式 格式:
     * 例如:  (a * 2.33) / (b * 3.33) 转换成 (a * 2.33M) / (b * 3.33M)
     * 当常量为 整数的时候 表达式 格式
     * 例如: (a * 2) / (b * 3)
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 15:57
     * @Param [java.lang.String, java.util.Map<java.lang.String,java.lang.Object>]
     * @Return java.math.BigDecimal
     */
    public static BigDecimal executeHighPrecisionMeter(String expression, Map<String, BigDecimal> env) {

        if (StringUtils.isEmpty(expression)) {
            throw new CompileExpressionErrorException("expression is null");
        }
        if (CollectionUtils.isEmpty(env)) {
            throw new ExpressionRuntimeException("env is null");
        }

        //expression 转换

        AviatorEvaluatorInstance instance = openHighPrecisionMeter();

        Map<String, Object> oEnv = new HashMap<>(env.size());
        oEnv.putAll(env);

        return (BigDecimal) instance.execute(expression, oEnv);
    }


    /**
     * Description: 高精度计算对象 AviatorEvaluatorInstance
     *
     * @Author: di.zhang
     * @Date: 2022/8/3 18:56
     * @Param []
     * @Return com.googlecode.aviator.AviatorEvaluatorInstance
     */
    private static AviatorEvaluatorInstance openHighPrecisionMeter() {
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();
        //开启缓存
        instance.setCachedExpressionByDefault(true);
        //强制将所有浮点数（包括科学计数法）都解析为 decimal 类型
        instance.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL.getDefaultValue());
        //将所有整数解析为 decimal 类型
        instance.setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL.getDefaultValue());
        return instance;
    }

    /**
     * Description: 高精度运算的时候 给 运算符 里面的常量 + M 处理
     * TODO 后面在处理 有点麻烦
     * 当常量为 浮点的时候 处理
     * 例如:  (a * 2.33) / (b * 3.33) 转换成 (a * 2.33M) / (b * 3.33M)
     * 当常量为 整数的时候 不处理
     * 例如: (a * 2) / (b * 3) 转换成 (a * 2) / (b * 3)
     *
     * @Author: di.zhang
     * @Date: 2022/8/5 16:01
     * @Param [java.lang.String]
     * @Return java.lang.String
     */
    private static String parseExpression(String expression) {

        char[] chars = expression.toCharArray();


        return null;
    }

    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        LocalDate now1 = LocalDate.now().plusDays(1);
        LocalDate now2 = LocalDate.now().minusDays(1);
        LocalDate now3 = LocalDate.now().plusDays(2);


        TreeMap treeMap = new TreeMap();

        treeMap.put(now1,"3");
        treeMap.put(now2,"1");
        treeMap.put(now,"2");
        treeMap.put(now3,"4");
        System.out.println(treeMap);
    }
}
