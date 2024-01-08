package com.study.util;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;


public class PdfSpireUtil {


    /**
     * 保存为pdf
     * @param fileName
     * @param pdfFileName
     */
    public void saveFileToPdf(String fileName , String pdfFileName){
        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        //用spire.xls 把 excel 转换成 不带水印的pdf
        //注意 这个spire 免费的 doc只能有3页内
        workbook.loadFromFile(fileName);
        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);
        //将生成的文档保存到指定路径
        workbook.saveToFile(pdfFileName, FileFormat.PDF);
    }
}
