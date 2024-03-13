/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:11 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.command;

import com.agileboot.common.annotation.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AddEquipmentCommand {
    
    @ExcelColumn(name = "设备名称")
    String name;
    
    @ExcelColumn(name = "设备型号")
    String model;
    
    @ExcelColumn(name = "设备序列号")
    String serialNumber;
    
    @ExcelColumn(name = "生产日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date productionDate;
    
    @ExcelColumn(name = "购买日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date purchaseDate;
    
    @ExcelColumn(name = "供应商ID")
    Long supplierId;
    
    @ExcelColumn(name = "状态")
    Integer status;
    
    @ExcelColumn(name = "备注")
    String remark;
}
