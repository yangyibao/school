package com.study.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelExportUtil {

	private static Logger log = LoggerFactory.getLogger(ExcelExportUtil.class.getName());

	public static void exportExcel1(String[] strMeaning, String[] strName, Collection<?> collection, HttpServletResponse response, String fileName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HSSFWorkbook wb = ExcelExportUtil.generateExcelforObject(strMeaning,strName,"",collection);
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		FileOutputStream fos = null;
		FileInputStream in = null;
		ServletOutputStream out = null;
		File f = new File(desktopPath+File.separator+fileName);
		try {
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fos = new FileOutputStream(f);
			wb.write(fos);// 写文件
			// 设置响应头，控制浏览器下载该文件
			response.setContentType("application/msword");
			response.setHeader("content-disposition", "attachment;filename="+fileName);
			// 读取要下载的文件，保存到文件输入流
			in = new FileInputStream(desktopPath+File.separator+fileName);
			// 创建输出流
			out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			log.debug("文件下载成功.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			try {
				out.flush();
				in.close();
				out.close();
				fos.close();
				f.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static HSSFWorkbook generateExcelforObject(String[] strMeaning, String[] strName, String str, Collection<?> collection) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HSSFWorkbook wb = new HSSFWorkbook();
		if(strMeaning==null || strMeaning.length<1)return wb; //表头为空侧返回
		if(strName==null || strName.length<1)return wb;    //字段侧返回
		if(strMeaning.length!=strName.length)return wb;    //两个数组长度不同侧返回
		HSSFRow row=null;
		Integer rowNum = 0;
		//设置工作簿的名称
		String sheetTitle= StringUtils.isEmpty(str)?"Sheet1":str;
		HSSFSheet sheet = wb.createSheet();
		wb.setSheetName(0,sheetTitle);
		//设置标题
		row = sheet.createRow(rowNum);
		setTitle(row, strMeaning,wb);

		/* 自动调整宽度 */
		for (int i = 0; i < strMeaning.length; i++) {
			sheet.autoSizeColumn(i);
		}
		/* 实际宽度 */
		int curColWidth = 0;

		/* 默认宽度 */
		int[] defaultColWidth = new int[strMeaning.length];
		/* 实际宽度 < 默认宽度的时候、设置为默认宽度 */
		for (int i = 0; i <strMeaning.length; i++) {
			defaultColWidth[i]=3000;
			curColWidth = sheet.getColumnWidth(i);
			if (curColWidth < defaultColWidth[i]) {
				sheet.setColumnWidth(i, defaultColWidth[i]);
			}
		}

		for (Iterator<?> iter = collection.iterator(); iter.hasNext();) {
			Object exportEle = iter.next();
			// 行对象
			row = sheet.createRow(++rowNum);
			//设置对应值
			setRow(row, strName, exportEle,wb);
		}
		return wb;
	}

	/**
	 * 方法描述: 为Excel页中的每个横行设置标题
	 * @param row
	 * @param strMeaning
	 * @param wb
	 * @author  yindong  2017-6-25
	 */
	private static void setTitle(HSSFRow row, String[] strMeaning, HSSFWorkbook wb){
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		//生成一个字体
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)10);
		// 把字体应用到当前的样式
		style.setFont(font);
		for (int k = 0; k < strMeaning.length; k++) {
			HSSFCell cell = row.createCell((short)k);
			cell.setCellStyle(style);
			cell.setCellValue(strMeaning[k]);
		}
	}

	/**
	 *
	 * 方法描述: 为Excel页中的每个横行设置值
	 * @param row
	 * @param strName
	 * @param exportModel
	 * @author  yindong  2017-6-25
	 */
	@SuppressWarnings("deprecation")
	private static void setRow(HSSFRow row, String[] strName, Object exportModel, HSSFWorkbook wb){
		Object temp=null;

		/**
		 * @author yindong
		 *  数据量过大时，加上边框后会报4000样式错误，大约6000条数据之后就会报错
		 */
		for (int k = 0; k < strName.length; k++) {
			// Cell对象
			HSSFCell cell = row.createCell((short) k);

			//设置对应值
			try {
				//检查该实体是否有这个属性
				temp = PropertyUtils.getProperty(exportModel, strName[k]);
			} catch (Exception e) {
				e.getStackTrace();
				continue;
			}

			//设置对应值
			if(null == temp){
				cell.setCellValue(StringUtils.EMPTY);
			}else{
				cell.setCellValue(temp.toString());
			}
		}
	}

}
