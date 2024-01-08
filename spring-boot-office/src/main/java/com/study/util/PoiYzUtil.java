package com.study.util;

import com.study.model.PoiYzVO;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 给一个已经完成的excel进行 盖章
 */
public class PoiYzUtil {

    public ByteArrayOutputStream getYzStream(String yz1) {
        if (null == yz1 || "".equals(yz1)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        BufferedImage bufferImg = null;
        try {
            bufferImg = ImageIO.read(new File(yz1));
            ImageIO.write(bufferImg, "png", byteArrayOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOut;
    }

    public Map<String, Object> drawImg(String fileName, PoiYzVO poiYzVO) {
        Map<String, Object> rsMap = new HashMap<>();

        //添加图片水印
        FileOutputStream fileOut = null;
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        try {
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            //加载图片
            workbook = WorkbookFactory.create(new FileInputStream(fileName));
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            Sheet sheet1 = workbook.getSheetAt(0);
            Drawing drawing = sheet1.createDrawingPatriarch();
            ByteArrayOutputStream byteArrayOut = getYzStream(poiYzVO.getImgPath());
            boolean isDraw = false;
            if (null != byteArrayOut) {
                //调整图片定位
                XSSFClientAnchor anchor = new XSSFClientAnchor(poiYzVO.getDx1(), poiYzVO.getDy1(), poiYzVO.getDx2(), poiYzVO.getDy2(), poiYzVO.getCol1(), poiYzVO.getRow1(), poiYzVO.getCol2(), poiYzVO.getRow2());
                drawing.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
                isDraw = true;
            }
            if (isDraw) {
                fileOut = new FileOutputStream(fileName);
                // 写入excel文件
                workbook.write(fileOut);
                byteArrayOut = null;
                rsMap.put("flag", true);
                return  rsMap;
            }
            rsMap.put("flag", false);
            rsMap.put("message", "未知错误!");
            return rsMap;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if(null != workbook){
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rsMap.put("flag", false);
        rsMap.put("message", "文件增加印章出错!");
        return rsMap;
    }
}
