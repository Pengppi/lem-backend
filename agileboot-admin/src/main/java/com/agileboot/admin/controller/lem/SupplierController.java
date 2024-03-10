/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:49 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.admin.controller.lem;


import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.lem.supplier.SupplierApplicationService;
import com.agileboot.domain.lem.supplier.command.AddSupplierCommand;
import com.agileboot.domain.lem.supplier.command.ChangeSupplierStatusCommand;
import com.agileboot.domain.lem.supplier.command.UpdateSupplierCommand;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import com.agileboot.domain.lem.supplier.dto.SupplierNameDto;
import com.agileboot.domain.lem.supplier.query.SupplierQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "厂商API", description = "厂商相关的增删查改")
@RestController
@RequestMapping("/lem/supplier")
@RequiredArgsConstructor
public class SupplierController {
    
    private final SupplierApplicationService supplierApplicationService;
    
    @Operation(summary = "厂商列表")
    @PreAuthorize("@permission.has('lem:supplier:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<SupplierEntity>> supplierList(SupplierQuery query) {
        PageDTO<SupplierEntity> page = supplierApplicationService.getSupplierList(query);
        return ResponseDTO.ok(page);
    }
    
    @Operation(summary = "厂商名称列表")
    @PreAuthorize("@permission.has('lem:supplier:list')")
    @GetMapping("/names")
    public ResponseDTO<List<SupplierNameDto>> supplierNameList( ){
        List<SupplierNameDto> list = supplierApplicationService.getSupplierNameList();
        return ResponseDTO.ok(list);
    }
    
    @Operation(summary = "厂商列表导出")
    @AccessLog(title = "厂商管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('lem:supplier:export')")
    @GetMapping("/excel")
    public void export(HttpServletResponse response, SupplierQuery query) {
        List<SupplierEntity> all = supplierApplicationService.getSupplierListAll(query);
        CustomExcelUtil.writeToResponse(all, SupplierEntity.class, response);
    }
    
    
    /**
     * 根据厂商编号获取详细信息
     */
    @Operation(summary = "厂商详情")
    @PreAuthorize("@permission.has('lem:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public ResponseDTO<SupplierEntity> getInfo(@PathVariable Long supplierId) {
        SupplierEntity supplier = supplierApplicationService.getSupplierInfo(supplierId);
        return ResponseDTO.ok(supplier);
    }
    
    /**
     * 新增厂商
     */
    @Operation(summary = "添加厂商")
    @PreAuthorize("@permission.has('lem:supplier:add')")
    @AccessLog(title = "厂商管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddSupplierCommand addCommand) {
        supplierApplicationService.addSupplier(addCommand);
        return ResponseDTO.ok();
    }
    
    /**
     * 修改厂商
     */
    @Operation(summary = "修改厂商")
    @PreAuthorize("@permission.has('lem:supplier:edit')")
    @AccessLog(title = "厂商管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@RequestBody UpdateSupplierCommand updateCommand) {
        supplierApplicationService.updateSupplier(updateCommand);
        return ResponseDTO.ok();
    }
    
    /**
     * 修改厂商状态
     */
    @Operation(summary = "修改厂商状态")
    @PreAuthorize("@permission.has('lem:supplier:edit')")
    @AccessLog(title = "厂商管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{supplierId}/status")
    public ResponseDTO<Void> editStatus(@PathVariable Long supplierId, @RequestBody ChangeSupplierStatusCommand command) {
        command.setSupplierId(supplierId);
        supplierApplicationService.changeSupplierStatus(command);
        return ResponseDTO.ok();
    }
    
    /**
     * 删除厂商
     */
    @Operation(summary = "删除厂商")
    @PreAuthorize("@permission.has('lem:supplier:remove')")
    @AccessLog(title = "厂商管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        supplierApplicationService.deleteSupplier(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }
}
