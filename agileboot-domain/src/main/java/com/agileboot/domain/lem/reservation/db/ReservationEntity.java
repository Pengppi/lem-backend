/**
 * @Author: Neo
 * @Date: 2024/03/14 星期四 05:23 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.reservation.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@TableName("equipment_reservation")
@Data
public class ReservationEntity extends BaseEntity<ReservationEntity> {
    
    @TableId(value = "reservation_id", type = IdType.AUTO)
    private Long reservationId;
    
    @TableField("equipment_id")
    private Long equipmentId;
    
    @TableField("start_datetime")
    private Date startDatetime;
    
    @TableField("end_datetime")
    private Date endDatetime;
    
    @TableField("status")
    private Integer status;
    
    @TableField("review_remark")
    private String reviewRemark;
    
    @TableField("remark")
    private String remark;
}
