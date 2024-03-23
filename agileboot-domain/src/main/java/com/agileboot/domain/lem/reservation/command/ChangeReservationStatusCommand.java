/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 12:08 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeReservationStatusCommand {
    private Long reservationId;
    
    private Integer status;
}
