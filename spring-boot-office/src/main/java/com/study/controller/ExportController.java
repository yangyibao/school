package com.study.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.study.model.BcSerXyzpGwglVO;
import com.study.model.DataVO1;
import com.study.service.BcSerXyzpGwglVOService;
import com.study.util.ExcelExportUtil;
import com.study.util.ExcelImportUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 导入excel示例
 */
@RestController
public class ExportController {

    /**
     * 测试数据集合
     * @return
     */
    public List<BcSerXyzpGwglVO> initData(){
        List<BcSerXyzpGwglVO> list = new ArrayList<>();
        BcSerXyzpGwglVO bcSerXyzpGwglVO = new BcSerXyzpGwglVO();
        bcSerXyzpGwglVO.setGwglBmid(1);
        bcSerXyzpGwglVO.setGwglBmmc("名称1");
        bcSerXyzpGwglVO.setGwglGwmc("教师岗位");
        bcSerXyzpGwglVO.setGwglGgxh("21");
        bcSerXyzpGwglVO.setGwglGwjb(25);
        bcSerXyzpGwglVO.setGwglZt(1);
        bcSerXyzpGwglVO.setGwglJfxs(1);

        list.add(bcSerXyzpGwglVO);
        BcSerXyzpGwglVO bcSerXyzpGwglVO1 = new BcSerXyzpGwglVO();
        bcSerXyzpGwglVO1.setGwglBmid(2);
        bcSerXyzpGwglVO1.setGwglBmmc("名称2");
        bcSerXyzpGwglVO1.setGwglGwmc("管理岗位");
        bcSerXyzpGwglVO1.setGwglGgxh("23");
        bcSerXyzpGwglVO1.setGwglGwjb(26);
        bcSerXyzpGwglVO1.setGwglZt(3);
        bcSerXyzpGwglVO1.setGwglJfxs(2);
        list.add(bcSerXyzpGwglVO1);
        return list;
    }

    /**
     * 导出excel poi方式
     * http://localhost:8080/ExportExcel
     * @param response
     */
    @GetMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response){
        List<BcSerXyzpGwglVO> data = initData();
        List<BcSerXyzpGwglVO> list = new ArrayList<>();
        list.addAll(data);
        String fileName = "person_journal" + ".xls";
        String[] strMeaning = new String[] {"招聘单位名称","招聘岗位经费形式","招聘部门名称","公告序号","招聘岗位名称","招聘岗位级别","招聘岗位序号"};
        String[] strName = new String[] {"gwglBmid","gwglJfxs","gwglBmmc","gwglGgxh","gwglGwmc","gwglGwjb","gwglGgxh"};

        // 设置文件名编码集
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            ExcelExportUtil.exportExcel1(strMeaning, strName, list, response,fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 示例数据 easy-Excel
     * @return
     */
    private List<DataVO1> data() {
        List<DataVO1> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DataVO1 data = new DataVO1();
            data.setA1("字符串" + i);
            data.setA2(new Date()+"");
            data.setA3("o"+i);
            data.setA4("A"+i);
            data.setA5("c"+i);
            list.add(data);
        }
        return list;
    }

    /**
     *  导出excel easyExcel 方式
     * @param response
     */
    @GetMapping(value = "/easyExcelExport")
    public  void easyExcelExport(HttpServletResponse response){
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            //easyExcel 写文档的核心
            EasyExcel.write(response.getOutputStream(), DataVO1.class).sheet("模板").doWrite(data());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // String path="C:\\doc\\easyExcel\\";
        // String fileName = path + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // EasyExcel.write(fileName, DataVO1.class).sheet("模板").doWrite(data());

        // Map<String, Object> rsMap = new HashMap<>();
        // rsMap.put("data", fileName);
        // return rsMap;

    }

}
