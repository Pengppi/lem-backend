/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:36 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.equipment.db.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentModelFactory {
    private final EquipmentService equipmentService;
    
    public EquipmentModel loadById(Long equipmentId) {
        EquipmentEntity byId = equipmentService.getById(equipmentId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, equipmentId, "设备");
        }
        return new EquipmentModel(byId);
    }
    
    public EquipmentModel create() {
        return new EquipmentModel();
    }
}
