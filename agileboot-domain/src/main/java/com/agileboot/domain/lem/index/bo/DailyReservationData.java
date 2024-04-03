/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 15:18
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyReservationData {
    private String date;
    private int reservationCount;
    private int reviewCount;
    
    
    public DailyReservationData(String date) {
        this.date = date;
        reservationCount = 0;
        reviewCount = 0;
    }
}
