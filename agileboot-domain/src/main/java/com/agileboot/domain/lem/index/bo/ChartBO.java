/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 18:13
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.bo;

import lombok.Data;

@Data
public class ChartBO {
    private int totalReservationCount;
    private int totalReviewCount;
    private int EquipmentUsedRate;
}
