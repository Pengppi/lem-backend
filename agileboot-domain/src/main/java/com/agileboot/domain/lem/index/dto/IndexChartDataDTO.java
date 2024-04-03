/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 17:20
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import com.agileboot.domain.lem.index.bo.DailyReservationData;
import lombok.Data;

import java.util.List;

@Data
public class IndexChartDataDTO {
    private int totalReservationCount;
    private int totalReviewCount;
    private int totalAgreementCount;
    private String reviewRate;
    private String reservationGrowthRate;
    private String reviewGrowthRate;
    private String agreementGrowthRate;
    private int todayIndex;
    private List<DailyReservationData> twoWeeksData;
}
