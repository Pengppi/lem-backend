/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:31 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.model;

import cn.hutool.core.convert.Convert;
import com.agileboot.domain.lem.equipment.command.AddEquipmentCommand;
import com.agileboot.domain.lem.equipment.command.ChangeEquipmentStatusCommand;
import com.agileboot.domain.lem.equipment.command.UpdateEquipmentCommand;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.equipment.db.EquipmentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentModel extends EquipmentEntity {
    private EquipmentService equipmentService;
    
    public EquipmentModel(EquipmentEntity entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }
    
    public EquipmentModel loadUpdateEquipmentCommand(UpdateEquipmentCommand command) {
        if (command != null) {
            loadAddEquipmentCommand(command);
        }
        return this;
    }
    
    public EquipmentModel loadAddEquipmentCommand(AddEquipmentCommand command) {
        if (command != null) {
            BeanUtils.copyProperties(command, this, "equipmentId");
        }
        return this;
    }
    
    public EquipmentModel loadChangeStatusCommand(ChangeEquipmentStatusCommand command) {
        if (command != null) {
            this.setStatus(Convert.toInt(command.getStatus()));
        }
        return this;
    }
}
