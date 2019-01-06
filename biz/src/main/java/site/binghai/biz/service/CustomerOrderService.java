package site.binghai.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.biz.dao.CustomerOrderDao;
import site.binghai.biz.entity.CustomerOrder;
import site.binghai.lib.service.BaseService;

import java.util.List;

/**
 * @author huaishuo
 * @date 2019/1/6 下午4:31
 **/
@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {

    @Autowired
    private CustomerOrderDao customerOrderDao;

    public List<CustomerOrder> findByFactoryId(Long factoryId) {
        CustomerOrder example = new CustomerOrder();
        example.setFactoryId(factoryId);
        return query(example);
    }

    /**
     * 分页取，pageSize=100
     */
    public List<CustomerOrder> findByFactoryIdAndPage(Long factoryId, Integer page) {
        return customerOrderDao.findByFactoryId(factoryId, new PageRequest(page, 100));
    }

    @Override
    protected JpaRepository<CustomerOrder, Long> getDao() {
        return customerOrderDao;
    }

    public Long countByFactoryId(Long factoryId) {
        return customerOrderDao.countByFactoryId(factoryId);
    }
}
