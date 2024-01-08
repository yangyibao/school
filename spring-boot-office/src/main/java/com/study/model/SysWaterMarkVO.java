package com.study.model;


import com.itextpdf.text.pdf.BaseFont;
import com.study.util.PdfWaterUtil;

public class SysWaterMarkVO {
	private static final long serialVersionUID = 1L;
	//水印图片位置
	private String logoPath;
	//水印文字
	private String word;
	//旋转角度，逆时针
	private int rot1tion = 45;
	//文字透明度
	private float opacity = 0.3f;
	//字间距
	private int wordSpace = 10;
	//字体大小
	private int wordSize = 30;
	//字体颜色
	private int wordColor = 0x008E8E8E;
	//字体
	private BaseFont font = PdfWaterUtil.BASE_FONT_CHINESE;
	//词距，图片距离(词距需加上文字的总长度，图片不加)
	private float wSpace = 0;
	//行距，图片行距离
	private float hSpace = 0;

	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	public int getRot1tion() {
		return rot1tion;
	}
	public void setRot1tion(int rot1tion) {
		this.rot1tion = rot1tion;
	}
	public float getOpacity() {
		return opacity;
	}
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	public int getWordSpace() {
		return wordSpace;
	}
	public void setWordSpace(int wordSpace) {
		this.wordSpace = wordSpace;
	}
	public int getWordSize() {
		return wordSize;
	}
	public void setWordSize(int wordSize) {
		this.wordSize = wordSize;
	}
	public int getWordColor() {
		return wordColor;
	}
	public void setWordColor(int wordColor) {
		this.wordColor = wordColor;
	}
	public BaseFont getFont() {
		return font;
	}
	public void setFont(BaseFont font) {
		this.font = font;
	}
	public float getwSpace() {
		return wSpace;
	}
	public void setwSpace(float wSpace) {
		this.wSpace = wSpace;
	}
	public float gethSpace() {
		return hSpace;
	}
	public void sethSpace(float hSpace) {
		this.hSpace = hSpace;
	}

}
