/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:55 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddSupplierCommand {
    
    @NotNull(message = "供应商名称不能为空")
    @Size(max = 64, message = "供应商名称长度不能超过64个字符")
    private String name;
    
    @Size(max = 32, message = "联系人名称长度不能超过32个字符")
    private String contactPerson;
    
    @Size(max = 32, message = "联系号码长度不能超过32个字符")
    private String contactPhone;
    
    @Size(max = 128, message = "联系地址长度不能超过128个字符")
    private String contactAddress;
    
    @Size(max = 256, message = "备注内容长度不能超过256个字符")
    private String remark;
}
