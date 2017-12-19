package com.mysharding.parsing.parser.context.selectitem;

import com.google.common.base.Optional;

/**
 * Select item interface.
 *
 * @author zhangliang
 */
public interface SelectItem {
    
    /**
     * 获取表达式.
     * 
     * @return 表达式
     */
    String getExpression();
    
    /**
     * 获取别名.
     * 
     * @return 别名
     */
    Optional<String> getAlias();
}
