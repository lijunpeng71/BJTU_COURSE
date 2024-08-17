package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.UserSearchHistory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface IUserSearchHistoryService {
    /**
     * 分页查询用户查询历史
     *
     * @param selectType
     * @param page
     * @param pageSize
     * @return
     */
    IPage<UserSearchHistory> getListPage(Integer selectType, Long page, Long pageSize);

    /**
     * 批量新增
     *
     * @param userSearchHistories
     */
    public void addList(List<UserSearchHistory> userSearchHistories);

    /**
     * 校验数据是否存在
     *
     * @param userSearchHistory
     * @return
     */
    Boolean checkExist(UserSearchHistory userSearchHistory);

    /**
     * 校验区间重叠
     *
     * @param userSearchHistory
     * @return
     */
    Boolean checkIntervalCoincide(UserSearchHistory userSearchHistory);


}
