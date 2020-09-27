package cn.stylefeng.guns.sys.modular.rest.controller;

import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.rest.model.LogQueryParam;
import cn.stylefeng.guns.sys.modular.rest.service.RestLoginLogService;
import cn.stylefeng.guns.sys.modular.system.warpper.LogWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@RestController
@RequestMapping("/rest/loginLog")
public class RestLoginLogController extends BaseController {

    @Autowired
    private RestLoginLogService restLoginLogService;

    /**
     * 查询登录日志列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:51 PM
     */
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestBody LogQueryParam logQueryParam) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询日志
        List<Map<String, Object>> result = restLoginLogService.getLoginLogs(page,
                logQueryParam.getBeginTime(), logQueryParam.getEndTime(), logQueryParam.getLogName());
        page.setRecords(new LogWrapper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 清空日志
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:51 PM
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    public ResponseData delLog() {
        restLoginLogService.remove(new QueryWrapper<>());
        return SUCCESS_TIP;
    }
}
