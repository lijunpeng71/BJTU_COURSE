package cn.edu.bjtu.citel.service;

import cn.edu.bjtu.citel.domain.SysStaff;
import cn.edu.bjtu.citel.repository.SysStaffMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 11:23
 */
@Service
public class SysStaffServiceImpl implements ISysStaffService {

    @Autowired
    private SysStaffMapper sysStaffMapper;

    @Override
    public List<SysStaff> getList() {
        return sysStaffMapper.selectList(new QueryWrapper<>());
    }
}
