package com.agileboot.common.enums.common;

import com.agileboot.common.enums.DictionaryEnum;
import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import lombok.AllArgsConstructor;

@Dictionary(name = "approval.status")
@AllArgsConstructor
public enum ApprovalStatusEnum implements DictionaryEnum<Integer> {
    CANCEL(0, "预约取消", CssTag.WARNING),
    WAITING(1, "审批待处理", CssTag.INFO),
    APPROVE(2, "审批通过", CssTag.SUCCESS),
    REJECT(3, "审批拒绝", CssTag.ERROR),
    ;
    
    private final int value;
    private final String description;
    private final String cssTag;
    
    @Override
    public Integer getValue() {
        return value;
    }
    
    @Override
    public String description() {
        return description;
    }
    
    @Override
    public String cssTag() {
        return cssTag;
    }
}
