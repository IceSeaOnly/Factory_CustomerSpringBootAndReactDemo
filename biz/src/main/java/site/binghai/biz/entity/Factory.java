package site.binghai.biz.entity;

import lombok.Data;
import site.binghai.lib.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author huaishuo
 * @date 2019/1/2 下午10:06
 **/
@Data
@Entity
public class Factory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String factoryName;
    /**
     * 车间数
     */
    private Integer workshopAmt;
    /**
     * 仓库数
     */
    private Integer storeAmt;
    /**
     * 工人数
     */
    private Integer workerAmt;
    /**
     * 设备数
     */
    private Integer equipmentAmt;
    /**
     * 设备磨损程度
     */
    private Double equipmentFailureRate;
}
