package site.binghai.biz.enums;

/**
 * @author huaishuo
 * @date 2019/1/2 下午10:15
 **/
public enum DoorTypeEnum {

    ASSEMBLE("拼装门"),

    BAKING_PAINT("烤漆门"),

    ANTI_THEFT("防盗门"),
    ;

    private String doorName;

    DoorTypeEnum(String doorName) {
        this.doorName = doorName;
    }

    public String getDoorName() {
        return doorName;
    }

}
