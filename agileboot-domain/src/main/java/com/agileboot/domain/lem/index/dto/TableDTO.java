/**
 * @Author: Neo
 * @Date: 2024/04/03 星期三 05:28 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import com.agileboot.domain.lem.index.bo.DailyReservationData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDTO extends DailyReservationData {
    private int userCount;
    private int reviewerCount;
    private int approvalCount;
    private int approvalRate;
    
    public TableDTO(String date) {
        super(date);
        userCount = 0;
        reviewerCount = 0;
        approvalCount = 0;
        approvalRate = 100;
    }
}
