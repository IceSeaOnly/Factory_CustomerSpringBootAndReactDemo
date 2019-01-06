package site.binghai.biz.service;

import org.springframework.stereotype.Service;
import site.binghai.biz.entity.User;
import site.binghai.lib.def.SystemException;
import site.binghai.lib.service.BaseService;

import javax.transaction.Transactional;

import static site.binghai.lib.enums.ErrorCode.*;

/**
 * @author huaishuo
 * @date 2019/1/3 下午5:17
 **/
@Service
public class UserService extends BaseService<User> {

    public User findByMailAndPass(String mail, String pass) {
        User query = new User();
        query.setMail(mail);
        query.setPassword(pass);
        return queryOne(query);
    }

    @Transactional
    public User reg(User user) throws SystemException {

        AssertNotEmpty(user.getRule(), MISSING_RULE);

        if (findByMailAndPass(user.getMail(), user.getPassword()) != null) {
            throw new SystemException(DUPLICATE_EXCEPTION);
        }

        return save(user);
    }
}
