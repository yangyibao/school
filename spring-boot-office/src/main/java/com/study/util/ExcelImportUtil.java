package com.study.util;

import com.study.controller.SerDictUtilContrller;
import com.study.model.BcSerXyzpGwglVO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * excel导入工具类 poi方式
 */
public class ExcelImportUtil {

    private static Logger log = LoggerFactory.getLogger(ExcelImportUtil.class.getName());

    protected Workbook getWorkbook(File file) {
        Workbook workbook = null;
        try {
            InputStream inp = new FileInputStream(file);
            workbook = WorkbookFactory.create(inp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    protected int getCols(Sheet sheet) {
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = 0;
        if (rows >= 1 && sheet.getRow(0) != null) {
            cols = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        return cols;
    }

    private int isNeedDy(String val){
        if ("是".equals(val)){
            return 1;
        }
        return 2;
    }

    private int getIntVal(String val){
        if(null != val && !"".equals(val)){
            return Integer.parseInt(val);
        }
        return 0;
    }

    private String getDayVal(Date val){
        if(null != val ){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return  dateFormat.format(val);
        }
        return "";
    }

    private String getZyStr(String str){
        if(null == str || "".equals(str)){
            return str;
        }
        String rsStr = str;
        if(str.indexOf("、")>0){
            rsStr = str.replaceAll("、",",");
        }
        return rsStr;
    }



    private List<BcSerXyzpGwglVO> getBcSerXyzpGwglVOList(Workbook workbook, Integer userId) {
        SerDictUtilContrller serDictUtilContrller = SpringUtils.getBean(SerDictUtilContrller.class);
        Map<String,Integer> gwglJfxsMap = serDictUtilContrller.getDictVal("20231008030");
        //读取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = getCols(sheet);
        //从第三行开始 可以改成1
        if (cols < 3) {
            return new ArrayList<>();
        }
        BcSerXyzpGwglVO bcSerXyzpGwglVO = null;

        List<BcSerXyzpGwglVO> list = new ArrayList<>();
        for (int r = 3; r < rows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            bcSerXyzpGwglVO = new BcSerXyzpGwglVO();
            bcSerXyzpGwglVO.setGwglZgbm("吉林省教育厅");
            bcSerXyzpGwglVO.setRowIndex(r);
            bcSerXyzpGwglVO.setGwglZt(1);
            for (int c = 0; c < cols; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(cell);

                    //序号 无用
                    if (c == 0) {
                        //bcSerXyzpGwglVO.setGwglGgxh(val);
                    //招聘单位
                    }else if(c == 1){
                        bcSerXyzpGwglVO.setGwglBmid(Integer.parseInt(val));
                    //经费形式
                    }else if(c == 2){
                        if(gwglJfxsMap.containsKey(val)){
                            bcSerXyzpGwglVO.setGwglJfxs(gwglJfxsMap.get(val));
                        }
                    //招聘部门名称
                    }else if(c == 3){
                        bcSerXyzpGwglVO.setGwglBmmc(val);
                    //公告序号
                    }else if(c == 4){
                        bcSerXyzpGwglVO.setGwglGgxh(val);
                        bcSerXyzpGwglVO.setGwglZpggid(Integer.parseInt(val));
                    //招聘岗位名称
                    }else if(c == 5){
                        bcSerXyzpGwglVO.setGwglGwmc(val);
                    }
                }
            }
            list.add(bcSerXyzpGwglVO);
        }

        return list;
    }

    public List<BcSerXyzpGwglVO> getBcSerXyzpGwglVOByFile(File file, Integer userId){
        Workbook workbook = getWorkbook(file);
        if (workbook != null) {
            return getBcSerXyzpGwglVOList(workbook, userId);
        }
        return null;
    }


    public static String getCurrentDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    public static String getUUID() {
        StringBuilder uuid = new StringBuilder(getCurrentDate("yyMMddHHmmssSSS"));
        uuid.append(Thread.currentThread().getId());
        uuid.append(RandomUtils.nextInt());
        return uuid.toString();
    }

    public static File saveTempFile(MultipartFile file) {
        if (file != null) {
            String strTmp = System.getProperty("java.io.tmpdir");
            File fileDir = new File(strTmp+ File.separator+"excel");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            String uuid = getUUID();
            String fileName = file.getOriginalFilename();
            String name = uuid + fileName;
            String saveName = fileDir.getAbsolutePath() + File.separator + name;
            File tmpFile = new File(saveName);

            try {
                file.transferTo(tmpFile);
                return tmpFile;
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        return null;
    }







}
