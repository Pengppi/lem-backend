package com.agileboot.common.enums.common;

import com.agileboot.common.enums.DictionaryEnum;
import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import lombok.AllArgsConstructor;

@Dictionary(name = "reservation.status")
@AllArgsConstructor
public enum ReservationStatusEnum implements DictionaryEnum<Integer> {
    CANCEL(0, "已取消", CssTag.WARNING),
    WAITING(1, "待审批", CssTag.INFO),
    APPROVE(2, "已同意", CssTag.SUCCESS),
    REJECT(3, "已拒绝", CssTag.ERROR),
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
