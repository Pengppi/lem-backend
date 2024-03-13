/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:40 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.equipment.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateEquipmentCommand extends AddEquipmentCommand {
    Long equipmentId;
}
