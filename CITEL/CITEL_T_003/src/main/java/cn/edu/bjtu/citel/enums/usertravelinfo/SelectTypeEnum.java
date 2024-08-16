package cn.edu.bjtu.citel.enums.usertravelinfo;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum SelectTypeEnum implements Serializable {
    BIRTHDAY_YEAR_TYPE(1, "生日年份"),
    TRAVEL_MILEAGE_TYPE(2, "飞行里程"),
    TRAVEL_TIME_TYPE(3, "飞行时间");
    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

    SelectTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static SelectTypeEnum getByValue(Integer value) {
        SelectTypeEnum[] values = SelectTypeEnum.values();
        for (SelectTypeEnum selectTypeEnum : values) {
            if (selectTypeEnum.value.equals(value)) {
                return selectTypeEnum;
            }
        }
        return null;
    }
}
