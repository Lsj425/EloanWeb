package com.sj.p2p.common.pojo;

import com.sj.p2p.common.util.BitStatesUtils;

public class UserInfo {

    // 基本信息
    private Long id;

    private Integer version = 0;

    private Long bitState = 0L;

    // 实名基本信息
    private String realName;

    private String idNumber;

    private String phoneNumber;

    // 下拉框选项信息
    private Long incomeGradeId;

    private Long marriageId;

    private Long kidCountId;

    private Long educationBackgroundId;

    private Integer authScore;

    private Long houseConditionId;

    private Long realAuthId;

    private String email;

    // 个人资料下拉选中信息
    private SystemDictionaryItem educationBackgroundItem;

    private SystemDictionaryItem incomeGradeItem;

    private SystemDictionaryItem marriageItem;

    private SystemDictionaryItem kidCountItem;

    private SystemDictionaryItem houseConditionItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getBitState() {
        return bitState;
    }

    public void setBitState(Long bitState) {
        this.bitState = bitState;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realname) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Long getIncomeGradeId() {
        return incomeGradeId;
    }

    public void setIncomeGradeId(Long incomeGradeId) {
        this.incomeGradeId = incomeGradeId;
    }

    public Long getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Long marriageId) {
        this.marriageId = marriageId;
    }

    public Long getKidCountId() {
        return kidCountId;
    }

    public void setKidCountId(Long kidCountId) {
        this.kidCountId = kidCountId;
    }

    public Long getEducationBackgroundId() {
        return educationBackgroundId;
    }

    public void setEducationBackgroundId(Long educationBackgroundId) {
        this.educationBackgroundId = educationBackgroundId;
    }

    public Integer getAuthScore() {
        return authScore;
    }

    public void setAuthScore(Integer authScore) {
        this.authScore = authScore;
    }

    public Long getHouseConditionId() {
        return houseConditionId;
    }

    public void setHouseConditionId(Long houseConditionId) {
        this.houseConditionId = houseConditionId;
    }

    public Long getRealAuthId() {
        return realAuthId;
    }

    public void setRealAuthId(Long realAuthId) {
        this.realAuthId = realAuthId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public SystemDictionaryItem getEducationBackgroundItem() {
        return educationBackgroundItem;
    }

    public void setEducationBackgroundItem(SystemDictionaryItem educationBackgroundItem) {
        this.educationBackgroundItem = educationBackgroundItem;
    }

    public SystemDictionaryItem getIncomeGradeItem() {
        return incomeGradeItem;
    }

    public void setIncomeGradeItem(SystemDictionaryItem incomeGradeItem) {
        this.incomeGradeItem = incomeGradeItem;
    }

    public SystemDictionaryItem getMarriageItem() {
        return marriageItem;
    }

    public void setMarriageItem(SystemDictionaryItem marriageItem) {
        this.marriageItem = marriageItem;
    }

    public SystemDictionaryItem getKidCountItem() {
        return kidCountItem;
    }

    public void setKidCountItem(SystemDictionaryItem kidCountItem) {
        this.kidCountItem = kidCountItem;
    }

    public SystemDictionaryItem getHouseConditionItem() {
        return houseConditionItem;
    }

    public void setHouseConditionItem(SystemDictionaryItem houseConditionItem) {
        this.houseConditionItem = houseConditionItem;
    }

    // 添加绑定的状态码
    public void addState(Long state) {
        bitState = BitStatesUtils.addState(bitState, state);
    }

    // 移除状态码
    public void removeState(Long state) {
        bitState = BitStatesUtils.removeState(bitState, state);
    }

    // 判断用户是否已经填写了基本资料
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_USER_INFO);
    }

    // 判断是否已经绑定了手机
    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_PHONE);
    }

    // 判断是否已经绑定了邮箱
    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_EMAIL);
    }

    // 判断是否已经有一个借款申请在申请流程中
    public boolean getIsApply() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", version=" + version +
                ", bitState=" + bitState +
                ", realName='" + realName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", incomeGradeId=" + incomeGradeId +
                ", marriageId=" + marriageId +
                ", kidCountId=" + kidCountId +
                ", educationBackgroundId=" + educationBackgroundId +
                ", authScore=" + authScore +
                ", houseConditionId=" + houseConditionId +
                ", realAuthId=" + realAuthId +
                ", email='" + email + '\'' +
                ", educationBackgroundItem=" + educationBackgroundItem +
                ", incomeGradeItem=" + incomeGradeItem +
                ", marriageItem=" + marriageItem +
                ", kidCountItem=" + kidCountItem +
                ", houseConditionItem=" + houseConditionItem +
                '}';
    }
}