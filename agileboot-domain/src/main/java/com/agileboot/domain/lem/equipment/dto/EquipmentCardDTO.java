/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 06:32 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.dto;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import lombok.Data;

@Data
public class EquipmentCardDTO {
    Long equipmentId;
    String name;
    String model;
    String description;
    Integer status;
    
    public EquipmentCardDTO(EquipmentEntity entity) {
        BeanUtil.copyProperties(entity, this);
    }
}
