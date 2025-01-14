package cn.edu.bjtu.citel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 21:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_travel_info")
public class UserTravelInfo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField
    private Long userId;

    /**
     * 性别
     */
    @TableField
    private Integer genderId;

    /**
     * 生日年份
     */
    @TableField
    private Integer birthdayYear;

    /**
     * 总飞行里程
     */
    @TableField
    private Double totalTravelMileage;

    /**
     * 总飞行时间
     */
    @TableField
    private Double totalTravelTime;
}
