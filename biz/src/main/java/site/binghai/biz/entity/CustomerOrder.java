package site.binghai.biz.entity;

import lombok.Data;
import site.binghai.biz.enums.DoorTypeEnum;
import site.binghai.lib.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author huaishuo
 * @date 2019/1/2 下午10:11
 **/
@Data
@Entity
public class CustomerOrder extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 工厂id
     */
    private Long factoryId;
    private String startTime;
    private String endTime;
    /**
     * 评价
     */
    private String estimate;
    private String customerName;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 门类型
     *
     * @see DoorTypeEnum
     */
    private String doorType;
    private Integer orderAmt;

}
