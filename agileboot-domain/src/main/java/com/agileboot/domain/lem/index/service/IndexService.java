/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 15:27
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.service;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.domain.lem.equipment.db.EquipmentEntity;
import com.agileboot.domain.lem.equipment.db.EquipmentService;
import com.agileboot.domain.lem.index.bo.ChartBO;
import com.agileboot.domain.lem.index.bo.DailyReservationData;
import com.agileboot.domain.lem.index.db.SQLFuncEnum;
import com.agileboot.domain.lem.index.dto.BarChartDTO;
import com.agileboot.domain.lem.index.dto.ChartDTO;
import com.agileboot.domain.lem.index.dto.ProgressDTO;
import com.agileboot.domain.lem.index.dto.TableDTO;
import com.agileboot.domain.lem.reservation.db.ReservationEntity;
import com.agileboot.domain.lem.reservation.db.ReservationService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndexService {
    private final ReservationService reservationService;
    private final EquipmentService equipmentService;
    
    public List<DailyReservationData> getLatestNewsData() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        LocalDate startDate = endDate.minusWeeks(2);
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getCreateTime, DailyReservationData::getDate)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getReservationId, DailyReservationData::getReservationCount)
                .selectFunc(() -> "sum(if(%s = 1,0,1))", ReservationEntity::getStatus, DailyReservationData::getReviewCount)
                .between(ReservationEntity::getCreateTime, startDate, endDate)
                .groupBy("date")
                .orderByDesc("date");
        return reservationService.selectJoinList(DailyReservationData.class, queryWrapper);
    }
    
    public List<ProgressDTO> getProgressData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusWeeks(1);
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getUpdateTime, ProgressDTO::getDate)
                .selectFunc(() -> "round(avg(if(%s = 2,1,0))*100)", ReservationEntity::getStatus, ProgressDTO::getPercentage)
                .between(ReservationEntity::getUpdateTime, startDate, endDate)
                .groupBy("date");
        Map<String, Integer> map = reservationService.selectJoinList(ProgressDTO.class, queryWrapper)
                .stream().collect(Collectors.toMap(ProgressDTO::getDate, ProgressDTO::getPercentage));
        LocalDate curDate = endDate;
        ProgressDTO[] arr = new ProgressDTO[7];
        while (!curDate.isEqual(startDate)) {
            int idx = 7 - curDate.getDayOfWeek().getValue();
            String dateStr = curDate.toString();
            arr[idx] = new ProgressDTO(dateStr, map.getOrDefault(dateStr, 100));
            curDate = curDate.minusDays(1);
        }
        return ListUtil.of(arr);
    }
    
    public List<ChartDTO> getChartData() {
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getReservationId, ChartBO::getTotalReservationCount)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getUpdaterId, ChartBO::getTotalReviewCount)
                .selectFunc(() -> "round(avg(if(%s = 2,1,0))*100)", EquipmentEntity::getStatus, ChartBO::getEquipmentUsedRate);
        ChartBO reservationBO = reservationService.selectJoinOne(ChartBO.class, queryWrapper);
        ChartDTO reservation = new ChartDTO();
        reservation.setName("预约总数");
        reservation.setValue(reservationBO.getTotalReservationCount());
        ChartDTO review = new ChartDTO();
        review.setName("审批总数");
        review.setValue(reservationBO.getTotalReviewCount());
        LocalDate today = LocalDate.now();
        getDataList(today.minusWeeks(1), today).forEach(data -> {
            reservation.getData().add(data.getReservationCount());
            review.getData().add(data.getReviewCount());
        });
        reservation.updateGrowthRatePercent();
        review.updateGrowthRatePercent();
        ChartDTO reviewRate = new ChartDTO("审批率", review.getValue() * 100 / reservation.getValue());
        MPJLambdaWrapper<EquipmentEntity> queryWrapper2 = new MPJLambdaWrapper<>();
        queryWrapper2.selectFunc(() -> "round(avg(if(%s = 2,1,0))*100)", EquipmentEntity::getStatus, ChartBO::getEquipmentUsedRate);
        ChartBO equipmentBO = equipmentService.selectJoinOne(ChartBO.class, queryWrapper2);
        ChartDTO equipmentUsedRate = new ChartDTO("设备使用率", equipmentBO.getEquipmentUsedRate());
        return ListUtil.of(reservation, review, reviewRate, equipmentUsedRate);
    }
    
    private List<DailyReservationData> getDataList(LocalDate startDate, LocalDate endDate) {
        HashMap<String, DailyReservationData> map = new HashMap<>();
        getReservationCountPerDay(startDate, endDate).forEach(dto -> map.put(dto.getDate(), dto));
        getReviewCountPerDay(startDate, endDate).forEach(dto -> {
            if (dto != null) {
                map.compute(dto.getDate(), (k, v) -> {
                    if (v == null) {
                        return dto;
                    } else {
                        v.setReviewCount(dto.getReviewCount());
                        return v;
                    }
                });
            }
        });
        LocalDate curDate = startDate;
        List<DailyReservationData> list = new ArrayList<>();
        while (!curDate.isAfter(endDate)) {
            String dateStr = curDate.toString();
            DailyReservationData data = map.get(dateStr);
            if (data == null) {
                data = new DailyReservationData(dateStr);
            }
            list.add(data);
            curDate = curDate.plusDays(1);
        }
        return list;
    }
    
    private List<DailyReservationData> getReservationCountPerDay(LocalDate startDate, LocalDate endDate) {
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getCreateTime, DailyReservationData::getDate)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getReservationId, DailyReservationData::getReservationCount)
                .between(ReservationEntity::getCreateTime, startDate, endDate)
                .groupBy("date");
        return reservationService.selectJoinList(DailyReservationData.class, queryWrapper);
    }
    
    private List<DailyReservationData> getReviewCountPerDay(LocalDate startDate, LocalDate endDate) {
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getUpdateTime, DailyReservationData::getDate)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getUpdaterId, DailyReservationData::getReviewCount)
                .between(ReservationEntity::getUpdateTime, startDate, endDate)
                .groupBy("date");
        return reservationService.selectJoinList(DailyReservationData.class, queryWrapper);
    }
    
    public List<BarChartDTO> getBarChartData() {
        LocalDate lastMonday = LocalDate.now().minusWeeks(1).with(java.time.DayOfWeek.MONDAY);
        LocalDate thisMonday = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        List<DailyReservationData> lastWeekData = getDataList(lastMonday, lastMonday.plusDays(6));
        List<DailyReservationData> thisWeekData = getDataList(thisMonday, thisMonday.plusDays(6));
        return ListUtil.of(new BarChartDTO(lastWeekData), new BarChartDTO(thisWeekData));
    }
    
    
    public List<TableDTO> getTableData() {
        MPJLambdaWrapper<ReservationEntity> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getCreateTime, TableDTO::getDate)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getReservationId, TableDTO::getReservationCount)
                .selectFunc(() -> "count(distinct %s)", ReservationEntity::getCreatorId, TableDTO::getUserCount)
                .groupBy("date");
        TreeMap<String, TableDTO> map = new TreeMap<>((a, b) -> b.compareTo(a));
        map.putAll(reservationService.selectJoinList(TableDTO.class, queryWrapper)
                .stream().collect(Collectors.toMap(TableDTO::getDate, data -> data)));
        queryWrapper.clear();
        queryWrapper.selectFunc(SQLFuncEnum.DATE, ReservationEntity::getUpdateTime, TableDTO::getDate)
                .selectFunc(SQLFuncEnum.COUNT, ReservationEntity::getUpdaterId, TableDTO::getReviewCount)
                .selectFunc(() -> "count(distinct %s)", ReservationEntity::getUpdaterId, TableDTO::getReviewerCount)
                .selectFunc(() -> "sum(if(%s=2,1,0))", ReservationEntity::getStatus, TableDTO::getApprovalCount)
                .groupBy("date");
        reservationService.selectJoinList(TableDTO.class, queryWrapper).forEach(data -> {
            if (data != null) {
                map.compute(data.getDate(), (k, v) -> {
                    if (v == null) {
                        return data;
                    } else {
                        v.setReviewCount(data.getReviewCount());
                        v.setReviewerCount(data.getReviewerCount());
                        v.setApprovalCount(data.getApprovalCount());
                        v.setApprovalRate(data.getApprovalCount() * 100 / data.getReviewCount());
                        return v;
                    }
                });
            }
        });
        return new ArrayList<>(map.values());
    }
}
