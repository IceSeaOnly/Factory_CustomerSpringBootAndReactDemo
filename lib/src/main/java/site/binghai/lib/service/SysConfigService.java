package site.binghai.lib.service;

import org.springframework.stereotype.Service;
import site.binghai.lib.entity.SysConfig;

import java.util.List;

@Service
public class SysConfigService extends BaseService<SysConfig> {
    public boolean isSystemClosed() {
        List<SysConfig> configList = findAll(999);
        if (isEmptyList(configList)) {
            return false;
        }
        SysConfig config = configList.get(0);
        return config.getCloseSystem() == null ? false : config.getCloseSystem();
    }

    public String getCloseReason() {
        List<SysConfig> configList = findAll(999);
        if (isEmptyList(configList)) {
            return "系统临时关闭";
        }
        SysConfig config = configList.get(0);
        return config.getCloseMessage() == null ? "系统临时关闭" : config.getCloseMessage();
    }

    public void setSystem(SysConfig config) {
        deleteAll("confirm");
        save(config);
    }
}
