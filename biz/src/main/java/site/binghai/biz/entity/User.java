package site.binghai.biz.entity;

import lombok.Data;
import site.binghai.biz.enums.UserRule;
import site.binghai.lib.entity.BaseEntity;
import site.binghai.lib.interfaces.SessionPersistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author huaishuo
 * @date 2019/1/3 下午5:08
 **/
@Data
@Entity
public class User extends BaseEntity implements SessionPersistent {
    @Id
    @GeneratedValue
    private Long id;
    private String mail;
    private String userName;
    private String password;
    private Long factoryId;
    /**
     * 角色
     *
     * @see UserRule
     */
    private String rule;
}
