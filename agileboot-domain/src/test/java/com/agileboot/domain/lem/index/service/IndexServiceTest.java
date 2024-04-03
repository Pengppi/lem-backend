/**
 * @Author: Neo
 * @Date: 2024/03/30 星期六 16:25
 * @Project: lem-backend
 * @IDE: IntelliJ IDEA
 **/

package com.agileboot.domain.lem.index.service;

import com.agileboot.domain.lem.index.bo.DailyReservationData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

@Slf4j
public class IndexServiceTest {
    private final IndexService indexService = mock(IndexService.class);
    
    @Test
    public void testGetTwoWeeksData() {
        List<DailyReservationData> twoWeeksData = indexService.getTwoWeeksData();
        log.info("twoWeeksData: {}", twoWeeksData);
        log.info("twoWeeksData size: {}", twoWeeksData.size());
        Assertions.assertNotNull(twoWeeksData);
    }
}
