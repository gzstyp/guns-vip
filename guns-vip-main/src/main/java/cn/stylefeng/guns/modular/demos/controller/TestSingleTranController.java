package cn.stylefeng.guns.modular.demos.controller;

import cn.stylefeng.guns.modular.demos.service.TranTestService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单数据源回滚
 * @author stylefeng
 * @Date 2018/7/20 23:39
*/
@RestController
@RequestMapping("/tran/single")
public class TestSingleTranController extends BaseController {

    @Autowired
    private TranTestService testMultiDbService;

    @RequestMapping("/success")
    public Object testSuccess() {
        testMultiDbService.testSingleSuccess();
        return SuccessResponseData.success();
    }

    @RequestMapping("/fail")
    public Object testFail() {
        testMultiDbService.testSingleFail();
        return SuccessResponseData.success();
    }
}