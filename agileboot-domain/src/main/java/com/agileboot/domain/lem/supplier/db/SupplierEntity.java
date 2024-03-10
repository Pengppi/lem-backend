/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:44 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.db;


import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("supplier")
public class SupplierEntity extends BaseEntity<SupplierEntity> {
    
    @TableId(value = "supplier_id", type = IdType.AUTO)
    Long supplierId;
    
    @TableField("name")
    String name;
    
    @TableField("contact_person")
    String contactPerson;
    
    @TableField("contact_phone")
    String contactPhone;
    
    @TableField("contact_address")
    String contactAddress;
    
    @TableField("status")
    Integer status;
    
    @TableField("remark")
    String remark;
    
}
