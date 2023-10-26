package com.yyb.font.util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextRange;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GenterText {

    private static String fontName = "田英章楷书";
    private static String curText = "故人具鸡黍邀我至田家绿树村边合青山郭外斜开轩面场圃把酒话桑麻待到重阳日还来就菊花";
    private static String filePath = "C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\t3.doc";
    private static String targetPath = "C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\t4.doc";

    private static TextBox getTextBox(Paragraph para,float horizontalPosition, float verticalPosition){
        TextBox tb = para.appendTextBox(37, 47);
        //设置文字环绕方式
        tb.getFormat().setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
        //设置文本框的相对位置
        tb.getFormat().setHorizontalOrigin(HorizontalOrigin.Left_Margin_Area);
        //水平位置
        tb.getFormat().setHorizontalPosition(horizontalPosition);
        //对其方式
        tb.getFormat().setVerticalOrigin(VerticalOrigin.Paragraph);
        //竖直位置
        tb.getFormat().setVerticalPosition(verticalPosition);
        //设置无边框
        tb.getFormat().setNoLine(true);
        //设置填充颜色透明
        tb.setFillColor(new Color(255,255,255,0));
        tb.getFormat().setFillColor(new Color(255,255,255,0));
        //调试颜色
//        tb.setFillColor(Color.gray);
        return tb;
    }

    private static TextRange getTextRange(Paragraph para, String str){
        TextRange textRange = para.appendText(str);
        textRange.getCharacterFormat().setFontName(fontName);
        textRange.getCharacterFormat().setFontSize(28f);
        return textRange;
    }

    private static List<String> getStrList(){
        List<String> list = new ArrayList<>();
        if(null !=curText && !"".equals(curText)){
            String str = curText;
            for(int i = 0; i < str.length() ; i++){
                String ss = String.valueOf(str.charAt(i));
                list.add(ss);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Document doc = new Document(filePath);
        List<String> strList = getStrList();
        int size = strList.size();
        Section section = doc.getSections().get(0);
        ParagraphCollection paragraphs  = section.getParagraphs();
        int docSize = paragraphs.getCount();
        int max = docSize;
        if(size > max){
            size = max;
        }
        for(int i=0; i< size;i ++){
            String str = strList.get(i);
            Paragraph para = paragraphs.get(i);
            //左右
            float hp = 67f;
            //上下
            float vp = -44f;
            TextBox tb = getTextBox(para, hp, vp);
            Paragraph tbPara = tb.getBody().addParagraph();
            getTextRange(tbPara,str);
            tbPara.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        //保存文档
        doc.saveToFile(targetPath, FileFormat.Docx_2013);

    }


}
