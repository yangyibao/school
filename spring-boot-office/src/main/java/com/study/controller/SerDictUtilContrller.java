package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可以读取系统字典 相关key 然后转换为map
 *
 */
@RestController
public class SerDictUtilContrller {

//    @Resource
//    private SerDictController serDictController;
//    @Resource
//    private SerDictMapper serDictMapper;
//
//    @RequestMapping
//    @ResponseBody
//    public Map<String, String> getDictMap(String... dictCode){
//        Map<String,String> rsMap = Maps.newHashMap();
//        Map<String, List<SerDict>> map = queryDictByCode(dictCode);
//        for(Map.Entry<String, List<SerDict>> entry:map.entrySet()){
//            String key = entry.getKey();
//            List<SerDict> list = entry.getValue();
//            for (SerDict serDict:list) {
//                rsMap.put(key+"_"+serDict.getDictKey(), serDict.getDictVal());
//            }
//        }
//        return rsMap;
//    }
//
//    @RequestMapping("/getDictVal")
//    @ResponseBody
//    public Map<String,Integer> getDictVal(String dictCode){
//        Map<String,Integer> rsMap = Maps.newHashMap();
//        Map<String, List<SerDict>> map = queryDictByCode(dictCode);
//        for(Map.Entry<String, List<SerDict>> entry:map.entrySet()){
//            String key = entry.getKey();
//            List<SerDict> list = entry.getValue();
//            for (SerDict serDict:list) {
//                rsMap.put(serDict.getDictVal(),serDict.getDictKey());
//            }
//        }
//        return rsMap;
//    }
//
//    @RequestMapping("/getDictId")
//    @ResponseBody
//    public Map<String,Integer> getDictId(String dictCode){
//        Map<String,Integer> rsMap = Maps.newHashMap();
//        Map<String, List<SerDict>> map = queryDictByCode(dictCode);
//        for(Map.Entry<String, List<SerDict>> entry:map.entrySet()){
//            String key = entry.getKey();
//            List<SerDict> list = entry.getValue();
//            for (SerDict serDict:list) {
//                rsMap.put(serDict.getDictVal(),serDict.getDictId());
//            }
//        }
//        return rsMap;
//    }
//
//    /*    @RequestMapping
//        @ResponseBody*/
//    public Map<String, Integer> getDictIndexMap(String... dictCode){
//        Map<String,Integer> rsMap = Maps.newHashMap();
//        Map<String, List<SerDict>> map = queryDictByCode(dictCode);
//        for(Map.Entry<String, List<SerDict>> entry:map.entrySet()){
//            String key = entry.getKey();
//            List<SerDict> list = entry.getValue();
//            for (SerDict serDict:list) {
//                rsMap.put(key+"_"+serDict.getDictKey(), serDict.getDictIndex());
//            }
//        }
//        return rsMap;
//    }
//
//
//    public Integer getDictIndex(String dictCode, Integer dictKey){
//        if(null == dictCode || "".equals(dictCode)){
//            return -1;
//        }
//
//        if(null == dictKey || "".equals(dictKey)){
//            return -1;
//        }
//
//        Response<Map<String, List<SerDict>>> response = serDictController.queryRedisDicts(dictCode);
//        Map<String, List<SerDict>> map = response.getData();
//        for(Map.Entry<String, List<SerDict>> entry:map.entrySet()){
//            String key = entry.getKey();
//            List<SerDict> list = entry.getValue();
//            for (SerDict serDict:list) {
//                if(dictKey.equals(serDict.getDictKey())){
//                    return serDict.getDictIndex();
//                }
//            }
//        }
//        return -1;
//    }
//
//    public Map<String, List<SerDict>> queryDictByCode(String... dictCode){
//        String item = "";
//        List<SerDict> data = null;
//        Map<String, Object> params = null;
//        Map<String, List<SerDict>> result = Maps.newHashMap();
//        int i = 0;
//        for(int len = dictCode.length; i < len; ++i) {
//            params = Maps.newHashMap();
//            item = dictCode[i];
//            params.put("dictCode", item);
//            data = serDictMapper.selectDataListByDictCode(params);
//            result.put(item, data);
//        }
//
//        return result;
//    }

    public Map<String, Integer> getDictVal(String s) {
        Map<String, Integer> rsMap = new HashMap<>();
        rsMap.put("1", 1);
        rsMap.put("2", 2);
        rsMap.put("3", 3);
        rsMap.put("4", 4);
        return rsMap;
    }
}
