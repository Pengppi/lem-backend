/**
 * @Author: Neo
 * @Date: 2024/03/09 星期六 06:11 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.dto;

import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import lombok.Data;

@Data
public class SupplierNameDto {
    private Long supplierId;
    private String name;
    
    public SupplierNameDto(Long supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
    }
    
    public SupplierNameDto(SupplierEntity entity) {
        this.supplierId = entity.getSupplierId();
        this.name = entity.getName();
    }
}
