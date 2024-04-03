/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:22 下午
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
import com.agileboot.domain.lem.equipment.EquipmentApplicationService;
import com.agileboot.domain.lem.equipment.command.AddEquipmentCommand;
import com.agileboot.domain.lem.equipment.command.ChangeEquipmentStatusCommand;
import com.agileboot.domain.lem.equipment.command.UpdateEquipmentCommand;
import com.agileboot.domain.lem.equipment.dto.EquipmentCardDTO;
import com.agileboot.domain.lem.equipment.dto.EquipmentDTO;
import com.agileboot.domain.lem.equipment.query.EquipmentCardQuery;
import com.agileboot.domain.lem.equipment.query.EquipmentQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "设备API", description = "设备相关的增删查改")
@RestController
@RequestMapping("/lem/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    
    private final EquipmentApplicationService equipmentApplicationService;
    
    @Operation(summary = "设备列表")
    @PreAuthorize("@permission.has('lem:equipment:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<EquipmentDTO>> equipmentList(EquipmentQuery query) {
        PageDTO<EquipmentDTO> page = equipmentApplicationService.getEquipmentList(query);
        return ResponseDTO.ok(page);
    }
    
    @Operation(summary = "设备卡片列表")
    @GetMapping("/cardList")
    public ResponseDTO<PageDTO<EquipmentCardDTO>> equipmentCardList(EquipmentCardQuery query) {
        PageDTO<EquipmentCardDTO> page = equipmentApplicationService.getCardList(query);
        return ResponseDTO.ok(page);
    }
    
    
    /**
     * 根据设备编号获取详细信息
     */
    @Operation(summary = "设备详情")
    @GetMapping("/{equipmentId}")
    public ResponseDTO<EquipmentDTO> getEquipmentInfo(@PathVariable Long equipmentId) {
        EquipmentDTO equipmentInfo = equipmentApplicationService.getEquipmentInfo(equipmentId);
        return ResponseDTO.ok(equipmentInfo);
    }
    
    
    @Operation(summary = "设备列表导出")
    @AccessLog(title = "设备管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('lem:equipment:export')")
    @GetMapping("/excel")
    public void export(HttpServletResponse response, EquipmentQuery query) {
        List<EquipmentDTO> all = equipmentApplicationService.getEquipmentListAll(query);
        CustomExcelUtil.writeToResponse(all, EquipmentDTO.class, response);
    }
    
    /**
     * 新增设备
     */
    @Operation(summary = "添加设备")
    @PreAuthorize("@permission.has('lem:equipment:add')")
    @AccessLog(title = "设备管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddEquipmentCommand command) {
        equipmentApplicationService.addEquipment(command);
        return ResponseDTO.ok();
    }
    
    /**
     * 修改设备
     */
    @Operation(summary = "修改设备")
    @PreAuthorize("@permission.has('lem:equipment:edit')")
    @AccessLog(title = "设备管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> update(@RequestBody UpdateEquipmentCommand command) {
        equipmentApplicationService.updateEquipment(command);
        return ResponseDTO.ok();
    }
    
    /**
     * 修改设备
     */
    @Operation(summary = "修改设备状态")
    @PreAuthorize("@permission.has('lem:equipment:edit')")
    @AccessLog(title = "设备管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{equipmentId}/status")
    public ResponseDTO<Void> changeStatus(@PathVariable Long equipmentId, @RequestBody ChangeEquipmentStatusCommand command) {
        command.setEquipmentId(equipmentId);
        equipmentApplicationService.changeStatus(command);
        return ResponseDTO.ok();
    }
    
    /**
     * 删除设备
     */
    @Operation(summary = "删除设备")
    @PreAuthorize("@permission.has('lem:equipment:remove')")
    @AccessLog(title = "设备管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> delete(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        equipmentApplicationService.deleteEquipment(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }
    
}
