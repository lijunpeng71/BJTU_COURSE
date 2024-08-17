package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.UserSearchHistory;
import cn.edu.bjtu.citel.repository.UserSearchHistoryMapper;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @Date created in 2024-08-16 17:38
 */
@Service
public class UserSearchHistoryServiceImpl implements IUserSearchHistoryService {

    @Autowired
    private UserSearchHistoryMapper userSearchHistoryMapper;

    @Override
    public IPage<UserSearchHistory> getListPage(Integer selectType, Long page, Long pageSize) {
        Page<UserSearchHistory> pageParam = new Page<>(page, pageSize);
        QueryWrapper<UserSearchHistory> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(selectType)) {
            queryWrapper.lambda().eq(UserSearchHistory::getSelectType, selectType);
        }
        return userSearchHistoryMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public void addList(List<UserSearchHistory> userSearchHistories) {
        if (CollectionUtil.isEmpty(userSearchHistories)) {
            return;
        }
        userSearchHistoryMapper.insert(userSearchHistories);
    }

    @Override
    public Boolean checkExist(UserSearchHistory userSearchHistory) {
        QueryWrapper<UserSearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserSearchHistory::getSelectType, userSearchHistory.getSelectType()).eq(UserSearchHistory::getBeginValue, userSearchHistory.getBeginValue()).eq(UserSearchHistory::getEndValue, userSearchHistory.getEndValue());
        Long count = userSearchHistoryMapper.selectCount(queryWrapper);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean checkIntervalCoincide(UserSearchHistory userSearchHistory) {
        QueryWrapper<UserSearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserSearchHistory::getSelectType, userSearchHistory.getSelectType());
        queryWrapper.lambda().or().ge(UserSearchHistory::getBeginValue, userSearchHistory.getBeginValue()).le(UserSearchHistory::getEndValue, userSearchHistory.getBeginValue());
        queryWrapper.lambda().or().ge(UserSearchHistory::getBeginValue, userSearchHistory.getEndValue()).le(UserSearchHistory::getEndValue, userSearchHistory.getEndValue());
        Long count = userSearchHistoryMapper.selectCount(queryWrapper);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
