import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Test {

    private static Logger log = LoggerFactory.getLogger(Test.class.getName());

    public static Map<String, Object> getRsMap(boolean flg, String errorMsg){
        Map<String,Object> rsMap = new HashMap<>();
        rsMap.put("flg",flg);
        rsMap.put("errorMsg",errorMsg);
        return rsMap;
    }

    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            log.error("加载图片未找到", e);
            e.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            //注：FileInputStream.available()方法可以从输入流中阻断由下一个方法调用这个输入流中读取的剩余字节数
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.error("IO操作图片错误", e);
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static Map<String, Object> getWrodFile(Map<String,Object> map,String templatePath,String templateName, String targetPath,String targetName){
        File templateNameFile = new File(templatePath+File.separator+templateName);
        if(!templateNameFile.exists()){
            log.error("文件不存在路径：{}",templateNameFile.getAbsolutePath());
            return getRsMap(false,"模板文件不存在!");
        }
        File targetPathFile = new File(targetPath);
        if(!targetPathFile.exists()){
            targetPathFile.mkdirs();
        }
        String pathName = targetPath+File.separator+targetName;
        //1.创建配置类
        Configuration configuration=new Configuration(Configuration.getVersion());
        //2.设置模板所在的目录
        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            //3.设置字符集
            configuration.setDefaultEncoding("utf-8");
            //4.加载模板
            Template template = configuration.getTemplate(templateName);
            //5.创建数据模型 map
            //6.创建Writer对象
            out =new FileWriter(new File(pathName));
            //7.输出
            template.process(map, out);
            Map<String, Object> rsMap = new HashMap<>();
            rsMap.put("flg", true);
            rsMap.put("file", pathName);
            return rsMap;
        } catch (IOException | TemplateException e ) {
            e.printStackTrace();
            log.error(e.getMessage());
            return getRsMap(false,"系统异常请查看日志!");
        }finally {
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getChekBox(Integer num){
        if(1 == num){
            return "<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
        }else{
            return "<w:sym w:font=\"Wingdings 2\" w:char=\"00A3\"/>";
        }
    }

    public static String getCheckStr(Integer num){
        //符合
        if(1 == num){
            return "<w:tcPr><w:tcW w:w=\"1174\" w:type=\"dxa\"/><w:vAlign w:val=\"center\"/></w:tcPr><w:p><w:pPr><w:adjustRightInd w:val=\"0\"/><w:snapToGrid w:val=\"0\"/><w:jc w:val=\"center\"/><w:rPr><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>符</w:t></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr><w:t xml:space=\"preserve\">  </w:t></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>合</w:t></w:r></w:p><w:p><w:pPr><w:adjustRightInd w:val=\"0\"/><w:snapToGrid w:val=\"0\"/><w:jc w:val=\"center\"/><w:rPr><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:sym w:font=\"Wingdings 2\" w:char=\"00A3\"/></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>不符合</w:t></w:r></w:p>";
        //不符合
        }else{
            return "<w:tcPr><w:tcW w:w=\"1174\" w:type=\"dxa\"/><w:vAlign w:val=\"center\"/></w:tcPr><w:p><w:pPr><w:adjustRightInd w:val=\"0\"/><w:snapToGrid w:val=\"0\"/><w:jc w:val=\"center\"/><w:rPr><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:sym w:font=\"Wingdings 2\" w:char=\"00A3\"/></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>符</w:t></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr><w:t xml:space=\"preserve\">  </w:t></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>合</w:t></w:r></w:p><w:p><w:pPr><w:adjustRightInd w:val=\"0\"/><w:snapToGrid w:val=\"0\"/><w:jc w:val=\"center\"/><w:rPr><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/></w:r><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr><w:t>不符合</w:t></w:r></w:p>";
        }

    }

    public static Map<String,Object> getParamMap(){
        Map<String, Object> map=new HashMap();
        String imgStr = getImageStr("C:\\Users\\YangYiBao\\Pictures\\ssd.png");
        map.put("p1", "长春市蓝梦职业培训学校");
        map.put("p2", "农安隆达金马城市广场2单元3041");
        map.put("p3", "胡适");
        map.put("p4", "胡适");
        map.put("p5", "15566442211");
//        map.put("p1", "1");
//        map.put("p2", "2");
//        map.put("p3", "3");
//        map.put("p4", "4");
//        map.put("p5", "5");
        map.put("p6", "2022");
        map.put("p7", "08");
        map.put("p8", "02");
        map.put("p9", "9");
        map.put("p10", "110");
        map.put("p11", "111");
        map.put("p12", "112");
        map.put("p13", "113");
        map.put("p14", "114");
        map.put("p15", "115");
        map.put("p16", "116");
        map.put("p17", "117");
        map.put("p18", "118");
        map.put("p19", "110");
        map.put("p20", "111");
        map.put("p21", "121");
        map.put("p22", "122");
        map.put("p23", "123");
        map.put("p24", "124");
        map.put("p25", "125");
        map.put("p26", "126");
        map.put("p27", "127");
        map.put("p28", "128");
        map.put("p29", "129");
        map.put("p30", imgStr);
        map.put("p31", "131");
        map.put("p32", "132");
        map.put("p33", "133");
        map.put("p34", "134");
        map.put("p35", "135");
        map.put("p36", "136");
        map.put("p37", "137");
        map.put("i1", getCheckStr(1));
        map.put("i2", getCheckStr(1));
        map.put("i3", getCheckStr(1));
        map.put("i4", getCheckStr(1));
        map.put("i5", getCheckStr(1));
        map.put("i6", getCheckStr(1));
        map.put("i7", getCheckStr(1));
        map.put("i8", getCheckStr(1));
        map.put("i9", getCheckStr(1));

        map.put("i10",getCheckStr(1));
        map.put("i11",getCheckStr(1));
        map.put("i12",getCheckStr(1));
        map.put("i13",getCheckStr(1));
        map.put("i14",getCheckStr(1));
        map.put("i15",getCheckStr(1));
        map.put("i16",getCheckStr(1));
        map.put("i17",getCheckStr(1));
        map.put("i18",getCheckStr(1));
        map.put("i19",getCheckStr(1));
        map.put("i20",getCheckStr(1));

        map.put("i21",getChekBox(1));
        map.put("i22",getChekBox(1));
        map.put("i23",getChekBox(1));
        map.put("i24",getChekBox(1));
        map.put("i25",getChekBox(1));
        return map;

    }

    public static void main(String[] args) throws Exception {
        String templatePath = "C:\\doc\\tomp";
//        String templateName = "final1.ftl";
        String templateName = "temp.xml";
        String targetPath = "C:\\doc\\tomp";
        String targetName = "temp.doc";

        Map<String, Object> rsMap = getWrodFile(getParamMap(), templatePath,templateName, targetPath, targetName);
        System.out.println(rsMap.get("flg"));
        if(rsMap.containsKey("file")){
            System.out.println(rsMap.get("file"));
        }
    }

}

