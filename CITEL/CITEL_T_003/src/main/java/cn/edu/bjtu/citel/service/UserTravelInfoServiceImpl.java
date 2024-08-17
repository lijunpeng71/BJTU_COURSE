package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.UserTravelInfo;
import cn.edu.bjtu.citel.enums.usersearchhistory.SelectTypeEnum;
import cn.edu.bjtu.citel.repository.UserTravelInfoMapper;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 21:47
 */
@Service
public class UserTravelInfoServiceImpl implements IUserTravelInfoService {

    @Autowired
    private UserTravelInfoMapper userTravelInfoMapper;

    @Override
    public IPage<UserTravelInfo> getListPage(Integer selectType, Long beginValue, Long endValue, Long page, Long pageSize) {
        Page<UserTravelInfo> pageParam = new Page<>(page, pageSize);
        QueryWrapper<UserTravelInfo> queryWrapper = new QueryWrapper<>();
        if (SelectTypeEnum.BIRTHDAY_YEAR_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getBirthdayYear, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getBirthdayYear, endValue);
            }
        } else if (SelectTypeEnum.TRAVEL_MILEAGE_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getTotalTravelMileage, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getTotalTravelMileage, endValue);
            }
        } else if (SelectTypeEnum.TRAVEL_TIME_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getTotalTravelTime, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getTotalTravelTime, endValue);
            }
        }
        return userTravelInfoMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Long getCountByRange(Integer selectType, Long beginValue, Long endValue) {
        QueryWrapper<UserTravelInfo> queryWrapper = new QueryWrapper<>();
        if (SelectTypeEnum.BIRTHDAY_YEAR_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getBirthdayYear, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getBirthdayYear, endValue);
            }
        } else if (SelectTypeEnum.TRAVEL_MILEAGE_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getTotalTravelMileage, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getTotalTravelMileage, endValue);
            }
        } else if (SelectTypeEnum.TRAVEL_TIME_TYPE.getValue().equals(selectType)) {
            if (ObjectUtil.isNotEmpty(beginValue)) {
                queryWrapper.lambda().ge(UserTravelInfo::getTotalTravelTime, beginValue);
            }
            if (ObjectUtil.isNotEmpty(endValue)) {
                queryWrapper.lambda().le(UserTravelInfo::getTotalTravelTime, endValue);
            }
        }
        return userTravelInfoMapper.selectCount(queryWrapper);
    }
}
