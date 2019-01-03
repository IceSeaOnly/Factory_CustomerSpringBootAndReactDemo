package site.binghai.lib.entity;

import site.binghai.lib.enums.OrderStatusEnum;
import site.binghai.lib.enums.PayBizEnum;

import javax.persistence.MappedSuperclass;

/**
 * 具有支付状态的实体父类
 * */
@MappedSuperclass
public abstract class PayBizEntity extends BaseEntity {
    private Long userId;
    private Boolean paid;
    private Long unifiedId;
    /**
     * {@link OrderStatusEnum}
     * */
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Long getUnifiedId() {
        return unifiedId;
    }

    public void setUnifiedId(Long unifiedId) {
        this.unifiedId = unifiedId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public abstract PayBizEnum getBizType();
}
