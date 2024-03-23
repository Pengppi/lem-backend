/**
 * @Author: Neo
 * @Date: 2024/03/14 星期四 05:58 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.domain.lem.reservation.command.AddReservationCommand;
import com.agileboot.domain.lem.reservation.db.ReservationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ReservationModel extends ReservationEntity {
    
    public ReservationModel(ReservationEntity entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }
    
    
    public void loadAddReservationCommand(AddReservationCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "reviewerIds");
        }
    }
}
