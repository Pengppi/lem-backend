/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:24 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.lem.equipment.command.AddEquipmentCommand;
import com.agileboot.domain.lem.equipment.command.ChangeEquipmentStatusCommand;
import com.agileboot.domain.lem.equipment.command.UpdateEquipmentCommand;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.equipment.db.EquipmentService;
import com.agileboot.domain.lem.equipment.dto.EquipmentCardDTO;
import com.agileboot.domain.lem.equipment.dto.EquipmentDTO;
import com.agileboot.domain.lem.equipment.model.EquipmentModel;
import com.agileboot.domain.lem.equipment.model.EquipmentModelFactory;
import com.agileboot.domain.lem.equipment.query.EquipmentCardQuery;
import com.agileboot.domain.lem.equipment.query.EquipmentQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentApplicationService {
    
    private final EquipmentService equipmentService;
    
    private final EquipmentModelFactory equipmentModelFactory;
    
    
    public PageDTO<EquipmentCardDTO> getCardList(EquipmentCardQuery query) {
        Page<EquipmentEntity> page = equipmentService.page(query.toPage(), query.toQueryWrapper());
        List<EquipmentCardDTO> cardList = page.getRecords().stream().map(EquipmentCardDTO::new).collect(Collectors.toList());
        return new PageDTO<>(cardList);
    }
    
    public PageDTO<EquipmentDTO> getEquipmentList(EquipmentQuery query) {
        Page<EquipmentDTO> equipmentList = equipmentService.selectJoinListPage(query.toPage(), EquipmentDTO.class, query.toQueryWrapper());
        return new PageDTO<>(equipmentList);
    }
    
    public EquipmentDTO getEquipmentInfo(Long equipmentId) {
        EquipmentQuery query = new EquipmentQuery();
        query.setEquipmentId(equipmentId);
        return equipmentService.selectJoinOne(EquipmentDTO.class, query.toQueryWrapper());
    }
    
    public void addEquipment(AddEquipmentCommand command) {
        equipmentModelFactory.create()
                .loadAddEquipmentCommand(command).insert();
    }
    
    public void updateEquipment(UpdateEquipmentCommand command) {
        equipmentModelFactory.loadById(command.getEquipmentId())
                .loadUpdateEquipmentCommand(command).updateById();
    }
    
    public void changeStatus(ChangeEquipmentStatusCommand command) {
        equipmentModelFactory.loadById(command.getEquipmentId())
                .loadChangeStatusCommand(command).updateById();
    }
    
    public void deleteEquipment(BulkOperationCommand<Long> command) {
        command.getIds().stream().map(equipmentModelFactory::loadById)
                .forEach(EquipmentModel::deleteById);
    }
    
    public List<EquipmentDTO> getEquipmentListAll(EquipmentQuery query) {
        return equipmentService.selectJoinList(EquipmentDTO.class, query.toQueryWrapper());
    }
}
