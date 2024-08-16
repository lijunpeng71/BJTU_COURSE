package cn.edu.bjtu.citel.controller;

import cn.edu.bjtu.citel.domain.SysStaff;
import cn.edu.bjtu.citel.result.R;
import cn.edu.bjtu.citel.service.ISysStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 10:58
 */
@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private ISysStaffService sysStaffService;

    @GetMapping({"/", "/index"})
    public R<List<SysStaff>> index() {
        List<SysStaff> staffList = sysStaffService.getList();
        return R.success(staffList);
    }
}
