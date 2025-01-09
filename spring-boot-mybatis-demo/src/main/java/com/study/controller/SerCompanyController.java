package com.study.controller;

import com.study.model.SerCompanyVO;
import com.study.service.SerCompanyService;
import com.study.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SerCompanyController {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SerCompanyService serCompanyService;

    /**
     * 查询公司信息列表
     * http://localhost:8080/querySerCompanyList
     * @return
     */
    @RequestMapping(method= RequestMethod.GET, value = "querySerCompanyList")
    @ResponseBody
    public List<SerCompanyVO> querySerCompanyList(){
        if(log.isDebugEnabled()){
            log.debug("do querySerCompanyList begin.");
        }
        return serCompanyService.querySerCompanyList();
    }

    /**
     * 查询公司信息根据id
     * http://localhost:8080/querySerCompanyById?companyId=1
     * @return
     */
    @RequestMapping(method= RequestMethod.GET, value = "querySerCompanyById")
    @ResponseBody
    public SerCompanyVO querySerCompanyById(Integer companyId){
        return serCompanyService.querySerCompanyById(companyId);
    }

    /**
     * 新增公司固定字段
     * @param serCompanyVO
     * http://localhost:8080/addCompany?companyName=百度&companyCode=321&companyMsg=百度是一家上市公司
     * @return
     */
    @RequestMapping(method= RequestMethod.GET, value = "addCompany")
    @ResponseBody
    public Map<String, Object> addCompany(SerCompanyVO serCompanyVO){
        if(log.isDebugEnabled()){
            log.debug("companyName:{}", serCompanyVO.getCompanyName());
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serCompanyVO.setCompanyCrdt(timestamp.toString());
        int num = serCompanyService.addCompany(serCompanyVO);
        Map<String ,Object> map =  ResultUtil.getRsMap(num);
        map.put("serCompanyVO", serCompanyVO);
        return map;
    }

    /**
     * 新增公司动态字段
     * http://localhost:8080/addCompanyDynamic?companyName=字节跳动&companyCode=&companyMsg=
     * @param serCompanyVO
     * @return
     */
    @RequestMapping(method= RequestMethod.GET, value = "addCompanyDynamic")
    @ResponseBody
    public Map<String, Object> addCompanyDynamic(SerCompanyVO serCompanyVO){
        if (log.isDebugEnabled()){
            log.debug("companyName:{}", serCompanyVO.getCompanyName());
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serCompanyVO.setCompanyCrdt(timestamp.toString());
        int num = serCompanyService.addCompanyDynamic(serCompanyVO);
        Map<String ,Object> map =  ResultUtil.getRsMap(num);
        map.put("serCompanyVO", serCompanyVO);
        return map;
    }

    /**
     * 批量新增公司
     * http://localhost:8080/addCompanyBatch
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "addCompanyBatch")
    @ResponseBody
    public Map<String, Object> addCompanyBatch(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStr = timestamp.toString();
        SerCompanyVO serCompanyVO = new SerCompanyVO();
        serCompanyVO.setCompanyName("导入1");
        serCompanyVO.setCompanyCode("3301");
        serCompanyVO.setCompanyCrdt(timeStr);
        SerCompanyVO serCompanyVO1 = new SerCompanyVO();
        serCompanyVO1.setCompanyName("导入2");
        serCompanyVO1.setCompanyCode("3302");
        serCompanyVO1.setCompanyCrdt(timeStr);
        List<SerCompanyVO> list = new ArrayList<>();
        list.add(serCompanyVO);
        list.add(serCompanyVO1);
        int num = serCompanyService.addCompanyBatch(list);
        return ResultUtil.getRsMap(num);
    }

    /**
     * 修改公司信息 固定语句
     * http://localhost:8080/updateCompany?companyId=13&companyName=百度2&companyCode=333&companyMsg=百度2是一家上市公司
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "updateCompany")
    @ResponseBody
    public Map<String, Object> updateCompany(SerCompanyVO serCompanyVO){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStr = timestamp.toString();
        serCompanyVO.setCompanyCrdt(timeStr);
        int num = serCompanyService.updateCompany(serCompanyVO);
        return  ResultUtil.getRsMap(num);
    }

    /**
     * 修改公司信息 动态语句
     * http://localhost:8080/updateCompanyDynamic?companyId=13&companyName=百度3&companyCode=333
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "updateCompanyDynamic")
    @ResponseBody
    public Map<String, Object> updateCompanyDynamic(SerCompanyVO serCompanyVO){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStr = timestamp.toString();
        serCompanyVO.setCompanyCrdt(timeStr);
        int num = serCompanyService.updateCompanyDynamic(serCompanyVO);
        return  ResultUtil.getRsMap(num);
    }

    /**
     * 删除公司信息
     * http://localhost:8080/deleteCompanyById?companyId=15
     * @param companyId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "deleteCompanyById")
    @ResponseBody
    public Map<String, Object> deleteCompanyById(Integer companyId){
        int num = serCompanyService.deleteCompanyById(companyId);
        return  ResultUtil.getRsMap(num);
    }

    /**
     * 查询公司信息 包含标签集合 join 方式
     * http://localhost:8080/querySerCompanyListTagJoin
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "querySerCompanyListTagJoin")
    @ResponseBody
    public List<SerCompanyVO> querySerCompanyListTagJoin(){
        return serCompanyService.querySerCompanyListTagJoin();
    }


    /**
     * 查询公司信息 一对多 查询 asso方式
     * http://localhost:8080/querySerCompanyListTagAsso
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "querySerCompanyListTagAsso")
    @ResponseBody
    public List<SerCompanyVO> querySerCompanyListTagAsso(){
        return serCompanyService.querySerCompanyListTagAsso();
    }


    /**
     * http://localhost:8081/test
     * @param companyId
     * @return
     */
    @RequestMapping(value = "test")
    @ResponseBody
    public SerCompanyVO  test(String companyId){
        if(log.isDebugEnabled()){
            log.debug("do querySerCompanyList begin. {}" , companyId);
        }
        return serCompanyService.querySerCompanyByIdStr(companyId);
    }
}
