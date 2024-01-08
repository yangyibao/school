package com.study.util;

import com.aspose.words.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordToPdfUtils {

	public static boolean isInit = false;

	/**
	 * word 转换pdf 不带水印 无页数限制
	 * 这个工具类是支持 doc文档 3个页面以上的
	 * @param docPath
	 * @param pdfPath
	 * @return
	 */
	public static Map<String, Object> word2pdf(String docPath, String pdfPath) throws Exception {
		//如果没有初始化 进行一个key注册 否则会需要licence 收费
		if(!isInit){
			registerWord();
			isInit = true;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			com.aspose.words.Document document = new com.aspose.words.Document(docPath);
			FileOutputStream os = new FileOutputStream(new File(pdfPath));
			document.save(os, SaveFormat.PDF);
			os.close();
			map.put("flag", true);
		} catch (Exception e) {
			map.put("flag", false);
			map.put("message", "word转换pdf失败!");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * aspose-words:jdk17:23.4 版本
	 * 注意这个方法 一定要进行一个注册，本身这个是收费的，用这个函数构造 一下key的信息，相当于破解了一下
	 */
	public static void registerWord() throws Exception {

		// 构造一个注册信息
		Class<?> zzXgCClass = Class.forName("com.aspose.words.zzXgC");
		Constructor<?> constructors = zzXgCClass.getDeclaredConstructors()[0];
		constructors.setAccessible(true);
		Object instance = constructors.newInstance("zzW5k", "zzYON");

		// zzXFN = 1
		java.lang.reflect.Field zzXFN = zzXgCClass.getDeclaredField("zzXFN");
		zzXFN.setAccessible(true);
		zzXFN.set(instance, 1);

		// 把注册信息放到 zzYVA这个类中来
		Class<?> zzYVAClass = Class.forName("com.aspose.words.zzYVA");
		java.lang.reflect.Field zzwP = zzYVAClass.getDeclaredField("zzwP");
		zzwP.setAccessible(true);
		ArrayList<Object> zzwPValue = new ArrayList<>();
		zzwPValue.add(instance);
		zzwP.set(null, zzwPValue);

		// 生成文档会掉这个来判断 zzXQo
		Class<?> zzXQoClass = Class.forName("com.aspose.words.zzXQo");
		java.lang.reflect.Field zzHA = zzXQoClass.getDeclaredField("zzHA");
		zzHA.setAccessible(true);
		zzHA.set(null, 128);
		Field zzWod = zzXQoClass.getDeclaredField("zzWod");
		zzWod.setAccessible(true);
		zzWod.set(null, false);
	}



}