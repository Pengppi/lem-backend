/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 02:03 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateSupplierCommand extends AddSupplierCommand {
    Long supplierId;
}
