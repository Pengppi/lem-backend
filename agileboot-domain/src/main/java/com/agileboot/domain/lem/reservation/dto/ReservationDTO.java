/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 02:28 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.dto;

import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.domain.lem.approval.dto.ApprovalResult;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDTO {
    @ExcelColumn(name = "预约ID")
    private Long reservationId;
    @ExcelColumn(name = "申请人")
    private String userName;
    @ExcelColumn(name = "申请设备")
    private String equipmentName;
    @ExcelColumn(name = "申请状态")
    private Integer status;
    @ExcelColumn(name = "设备使用开始时间")
    private Date startDatetime;
    @ExcelColumn(name = "设备使用结束时间")
    private Date endDatetime;
    @ExcelColumn(name = "申请时间")
    private Date createTime;
    @ExcelColumn(name = "更新时间")
    private Date updateTime;
    private List<ApprovalResult> approvalResults;
}
