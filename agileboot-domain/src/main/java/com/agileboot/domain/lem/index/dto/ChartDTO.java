/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 18:03
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChartDTO {
    private String name;
    private int value;
    private String percent;
    private List<Integer> data;
    
    public ChartDTO() {
        data = new ArrayList<>();
    }
    
    public ChartDTO(String name, int value) {
        this.name = name;
        this.value = value;
        data = new ArrayList<>();
        data.add(value);
    }
    
    public void updateGrowthRatePercent() {
        int size = data.size();
        int last = data.get(size - 2), cur = data.get(size - 1);
        if (last == 0) {
            this.percent = "0%";
            return;
        }
        int percent = (cur - last) * 100 / last;
        this.percent = percent > 0 ? "+" + percent + "%" : percent + "%";
    }
}
