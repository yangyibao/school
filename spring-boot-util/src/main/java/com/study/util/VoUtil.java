package com.study.util;

import com.study.model.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VoUtil {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class.getName());

    /**
     * 反射获取对象集合的某个字段的值 list
     * @param list 对象list
     * @param fieldName 字段名称
     * @return
     */
    private static List<Object> getFieldValList(List<?> list, String fieldName){
        List<Object> rsList = new ArrayList<>();
        try {
            for (Object object : list) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                rsList.add(field.get(object));
            }
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return rsList;
    }

    /**
     * 反射取字段值
     * @param obj
     * @param fieldName
     * @return
     */
    private static Object getFieldVal(Object obj, String fieldName){
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return  field.get(obj);
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void getFieldValTest(){

        TestVO t1 = new TestVO();
        t1.setAge(10);
        t1.setName("胡歌");
        t1.setId(1);

        Field[] fields = t1.getClass().getDeclaredFields();
        HashMap<String, String> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                // 把对象的属性做k，值做v
                // 当然中间还可做其他的业务操作，比如跳过某些属性什么的
                String name = field.getName();
                String s = field.get(t1).toString();
                map.put(name,s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug("data:{}", map);

    }



    public static void main(String[] args) {
        TestVO t1 = new TestVO();
        t1.setAge(10);
        t1.setName("胡歌");
        t1.setId(1);

        TestVO t2 = new TestVO();
        t2.setAge(10);
        t2.setName("王二");
        t2.setId(1);

        List<TestVO> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);

        List<Object> list1 = getFieldValList(list, "name");

        log.debug("data: {}", list1);

        log.debug("val: {}", getFieldVal(t2,"name"));

        //getFieldValTest();

    }

}
