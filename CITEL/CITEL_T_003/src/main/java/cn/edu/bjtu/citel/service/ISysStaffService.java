package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.SysStaff;

import java.util.List;

public interface ISysStaffService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<SysStaff> getList();
}
