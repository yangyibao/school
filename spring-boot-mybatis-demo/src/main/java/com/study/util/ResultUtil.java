package com.study.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    /**
     * 获取增加 修改 删除 的结果
     * @param num 操作数据
     * @return
     */
    public static Map<String,Object> getRsMap(Integer num){
        Map<String, Object> rsMap = new HashMap<>();
        String errorMsg = "";
        boolean succ = false;
        if(num > 0){
            succ =true;
        }else {
            errorMsg = "请于管理员联系!";
        }
        rsMap.put("flg", succ);
        rsMap.put("errorMsg", errorMsg);
        return rsMap;
    }

}
