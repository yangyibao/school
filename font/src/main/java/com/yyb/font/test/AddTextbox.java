package com.yyb.font.test;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextRange;
import com.spire.pdf.graphics.PdfDashStyle;

import java.awt.*;

public class AddTextbox {

    private void oldTest(){
        //创建文档
        Document doc = new Document();

        //添加指定大小的文本框
        TextBox tb = doc.addSection().addParagraph().appendTextBox(380, 280);
        //设置文字环绕方式
        tb.getFormat().setTextWrappingStyle(TextWrappingStyle.Square);
        //设置文本框的相对位置
        tb.getFormat().setHorizontalOrigin(HorizontalOrigin.Left_Margin_Area);
        tb.getFormat().setHorizontalPosition(120f);
        tb.getFormat().setVerticalOrigin(VerticalOrigin.Page);
        tb.getFormat().setVerticalPosition(100f);
        //设置文本框边框样式
        tb.getFormat().setLineStyle(TextBoxLineStyle.Thin_Thick);
        tb.getFormat().setLineColor(Color.gray);

        //插入图片到文本框
        Paragraph para = tb.getBody().addParagraph();
        DocPicture picture = para.appendPicture("C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\1.png");
        picture.setHeight(120f);
        picture.setWidth(180f);
        para.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para.getFormat().setAfterSpacing(13f);

        //插入文字到文本框
        para = tb.getBody().addParagraph();
        TextRange textRange = para.appendText("中美贸易争端，又称中美贸易战，也叫中美贸易摩擦，是中美经济关系中的重要问题。 "
                + "贸易争端主要发生在两个方面：一是中国具有比较优势的出口领域；"
                + "二是中国没有优势的进口和技术知识领域。");
        textRange.getCharacterFormat().setFontName("楷体");
        textRange.getCharacterFormat().setFontSize(11f);
        para.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //添加表格到文本框
        //声明数组内容
        String[][] data = new String[][]{
                new String[]{"国家", "年份", "出口额（美元）", "进口额（美元）"},
                new String[]{"中国", "2017", "125468", "101109"},
                new String[]{"美国", "2017", "86452", "124298"},
        };
        //添加表格
        Table table = tb.getBody().addTable();
        //指定表格行数、列数
        table.resetCells(3,4);
        //将数组内容填充到表格
        for (int i = 0; i < data.length; i++) {
            TableRow dataRow = table.getRows().get(i);
            dataRow.getCells().get(i).setWidth(70);
            dataRow.setHeight(22);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            for (int j = 0; j < data[i].length; j++) {
                dataRow.getCells().get(j).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                TextRange range2 = dataRow.getCells().get(j).addParagraph().appendText(data[i][j]);
                range2.getCharacterFormat().setFontName("楷体");
                range2.getCharacterFormat().setFontSize(11f);
                range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            }
        }
        //应用表格样式
        table.applyStyle(DefaultTableStyle.Colorful_Grid_Accent_3);

        //保存文档
        doc.saveToFile("C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\AddTextbox.docx", FileFormat.Docx_2013);
    }


    public static void main(String[] args) {
        Document doc = new Document("C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\t1.doc");
        Section section = doc.getSections().get(0);

        Paragraph para = section.getParagraphs().get(1);

        TextBox tb = para.appendTextBox(40, 40);

        //设置文字环绕方式
        tb.getFormat().setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
        //设置文本框的相对位置
        tb.getFormat().setHorizontalOrigin(HorizontalOrigin.Left_Margin_Area);
        //水平位置
        tb.getFormat().setHorizontalPosition(68f);
        //对其方式
        tb.getFormat().setVerticalOrigin(VerticalOrigin.Page);
        //竖直位置
        tb.getFormat().setVerticalPosition(64f);
        //设置文本框边框样式
        //tb.getFormat().setLineStyle(TextBoxLineStyle.Simple);
        //设置文本框颜色
        //tb.getFormat().setLineColor(Color.white);
        //设置无边框
        tb.getFormat().setNoLine(true);
        //tb.getFormat().setTextWrappingStyle(TextWrappingStyle.None);
        //设置填充颜色透明
        tb.setFillColor(new Color(255,255,255,0));
        tb.getFormat().setFillColor(new Color(255,255,255,0));



        //插入文字到文本框
        para = tb.getBody().addParagraph();
        TextRange textRange = para.appendText("好");
        textRange.getCharacterFormat().setFontName("田英章楷书");
        textRange.getCharacterFormat().setFontSize(25f);
        para.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //保存文档
        doc.saveToFile("C:\\Users\\YangYiBao\\Desktop\\文章笔记\\字帖程序\\template\\t2.docx", FileFormat.Docx_2013);


    }
}
