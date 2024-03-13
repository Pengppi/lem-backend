/**
 * @Author: Neo
 * @Date: 2024/03/02 星期六 05:31 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("equipment")
public class EquipmentEntity extends BaseEntity<EquipmentEntity> {
    
    @TableId(value = "equipment_id", type = IdType.AUTO)
    Long equipmentId;
    
    @TableField("name")
    String name;
    
    @TableField("model")
    String model;
    
    @TableField("serial_number")
    String serialNumber;
    
    @TableField("description")
    String description;
    
    @TableField("production_date")
    Date productionDate;
    
    @TableField("purchase_date")
    Date purchaseDate;
    
    @TableField("supplier_id")
    Long supplierId;
    
    @TableField("status")
    Integer status;
    
    @TableField("remark")
    String remark;
    
}
