/* ccbckj code generator V2 */
package com.study.model;

public class BcSerXyzpGwglVO {
    /**
     * 主键id
     */
    private Integer gwglId;

    /**
     * 招聘单位主管部门（吉林省教育厅）
     */
    private String gwglZgbm;

    /**
     * 招聘单位id(bc_ser_bm的bm_id)
     */
    private Integer gwglBmid;

    /**
     * 招聘部门名称
     */
    private String gwglBmmc;

    /**
     * 招聘岗位名称
     */
    private String gwglGwmc;

    /**
     * 招聘岗位级别（字典项code=20231008029）
     */
    private Integer gwglGwjb;

    //excel导入行号
    private  Integer rowIndex;

    //状态
    private  Integer gwglZt;

    private String gwglGgxh;

    private Integer gwglZpggid;

    private Integer gwglJfxs;

    /**
     * 获取 主键id bc_ser_xyzp_gwgl.gwgl_id
     * @return 主键id
     */
    public Integer getGwglId() {
        return gwglId;
    }

    /**
     * 设置 主键id bc_ser_xyzp_gwgl.gwgl_id
     * @param gwglId 主键id
     */
    public void setGwglId(Integer gwglId) {
        this.gwglId = gwglId;
    }

    /**
     * 获取 招聘单位主管部门（吉林省教育厅） bc_ser_xyzp_gwgl.gwgl_zgbm
     * @return 招聘单位主管部门（吉林省教育厅）
     */
    public String getGwglZgbm() {
        return gwglZgbm;
    }

    /**
     * 设置 招聘单位主管部门（吉林省教育厅） bc_ser_xyzp_gwgl.gwgl_zgbm
     * @param gwglZgbm 招聘单位主管部门（吉林省教育厅）
     */
    public void setGwglZgbm(String gwglZgbm) {
        this.gwglZgbm = gwglZgbm == null ? null : gwglZgbm.trim();
    }

    /**
     * 获取 招聘单位id(bc_ser_bm的bm_id) bc_ser_xyzp_gwgl.gwgl_bmid
     * @return 招聘单位id(bc_ser_bm的bm_id)
     */
    public Integer getGwglBmid() {
        return gwglBmid;
    }

    /**
     * 设置 招聘单位id(bc_ser_bm的bm_id) bc_ser_xyzp_gwgl.gwgl_bmid
     * @param gwglBmid 招聘单位id(bc_ser_bm的bm_id)
     */
    public void setGwglBmid(Integer gwglBmid) {
        this.gwglBmid = gwglBmid;
    }

    /**
     * 获取 招聘部门名称 bc_ser_xyzp_gwgl.gwgl_bmmc
     * @return 招聘部门名称
     */
    public String getGwglBmmc() {
        return gwglBmmc;
    }

    /**
     * 设置 招聘部门名称 bc_ser_xyzp_gwgl.gwgl_bmmc
     * @param gwglBmmc 招聘部门名称
     */
    public void setGwglBmmc(String gwglBmmc) {
        this.gwglBmmc = gwglBmmc == null ? null : gwglBmmc.trim();
    }

    /**
     * 获取 招聘岗位名称 bc_ser_xyzp_gwgl.gwgl_gwmc
     * @return 招聘岗位名称
     */
    public String getGwglGwmc() {
        return gwglGwmc;
    }

    /**
     * 设置 招聘岗位名称 bc_ser_xyzp_gwgl.gwgl_gwmc
     * @param gwglGwmc 招聘岗位名称
     */
    public void setGwglGwmc(String gwglGwmc) {
        this.gwglGwmc = gwglGwmc == null ? null : gwglGwmc.trim();
    }

    /**
     * 获取 招聘岗位级别（字典项code=20231008029） bc_ser_xyzp_gwgl.gwgl_gwjb
     * @return 招聘岗位级别（字典项code=20231008029）
     */
    public Integer getGwglGwjb() {
        return gwglGwjb;
    }

    /**
     * 设置 招聘岗位级别（字典项code=20231008029） bc_ser_xyzp_gwgl.gwgl_gwjb
     * @param gwglGwjb 招聘岗位级别（字典项code=20231008029）
     */
    public void setGwglGwjb(Integer gwglGwjb) {
        this.gwglGwjb = gwglGwjb;
    }


    public Integer getGwglZt() {
        return gwglZt;
    }

    public void setGwglZt(Integer gwglZt) {
        this.gwglZt = gwglZt;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getGwglGgxh() {
        return gwglGgxh;
    }

    public void setGwglGgxh(String gwglGgxh) {
        this.gwglGgxh = gwglGgxh;
    }

    public Integer getGwglZpggid() {
        return gwglZpggid;
    }

    public void setGwglZpggid(Integer gwglZpggid) {
        this.gwglZpggid = gwglZpggid;
    }

    public Integer getGwglJfxs() {
        return gwglJfxs;
    }

    public void setGwglJfxs(Integer gwglJfxs) {
        this.gwglJfxs = gwglJfxs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gwglId=").append(gwglId);
        sb.append(", gwglZgbm=").append(gwglZgbm);
        sb.append(", gwglBmid=").append(gwglBmid);
        sb.append(", gwglBmmc=").append(gwglBmmc);
        sb.append(", gwglGwmc=").append(gwglGwmc);
        sb.append(", gwglGwjb=").append(gwglGwjb);
        sb.append("]");
        return sb.toString();
    }


}
