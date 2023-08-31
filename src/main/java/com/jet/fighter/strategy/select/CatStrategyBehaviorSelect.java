package com.jet.fighter.strategy.select;

import com.jet.fighter.strategy.StrategyBehaviorSelect;
import com.jet.fighter.strategy.StrategyEnums;

/**
 * 云路供应链科技有限公司 版权所有 © Copyright 2021<br>
 * <p>
 *
 * @Author: di.zhang
 * @Date: 2023/8/30 17:12
 */
public class CatStrategyBehaviorSelect implements StrategyBehaviorSelect<String> {


    @Override
    public StrategyEnums strategy() {
        return StrategyEnums.CAT;
    }

    @Override
    public String behavior(String s) {
        return s + "行为:喵喵";
    }
}
