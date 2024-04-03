/**
 * @Author: Neo
 * @Date: 2024/04/03 星期三 04:50 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import lombok.Data;

@Data
public class LatestNewsDTO {
    private String date;
    private int reservationNumber;
    private int reviewNumber;
    
}
