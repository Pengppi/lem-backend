/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 15:06
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.admin.controller.lem;

import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.domain.lem.index.bo.DailyReservationData;
import com.agileboot.domain.lem.index.dto.BarChartDTO;
import com.agileboot.domain.lem.index.dto.ChartDTO;
import com.agileboot.domain.lem.index.dto.ProgressDTO;
import com.agileboot.domain.lem.index.dto.TableDTO;
import com.agileboot.domain.lem.index.service.IndexService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "首页API", description = "首页相关数据的查询")
@Slf4j
@RestController
@RequestMapping("/lem/index")
@RequiredArgsConstructor
public class IndexController {
    
    private final IndexService indexService;
    
    
    @GetMapping("/chart")
    public ResponseDTO<List<ChartDTO>> getChartData() {
        return ResponseDTO.ok(indexService.getChartData());
    }
    
    @GetMapping("/barChart")
    public ResponseDTO<List<BarChartDTO>> getBarChartData() {
        return ResponseDTO.ok(indexService.getBarChartData());
    }
    
    @GetMapping("/progress")
    public ResponseDTO<List<ProgressDTO>> getProgressData() {
        return ResponseDTO.ok(indexService.getProgressData());
    }
    
    @GetMapping("/latestNews")
    public ResponseDTO<List<DailyReservationData>> getLatestNewsData() {
        return ResponseDTO.ok(indexService.getLatestNewsData());
    }
    
    @GetMapping("/table")
    public ResponseDTO<List<TableDTO>> getTableData() {
        return ResponseDTO.ok(indexService.getTableData());
    }
}
