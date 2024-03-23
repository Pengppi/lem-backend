/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 02:28 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.dto;

import com.agileboot.domain.lem.approval.dto.ApprovalResult;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDTO {
    private Long reservationId;
    private String equipmentName;
    private Integer status;
    private Date startDatetime;
    private Date endDatetime;
    private Date createTime;
    private Date updateTime;
    private List<ApprovalResult> approvalResults;
}
