/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 05:25 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.MPJAbstractPageQuery;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.equipment.dto.EquipmentDTO;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;

@Data
public class EquipmentQuery extends MPJAbstractPageQuery {
    
    Long equipmentId;
    Integer status;
    String name;
    String model;
    String serialNumber;
    String supplierName;
    
    @Override
    public MPJLambdaWrapper addQueryCondition() {
        MPJLambdaWrapper queryWrapper = new MPJLambdaWrapper<EquipmentEntity>()
                .selectAll(EquipmentEntity.class)
                .selectAs(SupplierEntity::getName, EquipmentDTO::getSupplierName)
                .selectAs(SysDeptEntity::getDeptName, EquipmentDTO::getDeptName)
                .leftJoin(SupplierEntity.class, SupplierEntity::getSupplierId, EquipmentEntity::getSupplierId)
                .leftJoin(SysDeptEntity.class, SysDeptEntity::getDeptId, EquipmentEntity::getDeptId)
                .eq(EquipmentEntity::getDeleted, 0)
                .eq(equipmentId != null, EquipmentEntity::getEquipmentId, equipmentId)
                .eq(status != null, EquipmentEntity::getStatus, status)
                .like(StrUtil.isNotEmpty(name), EquipmentEntity::getName, name)
                .like(StrUtil.isNotEmpty(model), EquipmentEntity::getModel, model)
                .like(StrUtil.isNotEmpty(serialNumber), EquipmentEntity::getSerialNumber, serialNumber)
                .like(StrUtil.isNotEmpty(supplierName), SupplierEntity::getName, supplierName);
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("purchase_date");
            this.setOrderDirection("descending");
        }
        this.setTimeRangeColumn("purchase_date");
        return queryWrapper;
    }
}
