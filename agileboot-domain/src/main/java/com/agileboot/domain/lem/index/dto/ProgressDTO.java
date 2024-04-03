/**
 * @Author: Neo
 * @Date: 2024/04/03 星期三 03:13 下午
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.dto;

import lombok.Data;

@Data
public class ProgressDTO {
    private String color = "#41b6ff";
    private String date;
    private int percentage;
    
    public ProgressDTO(String date, int percentage) {
        this.percentage = percentage;
        this.date = date;
        updateColor();
    }
    
    private void updateColor() {
        if (percentage >= 80) {
            color = "#26ce83";
        }
    }
}
