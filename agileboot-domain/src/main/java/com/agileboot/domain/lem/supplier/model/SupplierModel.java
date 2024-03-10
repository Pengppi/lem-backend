/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:54 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.agileboot.domain.lem.equipment.command.ChangeEquipmentStatusCommand;
import com.agileboot.domain.lem.supplier.command.AddSupplierCommand;
import com.agileboot.domain.lem.supplier.command.ChangeSupplierStatusCommand;
import com.agileboot.domain.lem.supplier.command.UpdateSupplierCommand;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SupplierModel extends SupplierEntity {
    
    public SupplierModel(SupplierEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }
    
    public SupplierModel loadUpdateSupplierCommand(UpdateSupplierCommand command) {
        if (command != null) {
            loadAddSupplierCommand(command);
        }
        return this;
    }
    
    public SupplierModel loadAddSupplierCommand(AddSupplierCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "supplierId");
        }
        return this;
    }
    
    public SupplierModel loadChangeStatusCommand(ChangeSupplierStatusCommand command) {
        if (command != null) {
            this.setStatus(Convert.toInt(command.getStatus()));
        }
        return this;
    }
}
