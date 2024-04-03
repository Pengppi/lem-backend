/**
 * @Author: Neo
 * @Date: 2024/04/03 星期三 02:12 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import com.agileboot.domain.lem.index.bo.DailyReservationData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarChartDTO {
    List<Integer> reviewData;
    List<Integer> ReservationData;
    
    
    public BarChartDTO(List<DailyReservationData> oneWeekData) {
        reviewData = oneWeekData.stream().map(DailyReservationData::getReviewCount).collect(Collectors.toList());
        ReservationData = oneWeekData.stream().map(DailyReservationData::getReservationCount).collect(Collectors.toList());
    }
}
