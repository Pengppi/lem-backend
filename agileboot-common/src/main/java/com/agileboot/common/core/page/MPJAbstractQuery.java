/**
 * @Author: Neo
 * @Date: 2024/03/09 星期六 04:34 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.common.core.page;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.utils.time.DatePickUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public abstract class MPJAbstractQuery<T> {
    private static final String ASC = "ascending";
    private static final String DESC = "descending";
    protected String orderColumn;
    protected String orderDirection;
    protected String timeRangeColumn;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    
    /**
     * 生成query conditions
     *
     * @return 添加条件后的QueryWrapper
     */
    public MPJLambdaWrapper<T> toQueryWrapper() {
        MPJLambdaWrapper<T> queryWrapper = addQueryCondition();
        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper);
        
        return queryWrapper;
    }
    
    public abstract MPJLambdaWrapper<T> addQueryCondition();
    
    public void addSortCondition(MPJLambdaWrapper<T> queryWrapper) {
        if (queryWrapper == null || StrUtil.isEmpty(orderColumn)) {
            return;
        }
        
        Boolean sortDirection = convertSortDirection();
        if (sortDirection != null) {
            queryWrapper.orderBy(StrUtil.isNotEmpty(orderColumn), sortDirection,
                    StrUtil.toUnderlineCase(orderColumn));
        }
    }
    
    public void addTimeCondition(MPJLambdaWrapper<T> queryWrapper) {
        if (queryWrapper != null
                && StrUtil.isNotEmpty(this.timeRangeColumn)) {
            queryWrapper
                    .ge(beginTime != null, StrUtil.toUnderlineCase(timeRangeColumn),
                            DatePickUtil.getBeginOfTheDay(beginTime))
                    .le(endTime != null, StrUtil.toUnderlineCase(timeRangeColumn), DatePickUtil.getEndOfTheDay(endTime));
        }
    }
    
    /**
     * 获取前端传来的排序方向  转换成MyBatisPlus所需的排序参数 boolean=isAsc
     *
     * @return 排序顺序， null为无排序
     */
    public Boolean convertSortDirection() {
        Boolean isAsc = null;
        if (StrUtil.isEmpty(this.orderDirection)) {
            return isAsc;
        }
        
        if (ASC.equals(this.orderDirection)) {
            isAsc = true;
        }
        if (DESC.equals(this.orderDirection)) {
            isAsc = false;
        }
        
        return isAsc;
    }
    
}
