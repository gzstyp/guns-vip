package cn.stylefeng.guns.modular.demos.controller;

import cn.stylefeng.guns.modular.demos.service.TranTestService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源回滚
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/tran/multi")
public class TestMultiTranController extends BaseController {

    @Autowired
    private TranTestService testMultiDbService;

    @RequestMapping("/success")
    public Object testSuccess() {
        testMultiDbService.beginTest();
        return SuccessResponseData.success();
    }

    @RequestMapping("/fail")
    public Object testFail() {
        testMultiDbService.beginTestFail();
        return SuccessResponseData.success();
    }
}