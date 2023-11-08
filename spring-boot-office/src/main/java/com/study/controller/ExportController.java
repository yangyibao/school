package com.study.controller;

import com.study.model.BcSerXyzpGwglVO;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导入excel示例
 */
@RestController
public class ExportController {

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
     * poi 方式导出excel
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

}
