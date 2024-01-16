package com.study.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.MapUtils;
import com.study.model.BcSerXyzpGwglVO;
import com.study.model.DataVO1;
import com.study.model.PoiYzVO;
import com.study.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 将数据列表 导出到excel数据列表 poi方式
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
     * http://localhost:8080/easyExcelExport
     *  将一个数组导出到excel数据列表 easyExcel 方式
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


    /**
     * http://localhost:8080/easyExcelFillDataExport
     *  导出excel数据文件 填充表格且带印章  easyExcel 方式
     *  1.excel 填充
     *  2.excle 增加印章
     *  3.excel 转为 pdf 3页以内
     *  4.pdf 添加水印
     *  5.返回带水印的pdf路径
     */
    @GetMapping(value = "/easyExcelFillDataExport")
    public Map<String, Object> easyExcelFillDataExport(){
        Map<String, Object> rsMap = new HashMap<>();

        // 1.填充excel 开始
        String templateFileName = TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "simple.xlsx";

        String name = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis();
        // 方案2 根据Map填充
        String fileName =  name + ".xlsx";
        String pdfFileName =  name + ".pdf";
        String warterFileName = name+"-wt.pdf";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("name", "张三");
        map.put("number", 5.2);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
        // 填充excel 结束

        // 2.增加印章 开始
        String yzImg = TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "10101.png";
        PoiYzVO poiYzVO = new PoiYzVO();
        poiYzVO.setCol1(2);
        poiYzVO.setRow1(9);
        poiYzVO.setCol2(3);
        poiYzVO.setRow2(15);
        poiYzVO.setImgPath(yzImg);
        PoiYzUtil poiYzUtil = new PoiYzUtil();
        Map<String, Object> yzMap = poiYzUtil.drawImg(fileName, poiYzVO);
        // 增加印章 结束

        // 3.excel存为pdf 开始
        PdfSpireUtil pdfUitl = new PdfSpireUtil();
        pdfUitl.saveFileToPdf(fileName, pdfFileName);
        // 3.excel存为pdf 结束

        if((boolean)yzMap.get("flag")){
            // 4.pdf增加水印 开始
            PdfWaterUtil pdfWaterUtil = new PdfWaterUtil();
            Map<String, Object> pdfMap = pdfWaterUtil.drawWater(pdfFileName, warterFileName);
            // 4.pdf增加水印 结束
            if((boolean)pdfMap.get("flag")){
                File file1 = new File(fileName);
                File file2 = new File(pdfFileName);
                file1.delete();
                file2.delete();
                rsMap.put("flag", true);
                rsMap.put("path", warterFileName);
                return rsMap;
            }
        }

        rsMap.put("flag", false);
        return rsMap;
    }

    /**
     * 将doc 转换为 pdf
     * http://localhost:8080/parseDocToPdf
     * @param path
     * @return
     */
    @GetMapping(value = "/parseDocToPdf")
    public Map<String, Object> parseDocToPdf(String path) throws Exception {
        Map<String, Object> rsMap = new HashMap<>();
        String splitTarget = TestFileUtil.getPath() + "demo" + File.separator;
        path = TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "wx.docx";

        String downloadPath =  path;
        File file = new File( downloadPath);
        if(!file.exists()){
            rsMap.put("flag", true);
            rsMap.put("message", "请检查文件路径!");
            return rsMap;
        }
        System.out.println(path);

        String fileName = path.substring(path.lastIndexOf(File.separator), path.lastIndexOf("."));
        String pdfFileName = fileName + ".pdf";

        String tmpRealPath = file.getParentFile().getAbsolutePath()+ pdfFileName;


        Map<String, Object > tmpMap = WordToPdfUtils.word2pdf(downloadPath, tmpRealPath);
        if((boolean)tmpMap.get("flag")){
            String ftpPath = tmpRealPath.replace(splitTarget, "");
            rsMap.put("flag", true);
            rsMap.put("path", tmpRealPath);
            rsMap.put("ftpPath", ftpPath);
            return rsMap;
        }
        return tmpMap;
    }

    /**
     * 填充 doc模板，并且盖章等功能
     * http://localhost:8080/fillDocTest
     * @return
     */
    @GetMapping(value = "/fillDocTest")
    public Map<String, Object> fillDocTest(){
        WordFillUitl wordFillUitl = new WordFillUitl();
        wordFillUitl.doTest();
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("flag", true);
        rsMap.put("message", "");
        return rsMap;
    }

    /**
     * 关于 把数据写入excel模板中. 更多填充详见 EasyExcelFillTest 的示例
     */
}
