package cn.edu.bjtu.citel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 21:10
 */
@Data
@TableName("user_travel_info")
@FieldNameConstants
public class UserTravelInfo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "gender_id")
    private Integer genderId;

    @TableField(value = "birthday_year")
    private Integer birthdayYear;

    @TableField(value = "total_travel_mileage")
    private Double totalTravelMileage;

    @TableField(value = "total_travel_time")
    private Double totalTravelTime;
}
