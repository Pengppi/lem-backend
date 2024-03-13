/**
 * @Author: Neo
 * @Date: 2024/03/04 星期一 12:45 上午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class EquipmentCardQuery extends AbstractPageQuery {
    
    String name;
    String model;
    Integer status;
    
    @Override
    public QueryWrapper addQueryCondition() {
        QueryWrapper<EquipmentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("equipment_id", "name", "model", "description", "status")
                .like(StrUtil.isNotEmpty(name), "name", name)
                .like(StrUtil.isNotEmpty(model), "model", model)
                .eq(status != null, "status", status);
        return queryWrapper;
    }
}
