package com.study.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.study.model.SysWaterMarkVO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class PdfWaterUtil {

    /**
     * 中文字体支持
     */
    public static BaseFont BASE_FONT_CHINESE;
    static {
        try {
            BASE_FONT_CHINESE = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document document;

    protected OutputStream os;

    public Document getDocument() {
        if (document == null) {
            document = new Document();
        }
        return document;
    }

    /**
     * 给pdf上水印
     * @param inputFile 源文件路径
     * @param imageFile  水印图片
     * @param output    输出流
     * @param waterText  水印图片
     * @param sysWaterMarkVO 水印样式
     * @return
     */
    public  static int  waterMark(String inputFile, String imageFile, OutputStream output, String waterText, SysWaterMarkVO sysWaterMarkVO) {
        try {
            //读取文件
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, output);
            //获取页数
            int total = reader.getNumberOfPages() + 1;
            double imageDis = 0;
            double imageHeight = 0;
            double imageWidth = 0;
            Image image = null;
            //判断水印图片
            if(imageFile!=null && !"".equals(imageFile)){
                //图片信息
                image = Image.getInstance(imageFile);
                imageWidth = image.getWidth();
                imageHeight = image.getHeight();
                imageDis = Math.sqrt((imageWidth*imageWidth)/2)+
                        (sysWaterMarkVO.getwSpace());
                image.setRotationDegrees(sysWaterMarkVO.getRot1tion());//旋转 角度
//				image.setTransparency(new int[]{0,10});//设置透明度
            }

            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                //获取页面宽高
                Rectangle pageSize = reader.getPageSize(i);
                double width = pageSize.getWidth();
                double height = pageSize.getHeight();
                //在内容底层
//				under = stamper.getUnderContent(i);
                //在内容顶层
                under = stamper.getOverContent(i);
                //处理水印图片
                if(image != null){
                    double wDis = 0;
                    double hDis = height-imageDis;
                    for(int n = 0;n<(height*2)/imageDis;n++){
                        double wwDis = wDis;
                        double hhDis = hDis;
                        for(int j=0;j<(width*2)/imageDis;j++){
                            // 图片位置默认左下角
                            image.setAbsolutePosition((int)wwDis, (int)hhDis);
                            under.addImage(image);
                            wwDis = (wwDis + imageDis);
                            hhDis = (hhDis + imageDis);
                        }
                        hDis = hDis-( Math.sqrt((imageHeight*imageHeight)/2)*2)-
                                (sysWaterMarkVO.gethSpace()!=0? sysWaterMarkVO.gethSpace():10.0);
                    }
                }
                //判断水印文字
                if(waterText!=null && !"".equals(waterText)){
                    //文字处理开始
                    under.beginText();
                    //设置字体和文字大小
                    under.setFontAndSize(sysWaterMarkVO.getFont(), sysWaterMarkVO.getWordSize());
                    //设置文字颜色
                    under.setColorFill(new BaseColor(sysWaterMarkVO.getWordColor()));
                    PdfGState gs = new PdfGState();// 设置透明度
                    gs.setFillOpacity(sysWaterMarkVO.getOpacity());
                    under.setGState(gs);
                    under.setWordSpacing(sysWaterMarkVO.getWordSpace());//文字间距
                    //水印间距
                    double space = sysWaterMarkVO.getwSpace()!=0? sysWaterMarkVO.getwSpace():300.0;
                    //水印行距
                    double hSpace =  sysWaterMarkVO.gethSpace()!=0? sysWaterMarkVO.gethSpace():100.0;
                    double wDis = 0;
                    double hDis = height-space;
                    for(int n = 0;n<(height*2)/hSpace;n++){
                        double wwDis = wDis;
                        double hhDis = hDis;
                        for(int j=0;j<width/hSpace;j++){
                            // 添加水印文字
                            under.showTextAligned(PdfContentByte.ALIGN_RIGHT, waterText,(int)wwDis,(int)hhDis,
                                    sysWaterMarkVO.getRot1tion());
                            wwDis = (wwDis + space);
                            hhDis = (hhDis + space);
                        }
                        hDis = hDis-(hSpace);
                    }
                    //水印文字处理结束
                    under.endText();
                }

            }
            stamper.close();
            reader.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * 给一个pdf文件增加水印
     * @param baseFilePath 源文件
     * @param targetFilePath 生成水印的文件
     * @return
     */
    public Map<String, Object> drawWater(String baseFilePath, String targetFilePath){
        Map<String, Object> rsMap = new HashMap<>();

        // 设置水印
        SysWaterMarkVO sysWaterMarkVO = new SysWaterMarkVO();
        sysWaterMarkVO.setLogoPath("");
        sysWaterMarkVO.setWord("吉林 测试单位 管理");
        sysWaterMarkVO.setRot1tion(45);// 旋转角度，逆时针
        sysWaterMarkVO.setFont(this.BASE_FONT_CHINESE);// 字体
        sysWaterMarkVO.setOpacity(0.3f);// 透明度
        sysWaterMarkVO.setWordSpace(10);// 字间距
        sysWaterMarkVO.setWordSize(30);// 字体大小
        sysWaterMarkVO.setWordColor(0x008E8E8E);// 字体颜色
        sysWaterMarkVO.setwSpace((float) 282.0);// 词距，图片距离(词距需加上文字的总长度，图片不加)
        sysWaterMarkVO.sethSpace((float) 200.0);// 行距，图片行距离

        String filePath = baseFilePath;
        String outFile =  targetFilePath;
        OutputStream osEnd = null;

        try {
            PdfWaterUtil pdfWaterUtil = new PdfWaterUtil();
            osEnd = new FileOutputStream(outFile);
            int i= waterMark(filePath, sysWaterMarkVO.getLogoPath(), osEnd, sysWaterMarkVO.getWord(), sysWaterMarkVO);
            if(i == 1){
                rsMap.put("flag", true);
                rsMap.put("path", targetFilePath);
                return rsMap;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            rsMap.put("flag", false);
            rsMap.put("message", "系统增加水印出错!");
            return rsMap;
        }
        rsMap.put("flag", false);
        rsMap.put("message", "系统异常!");

        return rsMap;
    }

    public static void main(String[] args) {
        // 设置水印
        SysWaterMarkVO sysWaterMarkVO = new SysWaterMarkVO();
        sysWaterMarkVO.setLogoPath("");
        sysWaterMarkVO.setWord("长春 事业单位 管理");
        sysWaterMarkVO.setRot1tion(45);// 旋转角度，逆时针
        sysWaterMarkVO.setFont(PdfWaterUtil.BASE_FONT_CHINESE);// 字体
        sysWaterMarkVO.setOpacity(0.3f);// 透明度
        sysWaterMarkVO.setWordSpace(10);// 字间距
        sysWaterMarkVO.setWordSize(30);// 字体大小
        sysWaterMarkVO.setWordColor(0x008E8E8E);// 字体颜色
        sysWaterMarkVO.setwSpace((float) 282.0);// 词距，图片距离(词距需加上文字的总长度，图片不加)
        sysWaterMarkVO.sethSpace((float) 200.0);// 行距，图片行距离


        String filePath = "C:\\doc\\bcssv2\\target\\zj-pyba-231220192506839232.pdf";
        String outFile =  "C:\\doc\\bcssv2\\target\\zj-pyba-231220192506839232-2.pdf";
        OutputStream osEnd = null;
        try {
            osEnd = new FileOutputStream(outFile);
            waterMark(filePath, sysWaterMarkVO.getLogoPath(), osEnd, sysWaterMarkVO.getWord(), sysWaterMarkVO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
