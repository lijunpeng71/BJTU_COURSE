package cn.edu.bjtu.citel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-16 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_search_history")
public class UserSearchHistory implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 查询类型
     *
     * @see cn.edu.bjtu.citel.enums.usersearchhistory.SelectTypeEnum
     */
    @TableField
    private Integer selectType;

    /**
     * 开始时间
     */
    @TableField
    private Long beginValue;

    /**
     * 结束时间
     */
    @TableField
    private Long endValue;

    /**
     * 创建时间
     */
    @TableField
    private Date createTime;
}
