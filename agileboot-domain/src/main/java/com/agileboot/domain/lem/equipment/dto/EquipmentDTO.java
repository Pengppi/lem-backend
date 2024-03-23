/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 05:46 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.dto;

import com.agileboot.common.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

@Data
public class EquipmentDTO {
    @ExcelColumn(name = "设备ID")
    Long equipmentId;
    
    @ExcelColumn(name = "设备名称")
    String name;
    
    @ExcelColumn(name = "设备型号")
    String model;
    
    @ExcelColumn(name = "设备序列号")
    String serialNumber;
    
    @ExcelColumn(name = "生产日期")
    Date productionDate;
    
    @ExcelColumn(name = "购买日期")
    Date purchaseDate;
    
    @ExcelColumn(name = "供应商ID")
    Long supplierId;
    
    @ExcelColumn(name = "供应商名称")
    String supplierName;
    
    @ExcelColumn(name = "设备状态")
    Integer status;
    
    @ExcelColumn(name = "设备描述")
    String description;
    
    @ExcelColumn(name = "备注")
    String remark;
    
    @ExcelColumn(name = "创建者ID")
    private Long creatorId;
    
    @ExcelColumn(name = "创建者")
    private String creatorName;
    
    @ExcelColumn(name = "创建时间")
    private Date createTime;
    
    @ExcelColumn(name = "修改者ID")
    private Long updaterId;
    
    @ExcelColumn(name = "修改者")
    private String updaterName;
    
    @ExcelColumn(name = "修改时间")
    private Date updateTime;
}
