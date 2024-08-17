package cn.edu.bjtu.citel.controller;

import cn.edu.bjtu.citel.domain.UserSearchHistory;
import cn.edu.bjtu.citel.enums.usersearchhistory.SelectTypeEnum;
import cn.edu.bjtu.citel.result.ErrorCode;
import cn.edu.bjtu.citel.result.R;
import cn.edu.bjtu.citel.service.IUserSearchHistoryService;
import cn.edu.bjtu.citel.vo.SearchRangeVO;
import cn.edu.bjtu.citel.vo.UserSearchHistoryVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @Date created in 2024-08-16 17:39
 */
@RestController
@RequestMapping("/userSearchHistory")
public class UserSearchHistoryController {

    @Autowired
    private IUserSearchHistoryService userSearchHistoryService;


    @PutMapping("/addList")
    public R<Boolean> addList(@RequestBody UserSearchHistoryVO userSearchHistoryVO) {
        Integer selectType = userSearchHistoryVO.getSelectType();
        if (selectType == null || SelectTypeEnum.getByValue(selectType) == null) {
            return R.success(Boolean.FALSE);
        }
        List<SearchRangeVO> searchRanges = userSearchHistoryVO.getSearchRanges();
        if (CollectionUtil.isEmpty(searchRanges)) {
            return R.success(Boolean.FALSE);
        }
        List<UserSearchHistory> userSearchHistories = new ArrayList<>();
        searchRanges.forEach(searchRange -> {
            UserSearchHistory userSearchHistory = new UserSearchHistory();
            userSearchHistory.setSelectType(selectType);
            Long beginValue = searchRange.getBeginValue();
            if (ObjectUtil.isEmpty(beginValue)) {
                return;
            }
            userSearchHistory.setBeginValue(beginValue);
            Long endValue = searchRange.getEndValue();
            if (ObjectUtil.isEmpty(endValue)) {
                return;
            }
            userSearchHistory.setEndValue(endValue);
            userSearchHistory.setCreateTime(new Date());
            //用户生日年份、用户飞行里程、用户飞行时间不可重复
            if (userSearchHistoryService.checkExist(userSearchHistory)) {
                return;
            }
            /*if (SelectTypeEnum.TRAVEL_TIME_TYPE.getValue().equals(selectType)) {
                if (userSearchHistoryService.checkIntervalCoincide(userSearchHistory)) {
                    return;
                }
            }*/
            userSearchHistories.add(userSearchHistory);
        });
        userSearchHistoryService.addList(userSearchHistories);
        return R.success(Boolean.TRUE);
    }

    @PostMapping("/getListPage")
    public R<Page<UserSearchHistory>> getListPage(@RequestBody UserSearchHistoryVO searchHistoryVO, @RequestParam Long page, @RequestParam Long pageSize) {
        Integer selectType = searchHistoryVO.getSelectType();
        if (ObjectUtil.isNull(selectType)) {
            selectType = -1;
        }
        IPage<UserSearchHistory> userSearchHistoryIPage = userSearchHistoryService.getListPage(selectType, page, pageSize);
        Page<UserSearchHistory> userSearchHistoryPage = new Page<>(page, pageSize);
        userSearchHistoryPage.setPages(userSearchHistoryIPage.getPages());
        userSearchHistoryPage.setRecords(userSearchHistoryIPage.getRecords());
        userSearchHistoryPage.setTotal(userSearchHistoryIPage.getTotal());
        return R.success(userSearchHistoryPage);
    }


    @GetMapping("/checkExist")
    public R<Boolean> checkExist(UserSearchHistoryVO userSearchHistoryVO) {
        Integer selectType = userSearchHistoryVO.getSelectType();
        SearchRangeVO searchRange = userSearchHistoryVO.getSearchRange();
        if (ObjectUtil.isNull(selectType) || searchRange == null || ObjectUtil.isEmpty(searchRange.getBeginValue()) || ObjectUtil.isEmpty(searchRange.getEndValue())) {
            return R.error(ErrorCode.PARAM_ILLEGAL_ERROR_CODE, ErrorCode.PARAM_ILLEGAL_ERROR_MESSAGE);
        }
        UserSearchHistory userSearchHistory = new UserSearchHistory();
        userSearchHistory.setSelectType(selectType);
        userSearchHistory.setBeginValue(searchRange.getBeginValue());
        userSearchHistory.setEndValue(searchRange.getEndValue());
        Boolean existed = userSearchHistoryService.checkExist(userSearchHistory);
        return R.success(existed);
    }

    @GetMapping("/checkIntervalCoincide")
    public R<Boolean> checkIntervalCoincide(UserSearchHistoryVO userSearchHistoryVO) {
        Integer selectType = userSearchHistoryVO.getSelectType();
        SearchRangeVO searchRange = userSearchHistoryVO.getSearchRange();
        if (ObjectUtil.isNull(selectType) || searchRange == null || ObjectUtil.isEmpty(searchRange.getBeginValue()) || ObjectUtil.isEmpty(searchRange.getEndValue())) {
            return R.error(ErrorCode.PARAM_ILLEGAL_ERROR_CODE, ErrorCode.PARAM_ILLEGAL_ERROR_MESSAGE);
        }
        UserSearchHistory userSearchHistory = new UserSearchHistory();
        userSearchHistory.setSelectType(selectType);
        userSearchHistory.setBeginValue(searchRange.getBeginValue());
        userSearchHistory.setEndValue(searchRange.getEndValue());
        Boolean existed = userSearchHistoryService.checkIntervalCoincide(userSearchHistory);
        return R.success(existed);
    }
}
