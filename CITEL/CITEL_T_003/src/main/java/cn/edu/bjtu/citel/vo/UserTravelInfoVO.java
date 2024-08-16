package cn.edu.bjtu.citel.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-10 19:14
 */
@Data
public class UserTravelInfoVO implements Serializable {

    /**
     * 查询类型 1: 用户生日年份; 2: 用户飞行里程; 3: 用户飞行时间
     */
    private Integer selectType;

    /**
     * 单条查询区间
     */
    private SearchRangeVO searchRange;

    /**
     * 多条查询区间
     */
    private List<SearchRangeVO> searchRanges;
}



