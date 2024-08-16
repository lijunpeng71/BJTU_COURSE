package cn.edu.bjtu.citel.controller;

import cn.edu.bjtu.citel.domain.UserTravelInfo;
import cn.edu.bjtu.citel.enums.usertravelinfo.SelectTypeEnum;
import cn.edu.bjtu.citel.result.R;
import cn.edu.bjtu.citel.service.IUserTravelInfoService;
import cn.edu.bjtu.citel.vo.SearchRangeVO;
import cn.edu.bjtu.citel.vo.UserTravelInfoVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 21:51
 */
@RestController
@RequestMapping("/userTravelInfo")
public class UserTravelInfoController {

    @Autowired
    private IUserTravelInfoService userTravelInfoService;

    @PostMapping("/getListPage")
    public R<Page<UserTravelInfo>> getListPage(@RequestBody UserTravelInfoVO userTravelInfoVO, @RequestParam Long page, @RequestParam Long pageSize) {
        Integer selectType = userTravelInfoVO.getSelectType();
        SearchRangeVO searchRange = userTravelInfoVO.getSearchRange();
        String beginValue = null;
        String endValue = null;
        if (!Objects.isNull(searchRange)) {
            beginValue = searchRange.getBeginValue();
            endValue = searchRange.getEndValue();
        }
        IPage<UserTravelInfo> userTravelInfoIPage = userTravelInfoService.getListPage(selectType, beginValue, endValue, page, pageSize);
        Page<UserTravelInfo> userTravelInfoPage = new Page<>(page, pageSize);
        userTravelInfoPage.setPages(userTravelInfoIPage.getPages());
        userTravelInfoPage.setRecords(userTravelInfoIPage.getRecords());
        userTravelInfoPage.setTotal(userTravelInfoIPage.getTotal());
        return R.success(userTravelInfoPage);
    }


    @PostMapping("/getStatisticMapByRange")
    public R<Map<String, Object>> getStatisticMapByRange(@RequestBody UserTravelInfoVO userTravelInfoVO) {
        Integer selectType = userTravelInfoVO.getSelectType();
        List<SearchRangeVO> searchRanges = userTravelInfoVO.getSearchRanges();
        Map<String, Object> statisticMap = new HashMap<>();
        SelectTypeEnum selectTypeEnum = SelectTypeEnum.getByValue(selectType);
        statisticMap.put("subTitle", selectTypeEnum == null ? null : selectTypeEnum.getDesc());
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<Long> valueList = new ArrayList<>();
        if (CollectionUtil.isEmpty(searchRanges)) {
            Long count = userTravelInfoService.getCountByRange(selectType, null, null);
            Map<String, Object> allMap = new HashMap<>();
            allMap.put("name", "all");
            allMap.put("value", count);
            mapList.add(allMap);
            statisticMap.put("mapList", mapList);
            nameList.add("all");
            statisticMap.put("nameList", nameList);
            valueList.add(count);
            statisticMap.put("valueList", valueList);
            return R.success(statisticMap);
        }
        searchRanges.forEach(searchRange -> {
            Long count = userTravelInfoService.getCountByRange(selectType, searchRange.getBeginValue(), searchRange.getEndValue());
            String key = searchRange.getBeginValue() + "-" + searchRange.getEndValue();
            Map<String, Object> nameValueMap = new HashMap<>();
            nameValueMap.put("name", key);
            nameValueMap.put("value", count);
            nameList.add(key);
            valueList.add(count);
            mapList.add(nameValueMap);
        });
        statisticMap.put("mapList", mapList);
        statisticMap.put("nameList", nameList);
        statisticMap.put("valueList", valueList);
        return R.success(statisticMap);
    }

}
