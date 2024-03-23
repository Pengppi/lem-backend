/**
 * @Author: Neo
 * @Date: 2024/03/20 星期三 12:12 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.dto;

import lombok.Data;

@Data
public class ReviewerDTO {
    private Long reviewerId;
    private String reviewerName;
}
