package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.UserTravelInfo;
import cn.edu.bjtu.citel.vo.SearchRangeVO;
import cn.edu.bjtu.citel.vo.UserTravelInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 21:47
 */
public interface IUserTravelInfoService {

    /**
     * 查询列表(分页)
     *
     * @param selectType : 查询类型
     * @param beginValue : 开始值
     * @param endValue   : 结束值
     * @param page:      当前页
     * @param pageSize:  每页记录数
     * @return
     */
    IPage<UserTravelInfo> getListPage(Integer selectType, String beginValue, String endValue, Long page, Long pageSize);


    /**
     * 根据生日年份区间查询
     *
     * @param selectType: 查询类型
     * @param beginValue: 查询开始时间
     * @param endValue    : 查询结束时间
     * @return : 返回记录数
     */
    Long getCountByRange(Integer selectType, String beginValue, String endValue);
}
