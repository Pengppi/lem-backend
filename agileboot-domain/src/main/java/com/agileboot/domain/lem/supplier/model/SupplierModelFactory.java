/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 02:09 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import com.agileboot.domain.lem.supplier.db.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierModelFactory {
    private final SupplierService supplierService;
    
    public SupplierModel loadById(Long supplierId) {
        SupplierEntity byId = supplierService.getById(supplierId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, supplierId, "供应商");
        }
        return new SupplierModel(byId);
    }
    
    public SupplierModel create() {
        return new SupplierModel();
    }
}
