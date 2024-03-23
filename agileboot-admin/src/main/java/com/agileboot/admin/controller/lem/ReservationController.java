/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 03:59 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.admin.controller.lem;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.domain.lem.reservation.ReservationApplicationService;
import com.agileboot.domain.lem.reservation.command.AddReservationCommand;
import com.agileboot.domain.lem.reservation.command.ChangeReservationStatusCommand;
import com.agileboot.domain.lem.reservation.dto.ReservationDTO;
import com.agileboot.domain.lem.reservation.dto.ReviewerDTO;
import com.agileboot.domain.lem.reservation.query.ReservationQuery;
import com.agileboot.infrastructure.user.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "预约设备API", description = "预约记录的增删查改")
@RestController
@RequestMapping("/lem/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationApplicationService reservationApplicationService;
    
    @Operation(summary = "查看预约记录")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<ReservationDTO>> getMyReservationRecord(@RequestBody ReservationQuery query) {
        query.setUserId(AuthenticationUtils.getUserId());
        return ResponseDTO.ok(reservationApplicationService.getReservationList(query));
    }
    
    
    @Operation(summary = "新增预约单")
    @PreAuthorize("@permission.has('lem:reservation:add')")
    @AccessLog(title = "预约设备", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddReservationCommand command) {
        reservationApplicationService.addReservation(command);
        return ResponseDTO.ok();
    }
    
    @Operation(summary = "获取审批人列表")
    @GetMapping("/reviewers")
    public ResponseDTO<List<ReviewerDTO>> getReviwers() {
        List<ReviewerDTO> reviewerList = reservationApplicationService.getReviewerList(AuthenticationUtils.getUserId());
        return ResponseDTO.ok(reviewerList);
    }
    
    @Operation(summary = "修改预约状态")
    @PreAuthorize("@permission.has('lem:reservation:edit')")
    @AccessLog(title = "预约状态更新", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{reservationId}/status")
    public ResponseDTO<Void> changeStatus(@PathVariable Long reservationId, @RequestBody ChangeReservationStatusCommand command) {
        command.setReservationId(reservationId);
        reservationApplicationService.changeReservationStatus(command);
        return ResponseDTO.ok();
    }
}
