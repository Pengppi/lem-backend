/**
 * @Author: Neo
 * @Date: 2024/03/03 星期日 02:14 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.supplier.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.MPJAbstractPageQuery;
import com.agileboot.domain.lem.supplier.db.SupplierEntity;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SupplierQuery extends MPJAbstractPageQuery {
    
    protected Long supplierId;
    protected String name;
    protected String contactPerson;
    protected String contactPhone;
    protected String contactAddress;
    
    
    @Override
    public MPJLambdaWrapper<SupplierEntity> addQueryCondition() {
        MPJLambdaWrapper queryWrapper = new MPJLambdaWrapper<>().selectAll(SupplierEntity.class)
                .eq(supplierId != null, SupplierEntity::getSupplierId, supplierId)
                .like(StrUtil.isNotEmpty(name), SupplierEntity::getName, name)
                .like(StrUtil.isNotEmpty(contactPerson), SupplierEntity::getContactPerson, contactPerson)
                .like(StrUtil.isNotEmpty(contactPhone), SupplierEntity::getContactPhone, contactPhone)
                .like(StrUtil.isNotEmpty(contactAddress), SupplierEntity::getContactAddress, contactAddress)
                .eq(SupplierEntity::getDeleted, 0);
        
        this.timeRangeColumn = "create_time";
        
        return queryWrapper;
    }
}
