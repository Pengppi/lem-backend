/**
 * @Author: Neo
 * @Date: 2024/03/14 星期四 05:41 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddReservationCommand {
    
    private Long equipmentId;
    
    private List<Long> reviewerIds;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDatetime;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDatetime;
    
    private String remark;
}
