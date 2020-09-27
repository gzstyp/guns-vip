package cn.stylefeng.guns.sys.modular.rest.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.constant.state.BizLogType;
import cn.stylefeng.guns.sys.modular.rest.entity.RestOperationLog;
import cn.stylefeng.guns.sys.modular.rest.model.LogQueryParam;
import cn.stylefeng.guns.sys.modular.rest.service.RestOperationLogService;
import cn.stylefeng.guns.sys.modular.system.warpper.LogWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/rest/log")
public class RestLogController extends BaseController {

    @Autowired
    private RestOperationLogService restOperationLogService;

    /**
     * 查询操作日志列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestBody LogQueryParam logQueryParam) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询操作日志
        List<Map<String, Object>> result = restOperationLogService.getOperationLogs(
                page, logQueryParam.getBeginTime(), logQueryParam.getEndTime(),
                logQueryParam.getLogName(), BizLogType.valueOf(logQueryParam.getLogType()));

        page.setRecords(new LogWrapper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 查询操作日志详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable Long id) {
        RestOperationLog operationLog = restOperationLogService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(operationLog);
        return new LogWrapper(stringObjectMap).wrap();
    }

    /**
     * 清空日志
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @BussinessLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    public ResponseData delLog() {
        restOperationLogService.remove(new QueryWrapper<>());
        return SUCCESS_TIP;
    }
}
