/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 01:39 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.command;

import lombok.Data;

@Data
public class ChangeSupplierStatusCommand {
    Long supplierId;
    Integer status;
}
