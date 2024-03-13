/**
 * @Author: Neo
 * @Date: 2024/03/02 星期六 06:13 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.common.enums.common;

import com.agileboot.common.enums.DictionaryEnum;
import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import lombok.AllArgsConstructor;

@Dictionary(name = "equipment.status")
@AllArgsConstructor
public enum EquipmentStatusEnum implements DictionaryEnum<Integer> {
    DISPOSED(0, "已停用", CssTag.DANGER),
    AVAILABLE(1, "可使用", CssTag.SUCCESS),
    IN_USE(2, "使用中", CssTag.PRIMARY),
    MAINTENANCE(3, "检修中", CssTag.WARNING),
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
