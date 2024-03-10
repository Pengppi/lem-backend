/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 02:26 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.lem.supplier.command.AddSupplierCommand;
import com.agileboot.domain.lem.supplier.command.ChangeSupplierStatusCommand;
import com.agileboot.domain.lem.supplier.command.UpdateSupplierCommand;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import com.agileboot.domain.lem.supplier.db.SupplierService;
import com.agileboot.domain.lem.supplier.dto.SupplierNameDto;
import com.agileboot.domain.lem.supplier.model.SupplierModelFactory;
import com.agileboot.domain.lem.supplier.query.SupplierQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierApplicationService {
    
    private final SupplierService supplierService;
    
    private final SupplierModelFactory supplierModelFactory;
    
    public PageDTO<SupplierEntity> getSupplierList(SupplierQuery query) {
        Page<SupplierEntity> supplierList = supplierService.page(query.toPage(), query.toQueryWrapper());
        return new PageDTO<>(supplierList);
    }
    
    public List<SupplierNameDto> getSupplierNameList() {
        LambdaQueryWrapper<SupplierEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(SupplierEntity::getSupplierId, SupplierEntity::getName)
                .eq(SupplierEntity::getStatus, 1)
                .eq(SupplierEntity::getDeleted, 0);
        return supplierService.list(queryWrapper).stream().map(SupplierNameDto::new).collect(Collectors.toList());
    }
    
    public SupplierEntity getSupplierInfo(Long supplierId) {
        return supplierService.getById(supplierId);
    }
    
    public void addSupplier(AddSupplierCommand command) {
        supplierModelFactory.create()
                .loadAddSupplierCommand(command).insert();
    }
    
    public void updateSupplier(UpdateSupplierCommand command) {
        supplierModelFactory.loadById(command.getSupplierId())
                .loadUpdateSupplierCommand(command).updateById();
    }
    
    public void changeSupplierStatus(ChangeSupplierStatusCommand command) {
        supplierModelFactory.loadById(command.getSupplierId())
                .loadChangeStatusCommand(command).updateById();
    }
    
    public void deleteSupplier(BulkOperationCommand<Long> command) {
        for (Long id : command.getIds()) {
            supplierModelFactory.loadById(id).deleteById();
        }
    }
    
    public List<SupplierEntity> getSupplierListAll(SupplierQuery query) {
        return supplierService.list(query.toQueryWrapper());
    }
}
