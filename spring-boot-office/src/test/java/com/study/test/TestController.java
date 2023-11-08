package com.study.test;

import com.study.controller.ExportController;
import com.study.controller.ImportController;
import com.study.controller.ZipController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    @Resource
    private ZipController zipController;


    @Resource
    private ImportController importController;

    private MockMvc mockMvc;

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private WebApplicationContext wac;

    /**
     * 在每次测试执行前构建mvc环境
     * 响应中文不乱码
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        .addFilter((request, response, chain) -> {
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        }, "/*").build();

    }

    /**
     * 文件夹压缩为zip 测试入口
     */
    @Test
    public void testZip() {
        Map<String, Object> rsMap = zipController.testZip();
        log.info("rsMap:{}", rsMap);
    }


    /**
     * 导入excel 测试入口
     * 构建文件方式
     */
    @Test
    public void importBcSerXyzpGwglVO(){
        try {
            File file = new File("D:\\workdir\\school\\spring-boot-office\\src\\main\\resources\\doc\\gw_import_test.xlsx");
            MultipartFile multipartFile = new MockMultipartFile(
                "ab.jpg", //文件名
                "ab.jpg", //originalName 相当于上传文件在客户机上的文件名
                MediaType.ALL_VALUE, //文件类型
                new FileInputStream(file) //文件流
            );
            Map<String, Object> rsMap = importController.importBcSerXyzpGwglVO(multipartFile,1);
            log.debug("rsMap:{}", rsMap);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 导入excel 测试入口2
     * mock请求方式
     */
    @Test
    public void importBcSerXyzpGwglVO2(){
        File file = new File("D:\\workdir\\school\\spring-boot-office\\src\\main\\resources\\doc\\gw_import_test.xlsx");
        try {

            MultipartFile multipartFile = new MockMultipartFile(
                    "gw_import_test.xlsx", //文件名
                    "gw_import_test.xlsx", //originalName 相当于上传文件在客户机上的文件名
                    MediaType.ALL_VALUE, //文件类型
                    new FileInputStream(file) //文件流
            );

            String result = mockMvc.perform(MockMvcRequestBuilders.multipart("/importBcSerXyzpGwglVO")
                    .file("file", multipartFile.getBytes())
                    .characterEncoding("UTF-8")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
            log.debug("rsMap:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 导出excel
     */
    @Test
    public void exportExcel(){
        try {
            String result =  mockMvc.perform(MockMvcRequestBuilders.get("/exportExcel")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
            log.debug("rsMap:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
