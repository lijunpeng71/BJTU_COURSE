package cn.edu.bjtu.citel.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @Date created in 2024-08-16 19:16
 */
@Data
public class UserSearchHistoryVO implements Serializable {

    /**
     * 查询类型
     *
     * @see cn.edu.bjtu.citel.enums.usersearchhistory.SelectTypeEnum
     */
    private Integer selectType;

    /**
     * 查询区间
     */
    private SearchRangeVO searchRange;

    /**
     * 查询区间集合
     */
    private List<SearchRangeVO> searchRanges;
}
