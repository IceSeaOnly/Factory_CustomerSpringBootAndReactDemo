package site.binghai.biz.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.biz.entity.CustomerOrder;

import java.util.List;

/**
 * @author huaishuo
 * @date 2019/1/6 下午4:35
 **/
public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findByFactoryId(Long fid, Pageable pageable);

    Long countByFactoryId(Long fid);
}
