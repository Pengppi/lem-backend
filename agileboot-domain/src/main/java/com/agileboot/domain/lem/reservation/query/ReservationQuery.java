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
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationQuery extends MPJAbstractPageQuery {
    
    private Long userId;
    private String equipmentName;
    private String deptName;
    private String userName;
    private String reviewerName;
    private Integer status;
    
    @Override
    public MPJLambdaWrapper<ReservationEntity> addQueryCondition() {
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<ReservationEntity>()
                .selectAll(ReservationEntity.class)
                .selectAs(EquipmentEntity::getName, ReservationDTO::getEquipmentName)
                .selectAs(EquipmentEntity::getModel, ReservationDTO::getEquipmentModel)
                .selectAs(SysDeptEntity::getDeptName, ReservationDTO::getDeptName)
                .selectAs("u1", SysUserEntity::getUsername, ReservationDTO::getUserName)
                .selectAs("u2", SysUserEntity::getUsername, ReservationDTO::getReviewerName)
                .leftJoin(SysUserEntity.class, "u1", SysUserEntity::getUserId, ReservationEntity::getCreatorId)
                .leftJoin(SysUserEntity.class, "u2", SysUserEntity::getUserId, ReservationEntity::getUpdaterId)
                .leftJoin(EquipmentEntity.class, EquipmentEntity::getEquipmentId, ReservationEntity::getEquipmentId)
                .leftJoin(SysDeptEntity.class, SysDeptEntity::getDeptId, "u1", SysUserEntity::getDeptId)
                .eq(userId != null, ReservationEntity::getCreatorId, userId)
                .eq(status != null, ReservationEntity::getStatus, status)
                .like(StrUtil.isNotEmpty(equipmentName), EquipmentEntity::getName, equipmentName)
                .like(StrUtil.isNotEmpty(deptName), SysDeptEntity::getDeptName, deptName)
                .like(StrUtil.isNotEmpty(userName), "u1", SysUserEntity::getUsername, userName)
                .like(StrUtil.isNotEmpty(reviewerName), "u2", SysUserEntity::getUsername, reviewerName);
        
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("t.create_time");
            this.setOrderDirection("descending");
        }
        this.setTimeRangeColumn("t.create_time");
        
        return queryWrapper;
    }
}
