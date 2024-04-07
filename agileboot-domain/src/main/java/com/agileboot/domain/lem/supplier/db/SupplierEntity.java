/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:44 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.db;


import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.annotation.ExcelSheet;
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
@ExcelSheet(name = "供应商列表")
public class SupplierEntity extends BaseEntity<SupplierEntity> {
    
    @TableId(value = "supplier_id", type = IdType.AUTO)
    @ExcelColumn(name = "供应商ID")
    Long supplierId;
    
    @TableField("name")
    @ExcelColumn(name = "供应商名称")
    String name;
    
    @TableField("contact_person")
    @ExcelColumn(name = "联系人")
    String contactPerson;
    
    @TableField("contact_phone")
    @ExcelColumn(name = "联系电话")
    String contactPhone;
    
    @TableField("contact_address")
    @ExcelColumn(name = "联系地址")
    String contactAddress;
    
    @TableField("status")
    @ExcelColumn(name = "状态")
    Integer status;
    
    @TableField("remark")
    String remark;
    
}
