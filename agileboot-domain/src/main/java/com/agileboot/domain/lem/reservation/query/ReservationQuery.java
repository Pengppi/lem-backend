/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 12:22 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.MPJAbstractPageQuery;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.reservation.db.ReservationEntity;
import com.agileboot.domain.lem.reservation.dto.ReservationDTO;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;

@Data
public class ReservationQuery extends MPJAbstractPageQuery {
    
    private Long userId;
    
    private String equipmentName;
    
    private Integer status;
    
    @Override
    public MPJLambdaWrapper addQueryCondition() {
        MPJLambdaWrapper queryWrapper = new MPJLambdaWrapper<ReservationEntity>()
                .selectAll(ReservationEntity.class)
                .selectAs(EquipmentEntity::getName, ReservationDTO::getEquipmentName)
                .selectAs(SysUserEntity::getUsername, ReservationDTO::getUserName)
                .leftJoin(EquipmentEntity.class, EquipmentEntity::getEquipmentId, ReservationEntity::getEquipmentId)
                .leftJoin(SysUserEntity.class, SysUserEntity::getUserId, ReservationEntity::getCreatorId)
                .eq(userId != null, ReservationEntity::getCreatorId, userId)
                .eq(equipmentName != null, EquipmentEntity::getName, equipmentName)
                .eq(status != null, ReservationEntity::getStatus, status);
        
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("create_time");
            this.setOrderDirection("descending");
        }
        this.setTimeRangeColumn("create_time");
        
        return queryWrapper;
    }
}
