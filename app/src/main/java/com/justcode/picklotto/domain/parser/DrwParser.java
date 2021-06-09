package com.justcode.picklotto.domain.parser;

import com.google.gson.JsonObject;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.data.vo.StatisticsVo;

import java.util.List;

public class DrwParser {

    public static DrwEntity getDrwEntity(JsonObject obj) {
        DrwEntity entity = new DrwEntity();
        entity.setDrwNo(obj.get("drwNo").getAsInt());
        entity.setReturnValue(obj.get("returnValue").getAsString());
        entity.setDrwNoDate(obj.get("drwNoDate").getAsString());
        entity.setTotSellamnt(obj.get("totSellamnt").getAsLong());
        entity.setFirstAccumamnt(obj.get("firstAccumamnt").getAsLong());
        entity.setFirstWinamnt(obj.get("firstWinamnt").getAsLong());
        entity.setFirstPrzwnerCo(obj.get("firstPrzwnerCo").getAsInt());
        entity.setDrwtNo1(obj.get("drwtNo1").getAsInt());
        entity.setDrwtNo2(obj.get("drwtNo2").getAsInt());
        entity.setDrwtNo3(obj.get("drwtNo3").getAsInt());
        entity.setDrwtNo4(obj.get("drwtNo4").getAsInt());
        entity.setDrwtNo5(obj.get("drwtNo5").getAsInt());
        entity.setDrwtNo6(obj.get("drwtNo6").getAsInt());
        entity.setBnusNo(obj.get("bnusNo").getAsInt());
        return entity;
    }

    public static StatisticsVo getDrwAverage(List<DrwEntity> list) {
        StatisticsVo vo = new StatisticsVo();
        for (int i = 0; i < list.size(); i++) {
            DrwEntity entity = list.get(i);
            for (int j = 1; j <= 45; j++) {
                if (entity.getDrwtNo1() == j) {

                } else if (entity.getDrwtNo2() == j) {

                } else if (entity.getDrwtNo3() == j) {

                } else if (entity.getDrwtNo4() == j) {

                } else if (entity.getDrwtNo5() == j) {

                } else if (entity.getDrwtNo6() == j) {

                } else if (entity.getBnusNo() == j) {

                }
            }
        }
        return vo;
    }

}
