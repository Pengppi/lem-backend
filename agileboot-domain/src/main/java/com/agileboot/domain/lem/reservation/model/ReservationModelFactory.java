/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 11:21 上午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.lem.reservation.db.ReservationEntity;
import com.agileboot.domain.lem.reservation.db.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationModelFactory {
    
    private final ReservationService reservationService;
    
    public ReservationModel loadById(Long reservationId) {
        ReservationEntity byId = reservationService.getById(reservationId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, reservationId, "预约记录");
        }
        return new ReservationModel(byId);
    }
    
    public ReservationModel create() {
        return new ReservationModel();
    }
}
