/**
 * @Author: Neo
 * @Date: 2024/03/18 星期一 11:32 上午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.EquipmentStatusEnum;
import com.agileboot.domain.lem.equipment.EquipmentApplicationService;
import com.agileboot.domain.lem.equipment.command.ChangeEquipmentStatusCommand;
import com.agileboot.domain.lem.reservation.command.AddReservationCommand;
import com.agileboot.domain.lem.reservation.command.ChangeReservationStatusCommand;
import com.agileboot.domain.lem.reservation.db.ReservationService;
import com.agileboot.domain.lem.reservation.dto.ReservationDTO;
import com.agileboot.domain.lem.reservation.dto.ReviewerDTO;
import com.agileboot.domain.lem.reservation.model.ReservationModel;
import com.agileboot.domain.lem.reservation.model.ReservationModelFactory;
import com.agileboot.domain.lem.reservation.query.ReservationQuery;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.dept.db.SysDeptMapper;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationApplicationService {
    
    private final ReservationService reservationService;
    
    private final ReservationModelFactory reservationModelFactory;
    
    private final EquipmentApplicationService equipmentApplicationService;
    
    private final SysDeptMapper sysDeptMapper;
    
    public List<ReviewerDTO> getReviewerList(Long userId) {
        MPJLambdaWrapper<SysDeptEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.select(SysDeptEntity::getParentId)
                .rightJoin(SysUserEntity.class, SysUserEntity::getDeptId, SysDeptEntity::getDeptId)
                .eq(SysUserEntity::getUserId, userId);
        Long parentDeptId = sysDeptMapper.selectOne(queryWrapper).getParentId();
        queryWrapper.clear();
        queryWrapper.distinct().selectAs(SysDeptEntity::getLeaderId, ReviewerDTO::getReviewerId)
                .selectAs(SysDeptEntity::getLeaderName, ReviewerDTO::getReviewerName)
                .eq(SysDeptEntity::getDeptId, parentDeptId)
                .or()
                .eq(SysDeptEntity::getParentId, parentDeptId);
        return sysDeptMapper.selectJoinList(ReviewerDTO.class, queryWrapper);
    }
    
    public List<ReservationDTO> getReservationList(ReservationQuery query) {
        List<ReservationDTO> reservationList = reservationService.selectJoinList(ReservationDTO.class, query.toQueryWrapper());
        return reservationList;
    }
    
    // todo 修改获取审批人的方式
    public PageDTO<ReservationDTO> getReservationPage(ReservationQuery query) {
        Page<ReservationDTO> reservationPage = reservationService.selectJoinListPage(query.toPage(), ReservationDTO.class, query.toQueryWrapper());
        return new PageDTO<>(reservationPage);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public void addReservation(AddReservationCommand command) {
        Long equipmentId = command.getEquipmentId();
        equipmentApplicationService.changeStatus(
                new ChangeEquipmentStatusCommand(equipmentId, EquipmentStatusEnum.IN_USE.getValue()));
        ReservationModel reservationModel = reservationModelFactory.create();
        reservationModel.loadAddReservationCommand(command);
        reservationModel.insert();
    }
    
    public void changeReservationStatus(ChangeReservationStatusCommand command) {
        ReservationModel reservationModel = reservationModelFactory.loadById(command.getReservationId());
        reservationModel.setStatus(command.getStatus());
        reservationModel.setReviewRemark(command.getReviewRemark());
        reservationModel.updateById();
    }
    
}
