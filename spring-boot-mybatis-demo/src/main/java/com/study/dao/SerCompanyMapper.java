package com.study.dao;

import com.study.model.SerCompanyTagVO;
import com.study.model.SerCompanyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SerCompanyMapper {

    /**
     * 查询全部数据
     * @return
     */
    List<SerCompanyVO> selectSerCompanyList();

    /**
     * 根据id查询信息
     * @return
     */
    SerCompanyVO  selectSerCompanyById(Integer companyId);

    /**
     * 固定字段插入
     * @param serCompanyVO
     * @return
     */
    int insertSerCompanyVO(SerCompanyVO serCompanyVO);

    /**
     * 动态语句插入
     * @param serCompanyVO
     * @return
     */
    int insertSerCompanyDynamic(SerCompanyVO serCompanyVO);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertCompanyBatch(List<SerCompanyVO> list);

    /**
     * 修改信息
     * @param serCompanyVO
     * @return
     */
    int updateCompany(SerCompanyVO serCompanyVO);

    /**
     * 动态语句修改
     * @param serCompanyVO
     * @return
     */
    int updateCompanyDynamic(SerCompanyVO serCompanyVO);

    /**
     * 根据id删除信息
     * @param companyId
     * @return
     */
    int deleteCompanyById(Integer companyId);

    /**
     * 一对多查询公司信息 包含公司标签 join 方式
     * @return
     */
    List<SerCompanyVO> selectSerCompanyListTagJoin();

    /**
     * 一对多查询公司信息 包含公司标签 asso 方式
     * @return
     */
    List<SerCompanyVO> selectSerCompanyListTagAsso();

    /**
     * 一对多查询公司信息 包含公司标签 asso 方式
     * @param companyId
     * @return
     */
    SerCompanyTagVO getSerCompanyTag(int companyId);
}
