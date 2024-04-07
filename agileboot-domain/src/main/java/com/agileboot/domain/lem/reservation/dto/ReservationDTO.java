/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 02:28 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.dto;

import com.agileboot.common.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    @ExcelColumn(name = "预约ID")
    private Long reservationId;
    @ExcelColumn(name = "申请人")
    private String userName;
    @ExcelColumn(name = "审批人")
    private String reviewerName;
    @ExcelColumn(name = "所在部门")
    private String deptName;
    @ExcelColumn(name = "申请设备")
    private String equipmentName;
    @ExcelColumn(name = "设备型号")
    private String equipmentModel;
    @ExcelColumn(name = "申请状态")
    private Integer status;
    @ExcelColumn(name = "设备使用开始时间")
    private Date startDatetime;
    @ExcelColumn(name = "设备使用结束时间")
    private Date endDatetime;
    @ExcelColumn(name = "申请时间")
    private Date createTime;
    @ExcelColumn(name = "审批时间")
    private Date updateTime;
    private String remark;
    private String reviewRemark;
    
    public static void main(String[] args) {
        ReservationDTO reservationDTO = new ReservationDTO();
        
    }
}
